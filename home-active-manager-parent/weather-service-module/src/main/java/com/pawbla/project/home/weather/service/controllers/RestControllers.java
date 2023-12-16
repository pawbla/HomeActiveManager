package com.pawbla.project.home.weather.service.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/weather")
public class RestControllers {

    private final ConnectorDetailsRenderer connectorDetailsRenderer;
    private final WeatherJsonResponseParser weatherJsonResponseParser;
    private final SimplifiedWeatherRenderer simplifiedWeatherRenderer;

    public RestControllers(ConnectorDetailsRenderer connectorDetailsRenderer, WeatherJsonResponseParser weatherJsonResponseParser,
                           SimplifiedWeatherRenderer simplifiedWeatherRenderer) {
        this.connectorDetailsRenderer = connectorDetailsRenderer;
        this.weatherJsonResponseParser = weatherJsonResponseParser;
        this.simplifiedWeatherRenderer = simplifiedWeatherRenderer;
    }

    @GetMapping(value = "/measurements", produces= MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<String> weather() {
        return ResponseEntity.ok().body(weatherJsonResponseParser.getJSON());
    }

    @GetMapping(value = "/status", produces=MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<String> getConnectorsDetails() {
        return ResponseEntity.ok().body(connectorDetailsRenderer.getJSON());
    }

    @GetMapping(value = "/simplifiedMeasurement", produces=MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<String> getSimplifiedMeasurement() {
        return ResponseEntity.ok().body(simplifiedWeatherRenderer.getJSON());
    }
}
