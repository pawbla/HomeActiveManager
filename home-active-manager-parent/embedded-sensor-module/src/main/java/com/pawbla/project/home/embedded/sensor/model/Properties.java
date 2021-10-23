package com.pawbla.project.home.embedded.sensor.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class Properties {
    private int dhtDataPin;
    private int dhtSupplyPin;

    public int getDhtSupplyPin() {
        return dhtSupplyPin;
    }

    public void setDhtSupplyPin(int dhtSupplyPin) {
        this.dhtSupplyPin = dhtSupplyPin;
    }

    public int getDhtDataPin() {
        return dhtDataPin;
    }

    public void setDhtDataPin(int dhtDataPin) {
        this.dhtDataPin = dhtDataPin;
    }
}
