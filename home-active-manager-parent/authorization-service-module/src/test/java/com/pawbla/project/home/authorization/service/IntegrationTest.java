package com.pawbla.project.home.authorization.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = AFTER_CLASS)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    int serverPort;

    @Test
    public void getBearerToken() {
        ResponseEntity<String> response = testRestTemplate.postForEntity(getUri("/oauth/token?grant_type=password&scope=any"), authorizationRequest(), String.class);
        assertEquals("Status response code", HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void incorrectCredentials() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("first-client", "noonewildaleverguess");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("username", "admin");
        map.add("password", "password");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = testRestTemplate.postForEntity(getUri("/oauth/token?grant_type=password&scope=any"), request, String.class);
        assertEquals("Status response code", HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void incorrectFormData() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("first-client", "noonewilleverguess");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("username", "noexistinguser");
        map.add("password", "password");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = testRestTemplate.postForEntity(getUri("/oauth/token?grant_type=password&scope=any"), request, String.class);
        assertEquals("Status response code", HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void callWithCorrectToken() throws JsonProcessingException {
        ResponseEntity<String> response = testRestTemplate.postForEntity(getUri("/oauth/token?grant_type=password&scope=any"), authorizationRequest(), String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        String token = root.path("access_token").asText();
        assertNotNull(token);
        response = testRestTemplate.postForEntity(getUri("/api/v1/authorization/validate"), prepareRequest(token), String.class);
        assertEquals("Status response code", HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void callWithIncorrectToken() {
        ResponseEntity<String> response = testRestTemplate.postForEntity(getUri("/api/v1/authorization/validate"), prepareRequest("NoExistingToken"), String.class);
        assertEquals("Status response code", HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void callWithoutToken() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> response = testRestTemplate.postForEntity(getUri("/api/v1/authorization/validate"),
                new HttpEntity<String>("{}", headers), String.class);
        assertEquals("Status response code", HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    private String getUri(String path) {
        return "http://localhost:" + serverPort + path;
    }

    private HttpEntity<String> prepareRequest(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return new HttpEntity<>("{}", headers);
    }

    private HttpEntity<MultiValueMap<String, String>> authorizationRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("first-client", "noonewilleverguess");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("username", "admin");
        map.add("password", "password");
        return new HttpEntity<>(map, headers);
    }
}
