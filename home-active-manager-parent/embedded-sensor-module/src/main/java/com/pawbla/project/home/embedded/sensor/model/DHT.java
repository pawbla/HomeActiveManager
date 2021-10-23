package com.pawbla.project.home.embedded.sensor.model;

public class DHT {
    private int temperature;
    private int humidity;
    private boolean isError;

    public DHT(int temperature, int humidity, boolean isError) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.isError = isError;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public boolean isError() {
        return isError;
    }
}
