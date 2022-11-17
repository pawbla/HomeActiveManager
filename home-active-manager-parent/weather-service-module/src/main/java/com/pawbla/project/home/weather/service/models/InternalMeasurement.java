package com.pawbla.project.home.weather.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalMeasurement extends Measurement {
    @JsonProperty("temperature")
    private String temperature;
    @JsonProperty("humidity")
    private String humidity;
    @JsonProperty("isError")
    private boolean isError;
    @JsonProperty("dateTime")
    private String dateTime;

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    @Override
    public boolean isError() {
        return isError;
    }

    public String getDateTime() {
        return dateTime;
    }
}
