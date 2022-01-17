package com.gft.ddcz.ubs.codingtask.validation.forward;

import com.gft.ddcz.ubs.codingtask.model.Forward;
import com.gft.ddcz.ubs.codingtask.validation.base.AbstractDateValidator;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;

@Component
public class ForwardValueDateValidator extends AbstractDateValidator<Forward> {

    private final Clock clock;

    public ForwardValueDateValidator(Clock clock) {
        super("forward.value.date.must.be.after.current.date");
        this.clock = clock;
    }

    @Override
    protected LocalDate getFirstDate(Forward target) {
        return target.getValueDate();
    }

    @Override
    protected Comparison getComparison() {
        return Comparison.AFTER;
    }

    @Override
    protected LocalDate getSecondDate(Forward target) {
        return LocalDate.now(clock);
    }
}