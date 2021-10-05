package com.pawbla.project.home;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Integration test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest {

    private static final String REGEXP = "date\":\"[0-9.]* [0-9:]*";
    private static final String DATE = "date\":\"10.04 20:38";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    int serverPort;

    @Test
    public void measurementsConnctorTest() throws IOException {
        String actual = testRestTemplate.getForObject(getUri("measurements"), String.class);
        actual = actual.replaceAll(REGEXP, DATE);
        String expected = readFile("integration/expectedMeasurements.json");
        JSONAssert.assertEquals("Measurements response", expected, new JSONObject(actual), false);
    }

    @Test
    public void statusConnectorTest() throws IOException {
        getUri("asd");
        String actual = testRestTemplate.getForObject(getUri("status"), String.class);
        actual = actual.replaceAll(REGEXP, DATE);
        String expected = readFile("integration/expectedStatus.json");
        JSONAssert.assertEquals("Status response", expected, new JSONObject(actual), false);
    }

    private String getUri(String path) {
        return "http://localhost:" + serverPort + "/api/v1/weather/" + path;
    }

    private String readFile(String path) throws IOException {
        File resource = new ClassPathResource(path).getFile();
        return new String(Files.readAllBytes(resource.toPath()));
    }
}
