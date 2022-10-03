package com.pawbla.project.home.embedded.sensor.handler;

import com.pawbla.project.home.embedded.sensor.model.DHT;
import com.pawbla.project.home.embedded.sensor.reader.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Component
public class DHTHandler implements Handler {
    private final Logger LOG = LogManager.getLogger(DHTHandler.class);

    private final LinkedList<DHT> measurements;
    private final Reader reader;
    private LocalDateTime lastCorrectReadData;
    private int pin;
    private boolean isError;

    private static final int LIST_MAX_SIZE = 10;
    private static final int DHT_SENSOR_TYPE = 22;

    @Autowired
    public DHTHandler(@Value("${custom.dhtDataPin}") int pin, Reader reader) {
        this.reader = reader;
        isError = false;
        measurements = new LinkedList<>();
        lastCorrectReadData = LocalDateTime.of(2000, 1, 1, 1, 1, 1);
        this.pin = pin;
    }

    @Scheduled(fixedRate = 20000, initialDelay = 20000)
    public void handle() {
        LOG.info("Read data from DHT sensor on pin " + pin);
        int errorCode = reader.read(DHT_SENSOR_TYPE, pin);
        addDHTToList(measurements, (DHT) reader.getDht(), errorCode);
    }

    @Override
    public int getTemperature() {
        int sum = measurements.stream().map(DHT::getTemperature).reduce(0, Integer::sum);
        int size = measurements.size();
        return size != 0 ? sum / size : 0;
    }

    @Override
    public int getHumidity() {
        int sum = measurements.stream().map(DHT::getHumidity).reduce(0, Integer::sum);
        int size = measurements.size();
        return size != 0 ? sum / size : 0;
    }

    @Override
    public LocalDateTime getLastCorrectReadData() {
        return lastCorrectReadData;
    }

    @Override
    public boolean isError() {
        return isError;
    }

    private void addDHTToList(LinkedList list, DHT dht, int errorCode) {
        if (errorCode == 0) {
            LOG.info("Data has read correctly");
            isError = false;
            list.add(dht);
            lastCorrectReadData = LocalDateTime.now();
            if (list.size() > LIST_MAX_SIZE) {
                list.remove();
            }
        } else {
            isError = true;
            LOG.warn("Data incorrectly read with error code " + errorCode);
        }
    }
}
