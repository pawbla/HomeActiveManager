package com.pawbla.project.home.embedded.sensor.reader;

import com.pawbla.project.home.embedded.sensor.model.DHT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
    Only for development purpose when pi4j cannot be used
 */
@Component
@Profile({"dev", "test"})
public class DHTDevReader implements Reader {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Override
    public int read(int type, int pin) {
        logger.info("Read values with DHT" + type + "and on pin " + pin);
        return 0;
    }

    @Override
    public Object getDht() {
        logger.info("Get DHT");
        float temp = 12.3f;
        float hum = 35.6f;
        return new DHT(BigDecimal.valueOf(temp).setScale(1, RoundingMode.HALF_DOWN), BigDecimal.valueOf(hum).setScale(1, RoundingMode.HALF_DOWN));
    }
}
