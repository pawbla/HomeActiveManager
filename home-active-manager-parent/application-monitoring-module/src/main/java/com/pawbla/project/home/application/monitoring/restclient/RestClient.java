package com.pawbla.project.home.application.monitoring.restclient;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface RestClient {
    ResponseEntity<String> get(String url);
    ResponseEntity<String> post(String url);
    ResponseEntity<String> post(String url, HttpHeaders headers, String body);
}
