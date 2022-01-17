package com.gft.ddcz.ubs.codingtask.validation.spot;

import com.gft.ddcz.ubs.codingtask.model.Spot;
import com.gft.ddcz.ubs.codingtask.validation.base.AbstractDateValidator;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;

@Component
public class SpotValueDateValidator extends AbstractDateValidator<Spot> {

    private final Clock clock;

    public SpotValueDateValidator(Clock clock) {
        super("spot.value.date.must.be.current.date");
        this.clock = clock;
    }

    @Override
    protected LocalDate getFirstDate(Spot target) {
        return target.getValueDate();
    }

    @Override
    protected Comparison getComparison() {
        return Comparison.EXACTLY;
    }

    @Override
    protected LocalDate getSecondDate(Spot target) {
        return LocalDate.now(clock);
    }
}
