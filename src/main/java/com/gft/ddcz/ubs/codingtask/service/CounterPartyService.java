package com.gft.ddcz.ubs.codingtask.service;

import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Dummy service to provide valid counterparties.
 */
@Service
public class CounterPartyService {

    private static final Set<String> KNOWN_COUNTERPARTIES = Set.of("YODA1", "YODA2");

    /**
     * Checks if the given counterparty is known and hence valid.
     *
     * @param customer counterparty to validate
     * @return {@code true} if the counterparty is known, {@code false} otherwise
     */
    public boolean isKnownCounterparty(String customer) {
        return KNOWN_COUNTERPARTIES.contains(customer);
    }

}
