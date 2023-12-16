package com.pawbla.project.home.history.module.service;

import com.pawbla.project.home.history.module.client.WeatherMeasurementClient;
import com.pawbla.project.home.history.module.dao.WeatherHistoryDao;
import com.pawbla.project.home.history.module.model.WeatherMeasurement;
import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HistoryHandler {

    private final Logger LOG = LogManager.getLogger(HistoryHandler.class);

    private final WeatherHistoryDao weatherHistoryDao;
    private final WeatherMeasurementClient measurementClient;

    public HistoryHandler(WeatherHistoryDao weatherHistoryDao, WeatherMeasurementClient measurementClient) {
        this.weatherHistoryDao = weatherHistoryDao;
        this.measurementClient = measurementClient;
    }

    public void handle() {
        try {
            WeatherMeasurement weatherMeasurement = measurementClient.getSimplifiedMeasurement();
            weatherHistoryDao.save(weatherMeasurement);
        } catch (FeignException e) {
            LOG.warn("An Feign exception has occurred {}", e.getMessage());
        } catch (Exception e) {
            LOG.warn("An exception has occurred {}", e.getMessage());
        }
    }
}
