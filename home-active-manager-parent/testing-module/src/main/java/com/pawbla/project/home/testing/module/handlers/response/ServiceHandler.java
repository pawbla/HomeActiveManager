package com.pawbla.project.home.testing.module.handlers.response;

import org.springframework.http.ResponseEntity;

public interface ServiceHandler {
    ResponseEntity<String> getResponse();
    String getBody();
}
