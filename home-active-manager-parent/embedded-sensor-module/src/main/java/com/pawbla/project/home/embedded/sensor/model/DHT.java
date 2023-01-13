package com.pawbla.project.home.embedded.sensor.model;

import java.math.BigDecimal;

public class DHT {
    private BigDecimal temperature;
    private BigDecimal humidity;

    public DHT(BigDecimal temperature, BigDecimal humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }
}
