package com.pawbla.project.home.embedded.sensor.reader;

import com.pawbla.project.home.embedded.sensor.model.DHT;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Profile({"prod"})
public class DHTJniReader implements Reader {
    static {
        System.loadLibrary("dhtread");
    }

    private float humidity = 0;
    private float temperature = 0;

    public native int read(int type, int pin);

    @Override
    public Object getDht() {
        return new DHT(BigDecimal.valueOf(temperature).setScale(1, RoundingMode.HALF_DOWN), BigDecimal.valueOf(humidity).setScale(1, RoundingMode.HALF_DOWN));
    }
}