package com.pawbla.project.home.weather.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WindDirection {
    @JsonProperty("Localized")
    private String windDirection;
    @JsonProperty("Degrees")
    private String windDirectionDeg;

    public String getWindDirection() {
        return windDirection;
    }

    public String getWindDirectionDeg() {
        return windDirectionDeg;
    }
}
