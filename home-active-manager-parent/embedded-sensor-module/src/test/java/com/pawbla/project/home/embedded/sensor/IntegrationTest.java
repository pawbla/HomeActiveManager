package com.pawbla.project.home.embedded.sensor;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    int serverPort;

    private static final String REGEXP = "dateTime\":\"[0-9-]* [0-9:]*";
    private static final String DATE = "dateTime\":\"2021-10-23 01:01:01";

    @Test
    public void shouldAnswerWithTrue() throws InterruptedException, IOException {
        TimeUnit.SECONDS.sleep(20);
        ResponseEntity<String> resposne = testRestTemplate.getForEntity(getUri("measurements"), String.class);
        String actual = resposne.getBody().replaceAll(REGEXP, DATE);
        String headerDate = resposne.getHeaders().get("Date").get(0);
        String expected = readFile("integration/expectedMeasurements.json");
        JSONAssert.assertEquals("Status response", expected, new JSONObject(actual), false);
        assertNotNull(headerDate);
        assertThat(headerDate, matchesPattern("^[A-Z][a-z]{2,3},\\s[0-9]{1,2}\\s[A-Z][a-z]{2,3}\\s20[0-9]{2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}\\sGMT$"));
    }

    private String getUri(String path) {
        return "http://localhost:" + serverPort + "/api/v1/embedded/" + path;
    }

    private String readFile(String path) throws IOException {
        File resource = new ClassPathResource(path).getFile();
        return new String(Files.readAllBytes(resource.toPath()));
    }
}
