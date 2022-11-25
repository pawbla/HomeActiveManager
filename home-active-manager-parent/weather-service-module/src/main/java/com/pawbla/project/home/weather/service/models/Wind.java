package com.pawbla.project.home.weather.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

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
