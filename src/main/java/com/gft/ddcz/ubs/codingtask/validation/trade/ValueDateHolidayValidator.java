package com.gft.ddcz.ubs.codingtask.validation.trade;

import com.gft.ddcz.ubs.codingtask.model.Trade;
import com.gft.ddcz.ubs.codingtask.service.HolidayService;
import com.gft.ddcz.ubs.codingtask.validation.ValidationResult;
import com.gft.ddcz.ubs.codingtask.validation.ValidationRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Currency;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValueDateHolidayValidator implements ValidationRule<Trade> {

    private final HolidayService holidayService;

    @Override
    public void validate(Trade target, ValidationResult errors) {

        var valueDate = target.getValueDate();
        try {
            validateHolidayForCurrency(errors, valueDate, target.getCurrency1());
            validateHolidayForCurrency(errors, valueDate, target.getCurrency2());
        } catch (Exception e) {
            log.error("Unable to validate holidays for currency", e);
            errors.addError("unable.to.validate.holidays.for.currency");
        }
    }

    private void validateHolidayForCurrency(ValidationResult errors, LocalDate valueDate, String currencyCode) {
        if (holidayService.isHoliday(Currency.getInstance(currencyCode), valueDate)) {
            errors.addError("value.date.must.not.be.on.holiday");
        }
    }
}
