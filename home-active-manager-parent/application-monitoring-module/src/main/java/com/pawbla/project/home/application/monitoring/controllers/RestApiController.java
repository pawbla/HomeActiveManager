package com.pawbla.project.home.application.monitoring.controllers;

import com.pawbla.project.home.application.monitoring.services.ShutdownApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/monitoring")
public class RestApiController {

    @Autowired
    private ShutdownApplicationsService shutdownApplicationsService;

    @PostMapping(value = "/shutdown", consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> registerUser() {
        shutdownApplicationsService.execute();
        return ResponseEntity.ok().build();
    }
}
