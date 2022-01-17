package com.gft.ddcz.ubs.codingtask.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import lombok.Data;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Forward.class, name = Forward.NAME),
        @JsonSubTypes.Type(value = Spot.class, name = Spot.NAME),
        @JsonSubTypes.Type(value = Option.class, name = Option.NAME),
})
@Data
@Valid
public abstract class FxTransaction {
    private LocalDate tradeDate;
    private String customer;

    private Map<String, Object> unknownProperties;

    @JsonAlias("ccyPair")
    private String currencyPair;

    @JsonSetter
    @JsonIgnore
    public void setUnknownProperty(String name, Object value) {
        unknownProperties.put(name, value);
    }

    @JsonIgnore
    public String getCurrency1() {
        return currencyPair.substring(0, 3);
    }

    @JsonIgnore
    public String getCurrency2() {
        return currencyPair.substring(3);
    }
}
