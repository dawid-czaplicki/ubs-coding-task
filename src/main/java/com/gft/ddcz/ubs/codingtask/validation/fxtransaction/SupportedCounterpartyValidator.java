package com.gft.ddcz.ubs.codingtask.validation.fxtransaction;

import com.gft.ddcz.ubs.codingtask.model.FxTransaction;
import com.gft.ddcz.ubs.codingtask.service.CounterPartyService;
import com.gft.ddcz.ubs.codingtask.validation.ValidationResult;
import com.gft.ddcz.ubs.codingtask.validation.ValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupportedCounterpartyValidator implements ValidationRule<FxTransaction> {

    private final CounterPartyService counterPartyService;

    @Override
    public void validate(FxTransaction target, ValidationResult validationResult) {
        if (!counterPartyService.isKnownCounterparty(target.getCustomer())) {
            validationResult.addError("unknown.counterparty");
        }
    }
}
