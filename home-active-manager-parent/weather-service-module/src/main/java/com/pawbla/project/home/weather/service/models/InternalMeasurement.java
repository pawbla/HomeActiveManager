package com.pawbla.project.home.weather.service.models;

import static com.pawbla.project.home.weather.service.utils.Constants.EMPTY;

public class InternalMeasurement extends Measurement {
    private String temperature = EMPTY;
    private String humidity = EMPTY;

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
