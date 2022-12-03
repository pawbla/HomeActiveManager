package com.pawbla.project.home.weather.service.controllers.parsers;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.models.AirLyMeasurement;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("outWeather")
public class OutWeatherParser extends AbstractParser<AirLyMeasurement> {

    private static final String TEMPERATURE = "temperature";
    private static final String HUMIDITY = "humidity";

    private static final String TEMPERATURE_OBJ_NAME = "TEMPERATURE";
    private static final String HUMIDITY_OBJ_NAME = "HUMIDITY";

    private final HandlerInterface airLy;

    public OutWeatherParser(@Qualifier("AirLy") HandlerInterface airLy) {
        this.airLy = airLy;
    }

    @Override
    public JSONObject getParsedObject() {
        AirLyMeasurement measurement = getMeasurement(airLy);
        return new JSONObject()
                .put(TEMPERATURE, getRoundedValue(getValueByName(measurement.getCurrent().getValues(), TEMPERATURE_OBJ_NAME),
                        measurement.isError()))
                .put(HUMIDITY, getRoundedValue(getValueByName(measurement.getCurrent().getValues(), HUMIDITY_OBJ_NAME),
                        measurement.isError()));
    }
}
