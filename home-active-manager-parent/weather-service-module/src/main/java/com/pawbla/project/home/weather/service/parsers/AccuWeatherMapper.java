package com.pawbla.project.home.weather.service.parsers;

import com.pawbla.project.home.weather.service.models.AccuWeatherMeasurement;
import org.springframework.stereotype.Component;

@Component("accuWeather")
public class AccuWeatherMapper extends AbstractResponseMapper<AccuWeatherMeasurement> {
    @Override
    protected Class<AccuWeatherMeasurement> getValueType() {
        return AccuWeatherMeasurement.class;
    }

    @Override
    protected String getName() {
        return "AccuWeather";
    }

    @Override
    protected AccuWeatherMeasurement getMeasurementDefaultObject() {
        return new AccuWeatherMeasurement();
    }

    @Override
    protected String prepareBody(String body) {
        return body.replaceAll("^\\[|\\]$", "");
    }
}
