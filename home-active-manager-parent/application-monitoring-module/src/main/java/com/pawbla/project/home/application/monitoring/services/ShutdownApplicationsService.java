package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.restclient.RestClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Map;
import java.util.function.BiConsumer;

@Service
public class ShutdownApplicationsService {

    private final Logger log = LogManager.getLogger(this.getClass().getName());

    private static final String LOCAL_URL = "http://localhost";
    private static final String ACTUATOR_ENDPOINT = "/actuator";
    private static final String SHUTDOWN_ENDPOINT = ACTUATOR_ENDPOINT + "/shutdown";
    private static final String HEALTH_LIVENESS_ENDPOINT = ACTUATOR_ENDPOINT + "/health/liveness";
    private static final String HOLD_UNIX_COMMAND = "sudo shutdown -h now";
    private static final String RESTART_UNIX_COMMAND = "sudo shutdown -r now";
    private static final Long WAIT_TIMEOUT_CLOSE_CHECK_MS = 2000L;
    private static final int CLOSE_CHECK_ATTEMPTS = 3;

    @Value("#{${registered.apps}}")
    private final Map<String,String> registeredApplications;

    @Autowired
    private RestClient restClient;

    @Autowired
    private CommandExecutor commandExecutor;

    public ShutdownApplicationsService(Map<String, String> registeredApplications) {
        this.registeredApplications = registeredApplications;
    }

    public boolean execute(String cmd) {
        String shutdownCmd;
        if (cmd.equalsIgnoreCase("hold")) {
            shutdownCmd = HOLD_UNIX_COMMAND;
        } else if (cmd.equalsIgnoreCase("restart")) {
            shutdownCmd = RESTART_UNIX_COMMAND;
        } else {
            return false;
        }
        registeredApplications.forEach(shutdownApplication);
        int exitCode = commandExecutor.execute(shutdownCmd);
        if (exitCode != 0) {
            log.warn("Fallback for incorrect exit code. Trying to execute command again.");
            exitCode = commandExecutor.execute(shutdownCmd);
        }
        log.info("System shutdown command executed with {} exit code.", exitCode);
        return true;
    }

    private final BiConsumer<String,String> shutdownApplication = new BiConsumer<String, String>() {
        public void accept(String name, String port) {
            log.info("Shutdown {} application", name);
            try {
                sendShutdownRequest(port);
                if (checkApplicationNotClosed(port)) {
                    log.info("Execute fallback request");
                    sendShutdownRequest(port);
                    checkApplicationNotClosed(port);
                }
            } catch (RestClientException e) {
                log.warn("Cannot shutdown {} application on port {}. Message: {}", name, port, e.getMessage());
            }
        }
    };

    private boolean checkApplicationNotClosed(String port) {
        int iter = 0;
        try {
            while (iter < CLOSE_CHECK_ATTEMPTS) {
                Thread.sleep(WAIT_TIMEOUT_CLOSE_CHECK_MS);
                ResponseEntity<String> getResponse = restClient.get(prepareHealthLivenessCheckUrl(port));
                log.info("Health liveness check response code {} for iteration {}", getResponse.getStatusCode(), iter);
                iter++;
            }
            return true;
        } catch (InterruptedException | RestClientException e) {
            log.info("Exception catch. Application not responding, it's ok and that is mean application is correctly closed");
        }
        return false;
    }

    private void sendShutdownRequest(String port) {
        ResponseEntity<String> response =  restClient.post(prepareShutdownUrl(port));
        log.info("Shutdown request response code " + response.getStatusCode());
    }

    private String prepareShutdownUrl(String port) {
        return LOCAL_URL + ":" + port + SHUTDOWN_ENDPOINT;
    }

    private String prepareHealthLivenessCheckUrl(String port) {
        return LOCAL_URL + ":" + port + HEALTH_LIVENESS_ENDPOINT;
    }
}
