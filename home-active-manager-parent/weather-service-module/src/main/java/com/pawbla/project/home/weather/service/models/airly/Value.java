package com.pawbla.project.home.weather.service.models.airly;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Value {
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private Double value;

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }
}
