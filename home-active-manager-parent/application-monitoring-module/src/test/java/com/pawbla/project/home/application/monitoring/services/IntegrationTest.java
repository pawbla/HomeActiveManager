package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.handler.MonitoringHandlerImpl;
import com.pawbla.project.home.application.monitoring.utils.DateTimeUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext
class IntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    int serverPort;

    @MockBean
    private CommandExecutor commandExecutor;

    @MockBean
    private DateTimeUtils dateTimeUtils;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MonitoringHandlerImpl monitoringHandler;

    @Captor
    ArgumentCaptor<String> cmdCaptor;

    private MockRestServiceServer mockRestServiceServer;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(commandExecutor.execute(anyString())).thenReturn(-9).thenReturn(1);
        Mockito.when(dateTimeUtils.getNow()).thenReturn(LocalDateTime.of(2022, 8, 16, 19, 40, 1));
        Mockito.when(dateTimeUtils.getTimeOf(anyString())).thenReturn(LocalDateTime.of(2022, 8, 16, 20, 40, 1));
        mockRestServiceServer =  MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
    }

    @Test
    void shouldShutdownApplications() {

        prepareMockedSuccessResponse("http://localhost:8081/actuator/shutdown", HttpMethod.POST);
        prepareMockedSuccessResponse("http://localhost:8082/actuator/shutdown", HttpMethod.POST);

        prepareMockedResourceAccessException("http://localhost:8081/actuator/health/liveness");
        prepareMockedResourceAccessException("http://localhost:8082/actuator/health/liveness");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getUri("shutdown/hold"), HttpMethod.PUT, request, String.class);

        mockRestServiceServer.verify();
        verify(commandExecutor, Mockito.times(2)).execute(cmdCaptor.capture());
        assertEquals("sudo shutdown -h now", cmdCaptor.getValue());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void shouldRestartApplications() {

        prepareMockedSuccessResponse("http://localhost:8081/actuator/shutdown", HttpMethod.POST);
        prepareMockedSuccessResponse("http://localhost:8082/actuator/shutdown", HttpMethod.POST);

        prepareMockedResourceAccessException("http://localhost:8081/actuator/health/liveness");
        prepareMockedResourceAccessException("http://localhost:8082/actuator/health/liveness");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getUri("shutdown/restart"), HttpMethod.PUT, request, String.class);

        mockRestServiceServer.verify();
        verify(commandExecutor, Mockito.times(2)).execute(cmdCaptor.capture());
        assertEquals("sudo shutdown -r now", cmdCaptor.getValue());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void shouldHandleBadRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getUri("shutdown/badrequest"), HttpMethod.PUT, request, String.class);

        mockRestServiceServer.verify();
        verify(commandExecutor, Mockito.never()).execute(cmdCaptor.capture());
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Incorrect path parameter", response.getBody());
    }

    @Test
    void shouldProvideApplicationAndSystemStatus() throws IOException {
        //Given
        prepareMockedSuccessResponseWithBody("http://localhost:8081/actuator/info", HttpMethod.GET,
                prepareInfoResponse("FirstApp", "1.0"));
        prepareMockedSuccessResponseWithBody("http://localhost:8081/actuator/health", HttpMethod.GET,
                prepareHealthCheckResponse("UP"));
        prepareMockedSuccessResponseWithBody("http://localhost:8082/actuator/info", HttpMethod.GET,
                prepareInfoResponse("SecondApp", "2.0s"));
        prepareMockedSuccessResponseWithBody("http://localhost:8082/actuator/health", HttpMethod.GET,
                prepareHealthCheckResponse("DOWN"));

        monitoringHandler.scheduled();
        //When
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = testRestTemplate.exchange(getUri("status"), HttpMethod.GET,
                new HttpEntity<Object>(headers), String.class);
        //Then
        String expected = readFile("integration/expectedStatus.json");
        JSONObject actualJSON = new JSONObject(response.getBody());
        JSONAssert.assertEquals("Status response", new JSONObject(expected), actualJSON, true);

    }

    private String getUri(String path) {
        return "http://localhost:" + serverPort + "/api/v1/monitoring/" + path;
    }

    private void prepareMockedSuccessResponse(String url, HttpMethod httpMethod) {
        prepareMockedSuccessResponseWithBody(url, httpMethod, "ok");
    }

    private void prepareMockedSuccessResponseWithBody(String url, HttpMethod httpMethod, String body) {
        mockRestServiceServer.expect(times(1), requestTo(url))
                .andExpect(method(httpMethod))
                .andRespond(withSuccess(body, MediaType.APPLICATION_JSON));
    }

    private void prepareMockedResourceAccessException(String url) {
        mockRestServiceServer.expect(requestTo(url))
                .andRespond((response) -> { throw new ResourceAccessException("No resources available"); });
    }

    private String prepareInfoResponse(String appName, String version) {
        JSONObject app = new JSONObject()
                .put("version", version)
                .put("name", appName);

         return new JSONObject()
                .put("app",app)
                .toString();
    }

    private String prepareHealthCheckResponse(String status) {
        return new JSONObject()
                .put("status",status)
                .toString();
    }

    private String readFile(String path) throws IOException {
        File resource = new ClassPathResource(path).getFile();
        return Files.readString(resource.toPath());
    }
}