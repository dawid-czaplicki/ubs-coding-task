package com.gft.ddcz.ubs.codingtask.validation.options.american;

import com.gft.ddcz.ubs.codingtask.model.Option;
import com.gft.ddcz.ubs.codingtask.validation.ValidationResult;
import com.gft.ddcz.ubs.codingtask.validation.base.AbstractDateValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.gft.ddcz.ubs.codingtask.validation.base.AbstractDateValidator.Comparison.BEFORE;

@Component
public class ExcerciseStartExpiryDatesValidator extends AbstractDateValidator<Option> {

    protected ExcerciseStartExpiryDatesValidator() {
        super("excercise.start.date.before.expiry.date");
    }

    @Override
    public void validate(Option target, ValidationResult validationResult) {
        if (target.getStyle() == Option.Style.AMERICAN) {
            super.validate(target, validationResult);
        }
    }

    @Override
    protected LocalDate getFirstDate(Option target) {
        return target.getExcerciseStartDate();
    }

    @Override
    protected Comparison getComparison() {
        return BEFORE;
    }

    @Override
    protected LocalDate getSecondDate(Option target) {
        return target.getExpiryDate();
    }
}
