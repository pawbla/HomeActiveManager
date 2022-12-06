package com.pawbla.project.home.weather.service.models.airly;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Standard {
    @JsonProperty("name")
    private String name;
    @JsonProperty("pollutant")
    private String pollutant;
    @JsonProperty("limit")
    private double limit;
    @JsonProperty("percent")
    private double percent;
    @JsonProperty("averaging")
    private String averaging;

    public String getName() {
        return name;
    }

    public String getPollutant() {
        return pollutant;
    }

    public double getLimit() {
        return limit;
    }

    public double getPercent() {
        return percent;
    }

    public String getAveraging() {
        return averaging;
    }
}
