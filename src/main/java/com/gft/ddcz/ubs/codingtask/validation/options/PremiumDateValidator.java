package com.gft.ddcz.ubs.codingtask.validation.options;

import com.gft.ddcz.ubs.codingtask.model.Option;
import com.gft.ddcz.ubs.codingtask.validation.base.AbstractDateValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.gft.ddcz.ubs.codingtask.validation.base.AbstractDateValidator.Comparison.BEFORE;

@Component
public class PremiumDateValidator extends AbstractDateValidator<Option> {

    protected PremiumDateValidator() {
        super("premium.date.before.deliveryDate");
    }

    @Override
    protected LocalDate getFirstDate(Option target) {
        return target.getPremiumDate();
    }

    @Override
    protected Comparison getComparison() {
        return BEFORE;
    }

    @Override
    protected LocalDate getSecondDate(Option target) {
        return target.getDeliveryDate();
    }
}
