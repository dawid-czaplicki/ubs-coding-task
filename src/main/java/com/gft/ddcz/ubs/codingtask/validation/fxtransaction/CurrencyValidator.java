package com.gft.ddcz.ubs.codingtask.validation.fxtransaction;

import com.gft.ddcz.ubs.codingtask.model.FxTransaction;
import com.gft.ddcz.ubs.codingtask.validation.ValidationResult;
import com.gft.ddcz.ubs.codingtask.validation.ValidationRule;
import org.springframework.stereotype.Component;

import java.util.Currency;

import static org.apache.commons.lang3.StringUtils.length;

@Component
public class CurrencyValidator implements ValidationRule<FxTransaction> {

    public static final int CURRENCY_LENGTH = 3;

    @Override
    public void validate(FxTransaction target, ValidationResult validationResult) {
        var currencyPair = target.getCurrencyPair();

        if (length(currencyPair) != 2 * CURRENCY_LENGTH) {
            validationResult.addError("currency.pair.expected.length");
        } else {
            validateCurrencyCode(validationResult, target.getCurrency1());
            validateCurrencyCode(validationResult, target.getCurrency2());
        }
    }

    private void validateCurrencyCode(ValidationResult validationResult, String currency) {
        try {
            Currency.getInstance(currency);
        } catch (IllegalArgumentException iae) {
            validationResult.addError("incorrect.currency.code");
        }
    }
}
