package com.pawbla.project.home.embedded.sensor.reader;

import com.pawbla.project.home.embedded.sensor.model.DHT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/*
    Only for development purpose when pi4j cannot be used
 */
@Component
@Profile({"dev", "test"})
public class DHTDevReader implements Reader {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private int pin;
    private int supplyPin;


    public DHTDevReader(@Value("${custom.dhtDataPin}") int pin, @Value("${custom.dhtSupplyPin}") int supplyPin) {
        logger.info("DHTDevReader initialisation with data pin: " + pin + " and supply pin: " + supplyPin);
        this.pin = pin;
        this.pin = supplyPin;
    }

    @Override
    public void read() {
        logger.info("Read values.");
    }

    @Override
    public Object getDht() {
        logger.info("Get DHT");
        return new DHT(12, 35, false);
    }
}
