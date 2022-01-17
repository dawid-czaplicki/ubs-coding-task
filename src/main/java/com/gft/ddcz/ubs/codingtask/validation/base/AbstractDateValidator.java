package com.gft.ddcz.ubs.codingtask.validation.base;

import com.gft.ddcz.ubs.codingtask.model.FxTransaction;
import com.gft.ddcz.ubs.codingtask.validation.ValidationResult;
import com.gft.ddcz.ubs.codingtask.validation.ValidationRule;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.function.BiPredicate;

/**
 * Base class for validators focused on relation between two dates. Compares {@link #getFirstDate(FxTransaction)}
 * with {@link #getSecondDate(FxTransaction)} using {@link #getComparison()}. If the comparison fails, adds the
 * specified error code to the result list.
 * @param <T> type of transaction that can be validated using this validator
 */
public abstract class AbstractDateValidator<T extends FxTransaction> implements ValidationRule<T> {

    @RequiredArgsConstructor
    public enum Comparison {
        BEFORE(LocalDate::isBefore),
        AFTER(LocalDate::isAfter),
        NOT_BEFORE((d1, d2) -> !d1.isBefore(d2)),
        NOT_AFTER((d1, d2) -> !d1.isAfter(d2)),
        EXACTLY((d1,d2) -> d1.equals(d2));

        final BiPredicate<LocalDate, LocalDate> biPredicate;

        boolean isConditionMet(LocalDate firstDate, LocalDate secondDate) {
            return biPredicate.test(firstDate, secondDate);
        }

    }
    private final String errorCode;

    protected AbstractDateValidator(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public void validate(T target, ValidationResult validationResult) {

        var firstDate = getFirstDate(target);
        var secondDate = getSecondDate(target);

        boolean anyDateIsNull = firstDate == null || secondDate == null;

        if (anyDateIsNull || !conditionIsMet(firstDate, secondDate)) {
            validationResult.addError(errorCode);
        }
    }

    protected abstract LocalDate getFirstDate(T target);

    protected abstract Comparison getComparison();

    protected abstract LocalDate getSecondDate(T target);

    private boolean conditionIsMet(LocalDate firstDate, LocalDate secondDate) {
        return getComparison().isConditionMet(firstDate, secondDate);
    }
}
