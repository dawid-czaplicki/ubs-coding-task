package com.gft.ddcz.ubs.codingtask.rest;

import com.gft.ddcz.ubs.codingtask.model.FxTransaction;
import com.gft.ddcz.ubs.codingtask.service.TransactionValidationService;
import com.gft.ddcz.ubs.codingtask.validation.ValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Entry point for the validation requests.
 */
@RequiredArgsConstructor
@RestController("/validate")
@Slf4j
public class TransactionValidationController {

    private final TransactionValidationService transactionValidationService;
    private final MessageSource messageSource;

    /**
     * Validates the transactions and returns a list of validation results. The resulting array contains an entry for
     * each transaction, in the same order. The result for each transaction can either be SUCCESS or ERROR. In the latter case,
     * a list of errors is provided as well.
     * <p>
     * Should at least one validation result in an error, the HTTP Response Status is set to 400 Bad request. Otherwise,
     * it is 200 OK.
     *
     * @param fxTransactions the list of the transactions to be validated
     * @return either 200 OK or 400 Bad request response with a list of validation results for each of the transactions
     */
    @PostMapping
    ResponseEntity<List<ValidationResult>> validate(@RequestBody List<FxTransaction> fxTransactions) {
        var result = transactionValidationService.validateTransactions(fxTransactions);
        if (result.stream().anyMatch(ValidationResult::hasError)) {
            result.forEach(this::remapErrors);
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    private void remapErrors(ValidationResult validationResult) {
        var locale = LocaleContextHolder.getLocale();
        var translatedMessages = validationResult.getErrors()
                .stream()
                .map(error -> messageSource.getMessage(error, null, locale))
                .toList();
        validationResult.setErrors(translatedMessages);
    }
}

