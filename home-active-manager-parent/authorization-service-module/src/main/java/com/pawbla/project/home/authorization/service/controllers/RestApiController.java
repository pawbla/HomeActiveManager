package com.pawbla.project.home.authorization.service.controllers;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/authorization")
public class RestApiController {

    @PostMapping(value = "/validate", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> registerUser() {
        return ResponseEntity.ok().body(getDummyResponse());
    }

    private String getDummyResponse() {
        JSONObject obj = new JSONObject()
                .put("active", true);
        return obj.toString();
    }
}
