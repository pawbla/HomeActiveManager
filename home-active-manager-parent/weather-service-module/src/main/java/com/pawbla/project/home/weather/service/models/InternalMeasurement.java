package com.pawbla.project.home.weather.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalMeasurement extends Measurement {
    @JsonProperty("temperature")
    private Double temperature;
    @JsonProperty("humidity")
    private Double humidity;
    @JsonProperty("isError")
    private boolean isError;
    @JsonProperty("dateTime")
    private String dateTime;

    public Double getTemperature() {
        return temperature;
    }

    public Double getHumidity() {
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
