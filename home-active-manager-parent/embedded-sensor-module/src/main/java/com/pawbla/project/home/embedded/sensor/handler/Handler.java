package com.pawbla.project.home.embedded.sensor.handler;

import java.time.LocalDateTime;

public interface Handler {
    void handle();
    int getTemperature();
    int getHumidity();
    LocalDateTime getLastCorrectReadData();
    boolean isError();
}
