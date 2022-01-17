package com.gft.ddcz.ubs.codingtask.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Option extends FxTransaction {
    public static final String NAME = "VanillaOption";

    public enum Style {
        AMERICAN, EUROPEAN
    }

    private LocalDate expiryDate;
    private LocalDate premiumDate;
    private LocalDate deliveryDate;
    private LocalDate excerciseStartDate;
    private Style style;
}
