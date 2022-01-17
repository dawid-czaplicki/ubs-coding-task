package com.gft.ddcz.ubs.codingtask.service;

import com.gft.ddcz.ubs.codingtask.model.FxTransaction;
import com.gft.ddcz.ubs.codingtask.validation.ValidationResult;
import com.gft.ddcz.ubs.codingtask.validation.ValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.core.ResolvableType.forClassWithGenerics;

/**
 * A service to validate transactions. The rules to which the transactions are validated are implemented in separate
 * beans, each implementing a single buisness rule. There can be multiple validation rules defined for a single
 * transaction type.
 */
@Service
@RequiredArgsConstructor
public class TransactionValidationService {

    private final ApplicationContext applicationContext;

    public List<ValidationResult> validateTransactions(List<FxTransaction> fxTransactions) {

        return fxTransactions.stream()
                .map(this::validateTransaction)
                .toList();
    }

    private ValidationResult validateTransaction(FxTransaction transaction) {
        var validationResult = new ValidationResult();

        getApplicableValidationRules(transaction)
                .stream()
                .map(applicationContext::getBean)
                .map(bean -> (ValidationRule<FxTransaction>) bean)
                .forEach(validator -> validator.validate(transaction, validationResult));

        return validationResult;
    }

    private ArrayList<String> getApplicableValidationRules(FxTransaction fxTransaction) {
        var validationRules = new ArrayList<String>();

        Class<?> subClass = fxTransaction.getClass();
        while (subClass != null) {
            var rules = applicationContext.getBeanNamesForType(forClassWithGenerics(ValidationRule.class, subClass));
            validationRules.addAll(asList(rules));
            subClass = subClass.getSuperclass();
        }

        return validationRules;
    }

}
