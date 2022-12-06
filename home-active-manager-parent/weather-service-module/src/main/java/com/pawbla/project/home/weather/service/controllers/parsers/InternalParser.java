package com.pawbla.project.home.weather.service.controllers.parsers;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.models.InternalMeasurement;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("internalParser")
public class InternalParser extends AbstractParser<InternalMeasurement> {

    private static final String TEMPERATURE = "temperature";
    private static final String HUMIDITY = "humidity";

    private final HandlerInterface internal;

    public InternalParser(@Qualifier("internal") HandlerInterface internal) {
        this.internal = internal;
    }

    @Override
    public JSONObject getParsedObject() {
        InternalMeasurement measurement = getMeasurement(internal);
        return new JSONObject()
                .put(TEMPERATURE, getValue(measurement.getTemperature(), measurement.isError()))
                .put(HUMIDITY, getValue(measurement.getHumidity(), measurement.isError()));
    }
}
