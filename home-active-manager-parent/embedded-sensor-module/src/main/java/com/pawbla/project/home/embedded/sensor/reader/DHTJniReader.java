package com.pawbla.project.home.embedded.sensor.reader;

import com.pawbla.project.home.embedded.sensor.model.DHT;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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
        return new DHT(Math.round(temperature), Math.round(humidity));
    }
}