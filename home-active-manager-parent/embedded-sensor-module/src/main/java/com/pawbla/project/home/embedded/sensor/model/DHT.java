package com.pawbla.project.home.embedded.sensor.model;

public class DHT {
    private int temperature;
    private int humidity;

    public DHT(int temperature, int humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }
}
