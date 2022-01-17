package com.gft.ddcz.ubs.codingtask.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.time.LocalDate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Forward.class, name = Forward.NAME),
        @JsonSubTypes.Type(value = Spot.class, name = Spot.NAME)
})
@Data
public abstract class Trade extends FxTransaction {
    private LocalDate valueDate;
}
