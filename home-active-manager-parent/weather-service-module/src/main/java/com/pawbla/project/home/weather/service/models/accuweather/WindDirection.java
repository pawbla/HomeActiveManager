package com.pawbla.project.home.weather.service.models.accuweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
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
