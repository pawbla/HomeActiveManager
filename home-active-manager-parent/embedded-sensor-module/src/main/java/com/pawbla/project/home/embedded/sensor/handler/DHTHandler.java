package com.pawbla.project.home.embedded.sensor.handler;

import com.pawbla.project.home.embedded.sensor.model.DHT;
import com.pawbla.project.home.embedded.sensor.reader.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Component
public class DHTHandler implements Handler {

    private final LinkedList<DHT> measurements;
    private final Reader reader;
    private LocalDateTime lastCorrectReadData;
    private int errCounter;

    private static final int LIST_MAX_SIZE = 10;
    private static final int MAX_ACCEPTABLE_CONSECUTIVE_ERRORS = 3;

    @Autowired
    public DHTHandler(Reader reader) {
        this.reader = reader;
        measurements = new LinkedList<>();
        errCounter = 0;
        lastCorrectReadData = LocalDateTime.of(2000, 1, 1, 1, 1, 1);
    }

    @Scheduled(fixedRate = 20000, initialDelay = 20000)
    public void handle() throws InterruptedException {
        DHT dht;
        reader.read();
        dht = (DHT) reader.getDht();
        addDHTToList(measurements, dht);
        handleError(dht);
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
        return errCounter > MAX_ACCEPTABLE_CONSECUTIVE_ERRORS;
    }

    private void addDHTToList(LinkedList list, DHT dht) {
        if (!dht.isError()) {
            list.add(dht);
            lastCorrectReadData = LocalDateTime.now();
            if (list.size() > LIST_MAX_SIZE) {
                list.remove();
            }
        }
    }

    private void handleError(DHT dht) {
        if (dht.isError()) {
            errCounter++;
        } else {
            errCounter = 0;
        }
    }
}
