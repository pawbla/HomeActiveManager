package com.pawbla.project.home.application.monitoring.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawbla.project.home.application.monitoring.handler.MonitoringHandler;
import com.pawbla.project.home.application.monitoring.models.Monitoring;
import com.pawbla.project.home.application.monitoring.services.ShutdownApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/monitoring")
public class RestApiController {

    private final ShutdownApplicationsService shutdownApplicationsService;
    private final MonitoringHandler monitoringHandler;
    private final ObjectMapper objectMapper;

    @Autowired
    public RestApiController(final ShutdownApplicationsService shutdownApplicationsService, final MonitoringHandler monitoringHandler) {
        this.shutdownApplicationsService = shutdownApplicationsService;
        this.monitoringHandler = monitoringHandler;
        this.objectMapper = new ObjectMapper();
    }



    @PutMapping(value = "/shutdown/{cmd}", consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<String> shutdown(@PathVariable String cmd) {
        boolean ret = shutdownApplicationsService.execute(cmd);
        if (!ret) {
            return ResponseEntity.badRequest().body("Incorrect path parameter");
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/status", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<String> monitoring() throws JsonProcessingException {
        Monitoring monitoring = monitoringHandler.getMonitoring();
        String response = objectMapper.writeValueAsString(monitoring);
        return ResponseEntity.ok().body(response);
    }
}