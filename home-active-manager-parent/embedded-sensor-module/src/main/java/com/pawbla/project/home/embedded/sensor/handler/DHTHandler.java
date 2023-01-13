package com.pawbla.project.home.embedded.sensor.handler;

import com.pawbla.project.home.embedded.sensor.model.DHT;
import com.pawbla.project.home.embedded.sensor.reader.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
    private int errorCode;

    private static final int LIST_MAX_SIZE = 10;
    private static final int DHT_SENSOR_TYPE = 22;
    private static final BigDecimal ZERO_DOUBLE = BigDecimal.valueOf(0);

    @Autowired
    public DHTHandler(@Value("${custom.dhtDataPin}") int pin, Reader reader) {
        this.reader = reader;
        isError = true;
        measurements = new LinkedList<>();
        lastCorrectReadData = LocalDateTime.of(2000, 1, 1, 1, 1, 1);
        this.pin = pin;
        this.errorCode = -9;
    }

    @Scheduled(fixedRate = 20000, initialDelay = 20000)
    public void handle() {
        LOG.info("Read data from DHT sensor on pin " + pin);
        errorCode = reader.read(DHT_SENSOR_TYPE, pin);
        addDHTToList(measurements, (DHT) reader.getDht(), errorCode);
    }

    @Override
    public BigDecimal getTemperature() {
        BigDecimal sum = measurements.stream().map(DHT::getTemperature).reduce(ZERO_DOUBLE, BigDecimal::add);
        int size = measurements.size();
        return size != 0 ? sum.divide(BigDecimal.valueOf(size)) : ZERO_DOUBLE;
    }

    @Override
    public BigDecimal getHumidity() {
        BigDecimal sum = measurements.stream().map(DHT::getHumidity).reduce(ZERO_DOUBLE, BigDecimal::add);
        int size = measurements.size();
        return size != 0 ? sum.divide(BigDecimal.valueOf(size)) : ZERO_DOUBLE;
    }

    @Override
    public LocalDateTime getLastCorrectReadData() {
        return lastCorrectReadData;
    }

    @Override
    public boolean isError() {
        return isError;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
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
