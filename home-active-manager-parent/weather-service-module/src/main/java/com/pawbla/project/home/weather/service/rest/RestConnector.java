package com.pawbla.project.home.weather.service.rest;


import com.pawbla.project.home.weather.service.models.Response;
import com.pawbla.project.home.weather.service.models.Connector;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.pawbla.project.home.weather.service.utils.Constants.EMPTY;

@Service
@Scope("prototype")
public class RestConnector implements RestInterface {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private final RestTemplate rest;
    private final Response response;
    private Connector connector;

    @Autowired
    public RestConnector() {
        logger.info("Create REST Template object.");
        rest = new RestTemplate();
        response = new Response();
    }

    public void execute() {
        logger.debug("Execute connection to url " + connector.getRequest().getIp() + " for service " + connector.getName());
        ResponseEntity<String> resp = null;
        try {
            connector.incrementRequestCnt();
            resp = getResponseEntity();
            logger.info("Received status code " + resp.getStatusCodeValue() + " for: " + connector.getName());
            if (resp.getStatusCodeValue() != 200) {
                connector.incrementErrorRequestCnt();
                response.setErrorAndMessage("TO FIX", true);
                response.setModified(false);
            }
            if (StringUtils.isNotBlank(resp.getBody())) {
                logger.trace("Received response for " + connector.getName() + " body: " + resp.getBody());
                response.setErrorAndMessage(EMPTY, false);
                response.setBody(resp.getBody());
                response.setOkResponseDate(getHeaderDateAsString(resp));
            }
            response.setDate(getHeaderDateAsString(resp));
            response.setResponseCode(resp.getStatusCodeValue());
        } catch (RestClientException e) {
            connector.incrementErrorRequestCnt();
            response.setResponseCode(520);
            response.setErrorAndMessage("Unknown error has occurred: " + e.getMessage(), true);
            response.setModified(false);
            logger.warn("An exception has occurred when executed connection to url " + connector.getName() + ": " + e.getMessage());
        } finally {
            connector.setResponse(response);
        }
    }

    private ResponseEntity<String> getResponseEntity() {
        ResponseEntity<String> resp = null;
        int iter = 0;
        while (iter < 3) {
            logger.debug("Get REST data for service " + connector.getName() + " iteration " + iter);
            try {
                resp = rest.exchange(connector.getRequest().getIp(), connector.getRequest().getMethod(), connector.getRequest().getEntity(), String.class);
                if (resp.getStatusCodeValue() == 200) {
                    logger.debug("Received message with response code 200 for service: " + connector.getName());
                    break;
                }
            } catch (RestClientException e) {
                logger.warn("Unable to fetch datas from ip " + connector.getName() + " caused by exception: " + e);
                if (iter == 2) {
                    //Throw an exception only when appeard for the last fallback call
                    throw new RestClientException(e.getMessage());
                }
            }
            iter++;
        }
        return resp;
    }

    private String getHeaderDateAsString(ResponseEntity<String> resp) {
        String headerDate = resp.getHeaders().get("Date").get(0);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(headerDate, DateTimeFormatter.RFC_1123_DATE_TIME);
        return zonedDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }
}
