package com.pawbla.project.home.embedded.sensor.controllers;

import com.pawbla.project.home.embedded.sensor.handler.DHTHandler;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ResponseRenderer {

    private DHTHandler dhtHandler;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
}
