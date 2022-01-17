package com.gft.ddcz.ubs.codingtask.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Forward extends Trade {
    public static final String NAME = "Forward";
}
