package com.pawbla.project.home.weather.service.models.airly;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Index {
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private double value;
    @JsonProperty("level")
    private String level;
    @JsonProperty("description")
    private String description;
    @JsonProperty("advice")
    private String advice;
    @JsonProperty("color")
    private String color;

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public String getAdvice() {
        return advice;
    }

    public String getColor() {
        return color;
    }
}
