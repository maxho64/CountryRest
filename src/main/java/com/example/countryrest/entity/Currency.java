package com.example.countryrest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Currency {
    @JsonProperty("GBP")
    GBP,
    @JsonProperty("EUR")
    EUR,
    @JsonProperty("PLN")
    PLN;

    @JsonValue
    public String value() {
        return name();
    }

    public static Currency fromValue(String v) {
        return valueOf(v);
    }

}
