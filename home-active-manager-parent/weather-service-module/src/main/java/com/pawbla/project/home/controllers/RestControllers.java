package com.pawbla.project.home.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class RestControllers {

    @Autowired
    private ConnectorDetailsRenderer connectorDetailsRenderer;

    @Autowired
    private WeatherRenderer weatherRenderer;

    @GetMapping(value = "/weather", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> weather() {
        return ResponseEntity.ok().body(weatherRenderer.getJSON());
    }

    @GetMapping(value = "/connectors", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getConnectorsDetails() {
        return ResponseEntity.ok().body(connectorDetailsRenderer.getJSON());
    }
}
