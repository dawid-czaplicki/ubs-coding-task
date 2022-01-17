package com.gft.ddcz.ubs.codingtask.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;
import java.util.Set;

/**
 * Dummy service to provide holidays for currencies. This should normally implement a complete, up-to-date schedule or
 * consume a proper web service to provide accurate data.
 */
@Service
public class HolidayService {

    private static final Map<Currency, Set<LocalDate>> HOLIDAYS = Map.of(
            Currency.getInstance("EUR"),
            Set.of(LocalDate.of(2022, 1, 22),
                    LocalDate.of(2022, 2, 28))
    );

    /**
     * Checks if a given date is a holiday for the given currency.
     *
     * @param currency currency to be checked
     * @param valueDate value date to be validated
     * @return {@code true} if the given date is a holiday for the currency, {@code false} otherwise
     */
    public boolean isHoliday(Currency currency, LocalDate valueDate) {
        return getHolidaysForCurrency(currency).contains(valueDate);
    }

    private Set<LocalDate> getHolidaysForCurrency(Currency currency) {
        return HOLIDAYS.getOrDefault(currency, Set.of());
    }
}
