package com.pawbla.project.home.embedded.sensor.controllers;

import com.pawbla.project.home.embedded.sensor.handler.DHTHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api/v1/embedded")
public class RestController {

    @Autowired
    private ResponseRenderer renderer;

    @GetMapping(value = "/measurements", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> weather() {
        return ResponseEntity.ok()
                .headers(getHeader())
                .body(renderer.getJSON());
    }

    private HttpHeaders getHeader() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Date", Instant.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME));
        return responseHeaders;
    }
}
