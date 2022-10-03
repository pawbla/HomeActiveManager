package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.Application;
import com.pawbla.project.home.application.monitoring.restclient.RestClient;
import com.pawbla.project.home.application.monitoring.utils.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
public class ApplicationMonitoringServiceImpl implements ApplicationMonitoringService {
    private final Logger log = LogManager.getLogger(ApplicationMonitoringServiceImpl.class);

    private static final String LOCAL_URL = "http://localhost";
    private static final String ACTUATOR = "actuator";
    private static final String INFO = "info";
    private static final String HEALTH = "health";
    private final RestClient restClient;
    private final DateTimeUtils dateTimeUtils;

    @Autowired
    public ApplicationMonitoringServiceImpl(final RestClient restClient, final DateTimeUtils dateTimeUtils) {
        this.restClient = restClient;
        this.dateTimeUtils = dateTimeUtils;
    }

    @Override
    public void readApplicationsInfo(List<Application> applicationList) {
        log.info("Read applications info");
        applicationList
                .stream()
                .filter(app -> StringUtils.isBlank(app.getName()))
                .forEach(this::updateApplicationInfo);
    }

    @Override
    public void healthCheck(List<Application> applicationList) {
        applicationList.forEach(this::healthCheck);
    }

    private void updateApplicationInfo(Application application) {
        String url = getInfoUrl(application.getPort());
        String responseBody = "";
        try {
            ResponseEntity<String> response = restClient.get(url);
            int statusCode = response.getStatusCodeValue();
            if (statusCode == 200) {
                responseBody = response.getBody();
                JSONObject parsedResponseBody = new JSONObject(responseBody);
                JSONObject appJsonObj = parsedResponseBody.getJSONObject("app");
                application.setName(appJsonObj.getString("name"));
                application.setVersion(appJsonObj.getString("version"));
                application.setDateTime(dateTimeUtils.getNow());
            } else {
                log.warn("Received response status code {} for {}.",statusCode, url);
            }
        } catch (RestClientException e) {
            log.warn("Cannot get response for {}. Message: {}", url, e.getMessage());
        } catch (JSONException e) {
            log.warn("Cannot parse response body {}. ", responseBody);
        }
    }

    private void healthCheck(Application application) {
        log.info("Health check for {}", application.getName());
        String url = getHealthCheckUrl(application.getPort());
        String responseBody = "";
        boolean health = false;
        try {
            ResponseEntity<String> response = restClient.get(url);
            int statusCode = response.getStatusCodeValue();
            if (statusCode == 200) {
                responseBody = response.getBody();
                JSONObject parsedResponseBody = new JSONObject(responseBody);
                if (parsedResponseBody.getString("status").equals("UP")) {
                    health = true;
                }
            } else {
                log.warn("Received response status code {} for {}.",statusCode, url);
            }
        } catch (RestClientException e) {
            log.warn("Cannot get response for {}. Message: {}", url, e.getMessage());
        } catch (JSONException e) {
            log.warn("Cannot parse response body {}. ", responseBody);
        }
        application.setHealth(health);
    }

    private String getInfoUrl(String port) {
        return getBaseUrl(port).concat(INFO);
    }

    private String getHealthCheckUrl(String port) {
        return getBaseUrl(port).concat(HEALTH);
    }

    private String getBaseUrl(String port) {
        return LOCAL_URL.concat(":").concat(port).concat("/").concat(ACTUATOR).concat("/");
    }
}
