package com.gft.ddcz.ubs.codingtask.validation.trade;

import com.gft.ddcz.ubs.codingtask.model.FxTransaction;
import com.gft.ddcz.ubs.codingtask.model.Trade;
import com.gft.ddcz.ubs.codingtask.validation.base.AbstractDateValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.gft.ddcz.ubs.codingtask.validation.base.AbstractDateValidator.Comparison.NOT_BEFORE;

@Component
public class ValueDateValidator extends AbstractDateValidator<Trade> {

    protected ValueDateValidator() {
        super("value.date.not.before.trade.date");
    }

    @Override
    protected LocalDate getFirstDate(Trade fxTransaction) {
        return fxTransaction.getValueDate();
    }

    @Override
    protected Comparison getComparison() {
        return NOT_BEFORE;
    }

    @Override
    protected LocalDate getSecondDate(Trade fxTransaction) {
        return fxTransaction.getTradeDate();
    }
}
