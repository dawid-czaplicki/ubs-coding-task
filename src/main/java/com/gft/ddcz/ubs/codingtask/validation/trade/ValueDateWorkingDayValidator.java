package com.gft.ddcz.ubs.codingtask.validation.trade;

import com.gft.ddcz.ubs.codingtask.model.FxTransaction;
import com.gft.ddcz.ubs.codingtask.model.Trade;
import com.gft.ddcz.ubs.codingtask.validation.ValidationResult;
import com.gft.ddcz.ubs.codingtask.validation.ValidationRule;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.EnumSet;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

@Component
public class ValueDateWorkingDayValidator implements ValidationRule<Trade> {

    private static final EnumSet<DayOfWeek> WEEKEND = EnumSet.of(SATURDAY, SUNDAY);

    @Override
    public void validate(Trade target, ValidationResult errors) {
        if(target.getValueDate() == null) {
            return;
        }

        var tradeDayOfWeek = target.getValueDate().getDayOfWeek();
        if (WEEKEND.contains(tradeDayOfWeek)) {
            errors.addError("value.date.must.not.be.on.weekend");
        }
    }
}
