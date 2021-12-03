package com.pawbla.project.home.weather.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/weather")
public class RestControllers {

    @Autowired
    private ConnectorDetailsRenderer connectorDetailsRenderer;

    @Autowired
    private WeatherRenderer weatherRenderer;

    @GetMapping(value = "/measurements", produces= MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<String> weather() {
        return ResponseEntity.ok().body(weatherRenderer.getJSON());
    }

    @GetMapping(value = "/status", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getConnectorsDetails() {
        return ResponseEntity.ok().body(connectorDetailsRenderer.getJSON());
    }
}
