package com.pawbla.project.home.embedded.sensor.handler;

import java.time.LocalDateTime;

public interface Handler {
    void handle() throws InterruptedException;
    int getTemperature();
    int getHumidity();
    LocalDateTime getLastCorrectReadData();
    boolean isError();
    int getErrorCode();
}
