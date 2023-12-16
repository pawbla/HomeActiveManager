package com.pawbla.project.home.user.management;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = AFTER_CLASS)
public class UserManagementIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    int serverPort;

    @Test
    public void get_user_by_username() throws IOException {
        String actual = testRestTemplate.getForObject(getUri("user?login=user"), String.class);
        String expected = readFile("integration/userByName.json");
        JSONObject actualJSON = new JSONObject(actual);
        JSONAssert.assertEquals("Measurements response", new JSONObject(expected), actualJSON, true);
    }

    @Test
    public void get_users() throws IOException {
        String actual = testRestTemplate.getForObject(getUri("users"), String.class);
        String expected = readFile("integration/usersList.json");
        JSONObject actualJSON = new JSONObject(actual);
        assertTrue("Response contains elements", new JSONObject(expected).length() > 0);
    }

    @Test
    public void get_roles() throws IOException {
        String actual = testRestTemplate.getForObject(getUri("roles"), String.class);
        String expected = readFile("integration/rolesList.json");
        JSONObject actualJSON = new JSONObject(actual);
        JSONAssert.assertEquals("Measurements response", new JSONObject(expected), actualJSON, false);
    }

    @Test
    public void add_user() throws IOException {
        String user = readFile("integration/userToStore.json");
        String response = testRestTemplate.postForObject(getUri("user"), prepareRequest(user), String.class);
        assertEquals("user stored succesfully", response);
    }

    @Test
    public void remove_user() throws IOException {
        testRestTemplate.delete(getUri("user/4"));
        ResponseEntity<String> response = testRestTemplate.getForEntity(getUri("user?login=deleteUser"), String.class);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    public void update_user() throws IOException {
        String user = readFile("integration/userUpdateInput.json");
        ResponseEntity<String> response = testRestTemplate.exchange(getUri("user/5"), HttpMethod.PUT,
                prepareRequest(user), String.class);
        assertEquals(200, response.getStatusCode().value());
        String actual = testRestTemplate.getForObject(getUri("user?login=updateUser"), String.class);
        String expected = readFile("integration/userUpdateExpected.json");
        JSONObject actualJSON = new JSONObject(actual);
        JSONAssert.assertEquals("Measurements response", new JSONObject(expected), actualJSON, true);
    }

    @Test
    public void update_password() throws IOException {
        String user = readFile("integration/passwordUpdate.json");
        ResponseEntity<String> response = testRestTemplate.exchange(getUri("password/6"), HttpMethod.PUT,
                prepareRequest(user), String.class);
        assertEquals(200, response.getStatusCode().value());
    }

    private String getUri(String path) {
        return "http://localhost:" + serverPort + "/api/v1/usermanagement/" + path;
    }

    private String readFile(String path) throws IOException {
        File resource = new ClassPathResource(path).getFile();
        return new String(Files.readAllBytes(resource.toPath()));
    }

    private HttpEntity<String> prepareRequest(String request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<String>(new JSONObject(request).toString(), headers);
    }
}
