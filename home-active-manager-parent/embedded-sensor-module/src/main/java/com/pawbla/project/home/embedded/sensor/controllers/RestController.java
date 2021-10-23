package com.pawbla.project.home.embedded.sensor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api/v1/embedded")
public class RestController {

    @Autowired
    private ResponseRenderer renderer;

    @GetMapping(value = "/measurements", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> weather() {
        return ResponseEntity.ok().body(renderer.getJSON());
    }
}
