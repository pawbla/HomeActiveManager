package com.pawbla.project.home.application.monitoring.restclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientImplementation implements RestClient {

    private final Logger log = LogManager.getLogger(this.getClass().getName());

    @Autowired
    private RestTemplate restTemplate;


    public ResponseEntity<String> get(String url) {
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> post(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return this.post(url, headers, "{}");
    }

    public ResponseEntity<String> post(String url, HttpHeaders headers, String body) {
        HttpEntity<String> request = new HttpEntity<>(new JSONObject(body).toString(), headers);
        return restTemplate.postForEntity(url, request, String.class);
    }

}
