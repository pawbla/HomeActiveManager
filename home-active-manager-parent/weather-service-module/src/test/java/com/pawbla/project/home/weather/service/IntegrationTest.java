package com.pawbla.project.home.weather.service;

import com.pawbla.project.home.weather.service.utils.DateTimeUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.mockito.Mockito.when;

/**
 * Integration test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest {

    private static final String REGEXP_SUN_RISE = "value\":\"0[0-9]:46";
    private static final String DATE_SUN_RISE = "value\":\"06:00";
    private static final String REGEXP_SUN_SET = "value\":\"1[0-9]:33";
    private static final String DATE_SUN_SET = "value\":\"18:00";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private DateTimeUtils dataTimeUtils;

    @LocalServerPort
    int serverPort;

    @BeforeAll
    void init() {
        when(dataTimeUtils.getCurrentYear()).thenReturn(2022);
        when(dataTimeUtils.getCurrentMonth()).thenReturn(2);
        when(dataTimeUtils.getCurrentDay()).thenReturn(4);
        when(dataTimeUtils.getCurrentHour()).thenReturn(12);
        when(dataTimeUtils.getCurrentMinute()).thenReturn(0);
        when(dataTimeUtils.getCurrentSecond()).thenReturn(0);
    }


    @Test
    public void measurementsConnctorTest() throws IOException, JSONException {
        String actual = testRestTemplate.getForObject(getUri("measurements"), String.class);
        actual = actual.replaceAll(REGEXP_SUN_RISE, DATE_SUN_RISE);
        actual = actual.replaceAll(REGEXP_SUN_SET, DATE_SUN_SET);
        String expected = readFile("integration/expectedMeasurements.json");
        JSONObject actualJSON = new JSONObject(actual);
        JSONAssert.assertEquals("Measurements response", new JSONObject(expected), actualJSON, true);
    }

    @Test
    public void statusConnectorTest() throws IOException, JSONException {
        String actual = testRestTemplate.getForObject(getUri("status"), String.class);
        String expected = readFile("integration/expectedStatus.json");
        JSONAssert.assertEquals("Status response", expected, new JSONObject(actual), true);
    }

    private String getUri(String path) {
        return "http://localhost:" + serverPort + "/api/v1/weather/" + path;
    }

    private String readFile(String path) throws IOException {
        File resource = new ClassPathResource(path).getFile();
        return new String(Files.readAllBytes(resource.toPath()), StandardCharsets.UTF_8);
    }
}
