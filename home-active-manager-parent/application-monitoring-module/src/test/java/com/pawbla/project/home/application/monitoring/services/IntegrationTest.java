package com.pawbla.project.home.application.monitoring.services;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

    @Autowired
    private RestTemplate restTemplate;

    @Captor
    ArgumentCaptor<String> cmdCaptor;

    private MockRestServiceServer mockRestServiceServer;

    @Before
    public void before() {
        Mockito.when(commandExecutor.execute(anyString())).thenReturn(-9).thenReturn(1);
    }

    @Test
    public void shutdown_applications() {
        mockRestServiceServer =  MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();

        prepareMockedSuccessResponse("http://localhost:8081/actuator/shutdown", HttpMethod.POST);
        prepareMockedSuccessResponse("http://localhost:8082/actuator/shutdown", HttpMethod.POST);

        prepareMockedResourceAccessException("http://localhost:8081/actuator/health/liveness");
        prepareMockedResourceAccessException("http://localhost:8082/actuator/health/liveness");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        testRestTemplate.postForObject(getUri("shutdown"), request, String.class);

        mockRestServiceServer.verify();
        verify(commandExecutor).execute(cmdCaptor.capture());
        assertEquals("Captured shutdown command", "sudo shutdown -h now", cmdCaptor.getValue());

    }

    private String getUri(String path) {
        return "http://localhost:" + serverPort + "/api/v1/monitoring/" + path;
    }

    private void prepareMockedSuccessResponse(String url, HttpMethod httpMethod) {
        mockRestServiceServer.expect(times(1), requestTo(url))
                .andExpect(method(httpMethod))
                .andRespond(withSuccess("ok", MediaType.APPLICATION_JSON));
    }

    private void prepareMockedResourceAccessException(String url) {
        mockRestServiceServer.expect(requestTo(url))
                .andRespond((response) -> { throw new ResourceAccessException("No resources available"); });
    }
}