package com.gft.ddcz.ubs.codingtask.validation;

import com.gft.ddcz.ubs.codingtask.model.FxTransaction;
import com.gft.ddcz.ubs.codingtask.service.TransactionValidationService;

/**
 * A single business rule to validate a transaction. The rules are applied in {@link TransactionValidationService}.
 */
public interface ValidationRule<T extends FxTransaction> {

    /**
     * Validates the given transaction. Any validation errors are applied to {@link ValidationResult#addError(String)}.
     *
     * @param target the transaction to be validated
     * @param result object aggregating the validation results
     */
    void validate(T target, ValidationResult result);
}
