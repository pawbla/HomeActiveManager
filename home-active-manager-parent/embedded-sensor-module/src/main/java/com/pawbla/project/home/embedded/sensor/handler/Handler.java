package com.pawbla.project.home.embedded.sensor.handler;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface Handler {
    void handle() throws InterruptedException;
    BigDecimal getTemperature();
    BigDecimal getHumidity();
    LocalDateTime getLastCorrectReadData();
    boolean isError();
    int getErrorCode();
}
