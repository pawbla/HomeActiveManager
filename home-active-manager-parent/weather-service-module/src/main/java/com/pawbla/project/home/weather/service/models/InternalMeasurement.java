package com.pawbla.project.home.weather.service.models;

public class InternalMeasurement extends Measurement {
    private String temperature;
    private String humidity;

    public void setMeasurements(String temperature, String humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }


}
