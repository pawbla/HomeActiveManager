package com.pawbla.project.home.weather.service.handlers;

import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.Response;
import com.pawbla.project.home.weather.service.parsers.ResponseMapper;
import com.pawbla.project.home.weather.service.registry.ConnectorsRegistryInterface;
import com.pawbla.project.home.weather.service.rest.RestInterface;
import com.pawbla.project.home.weather.service.models.Connector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

public abstract class AbstractHandler implements HandlerInterface {

    /**
     * Logger
     */
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private ResponseMapper responseMapper;
    private RestInterface restConnector;
    private Connector connector;
    private ConnectorsRegistryInterface registry;
    private Measurement measurement;
    private int delay;
    private int confTime;

    public AbstractHandler(RestInterface restConnector, ConnectorsRegistryInterface registry) {
        this.registry = registry;
        this.restConnector = restConnector;
    }

    public void setConnector(Connector connector) {
        this.restConnector.setConnector(connector);
        this.connector = connector;
        this.registry.registerConnector(connector);
        this.delay = 0;
        this.confTime = 0;
    }

    public void setResponseMapper(ResponseMapper responseMapper) {
        this.responseMapper = responseMapper;
    }

    public Response getResponse() {
        return this.connector.getResponse();
    }

    @Override
    public void setRecovery(int delay, int confTime) {
        this.delay = delay;
        this.confTime = confTime;
    }

    @Override
    public void execute() {
        int iter = 0;
        do {
            this.restConnector.execute();
            Response response = this.connector.getResponse();
            measurement = responseMapper.map(response);
            if (response.getResponseCode() == 200 || confTime == 0) {
                break;
            }

            logger.debug("Prepare " + iter + " recovery reading data for connector " + this.connector.getName());
            try {
                Thread.sleep((long)delay * 60 * 1000);
            } catch (InterruptedException e) {
                logger.error("An exception has occurred during thread sleep " + e.getMessage());
            }
            iter++;
        } while (iter < confTime / (delay * 2));

    }

    public Measurement getMeasurement() {
        return this.measurement;
    }

    @Scheduled(cron="0 0 0 * * ?", zone="Europe/Warsaw")
    public void clearRequestCounter() {
        logger.info("Clean daily request counter for {}", connector.getName());
        connector.restartDailyRequestCounter();
    }


}
