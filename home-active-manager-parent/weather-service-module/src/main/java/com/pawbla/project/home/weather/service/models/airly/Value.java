package com.pawbla.project.home.weather.service.models.airly;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Value {
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private double value;

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
}
