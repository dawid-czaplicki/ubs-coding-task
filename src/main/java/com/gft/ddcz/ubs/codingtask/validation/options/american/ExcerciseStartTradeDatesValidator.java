package com.gft.ddcz.ubs.codingtask.validation.options.american;

import com.gft.ddcz.ubs.codingtask.model.Option;
import com.gft.ddcz.ubs.codingtask.validation.ValidationResult;
import com.gft.ddcz.ubs.codingtask.validation.base.AbstractDateValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.gft.ddcz.ubs.codingtask.validation.base.AbstractDateValidator.Comparison.AFTER;

@Component
public class ExcerciseStartTradeDatesValidator extends AbstractDateValidator<Option> {

    protected ExcerciseStartTradeDatesValidator() {
        super("excercise.start.date.after.trade.date");
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
        return AFTER;
    }

    @Override
    protected LocalDate getSecondDate(Option target) {
        return target.getTradeDate();
    }
}
