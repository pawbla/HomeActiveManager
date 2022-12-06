package com.pawbla.project.home.weather.service.models.accuweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Wind {
    @JsonProperty("Direction")
    private WindDirection direction;
    @JsonProperty("Speed")
    private WindSpeed speed;

    public WindDirection getDirection() {
        return direction;
    }

    public WindSpeed getSpeed() {
        return speed;
    }
}
