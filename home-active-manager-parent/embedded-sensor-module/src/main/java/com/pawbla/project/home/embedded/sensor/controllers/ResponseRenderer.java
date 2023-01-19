package com.pawbla.project.home.embedded.sensor.controllers;

import com.pawbla.project.home.embedded.sensor.handler.DHTHandler;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ResponseRenderer {

    private static final String TIMEOUT_ERROR_MSG = "DHT Sensor timeout exceeded error";
    private static final String CHECKSUM_ERROR_MSG = "DHT Sensor checksum error";
    private static final String ARGUMENT_ERROR_MSG = "DHT Sensor argument error";
    private static final String GPIO_ERROR_MSG = "DHT Sensor GPIO error";
    private static final String STARTING_MSG = "DHT Sensor is starting";

    private final DHTHandler dhtHandler;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public ResponseRenderer(DHTHandler dhtHandler) {
        this.dhtHandler = dhtHandler;
    }

    public String getJSON() {
        JSONObject response = new JSONObject()
                .put("temperature", dhtHandler.getTemperature())
                .put("humidity", dhtHandler.getHumidity())
                .put("dateTime", dhtHandler.getLastCorrectReadData().format(formatter))
                .put("isError", dhtHandler.isError());
        return response.toString();
    }

    public String getErrorMessage() {
        int code = dhtHandler.getErrorCode();
        String msg = "Unexpected error code " + code;
        if (code == -1) {
            msg = TIMEOUT_ERROR_MSG;
        } else if (code == -2) {
            msg = CHECKSUM_ERROR_MSG;
        } else if (code == -3) {
            msg = ARGUMENT_ERROR_MSG;
        } else if (code == -4) {
            msg = GPIO_ERROR_MSG;
        } else if (code == -9) {
            msg = STARTING_MSG;
        }
        return msg;
    }

    public boolean isError() {
        return dhtHandler.isError();
    }
}
