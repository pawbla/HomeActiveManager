package com.pawbla.project.home.weather.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalMeasurement extends Measurement {
    @JsonProperty("temperature")
    private double temperature;
    @JsonProperty("humidity")
    private double humidity;
    @JsonProperty("isError")
    private boolean isError;
    @JsonProperty("dateTime")
    private String dateTime;

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
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
