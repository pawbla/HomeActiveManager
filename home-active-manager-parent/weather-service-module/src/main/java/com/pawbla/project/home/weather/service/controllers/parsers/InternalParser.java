package com.pawbla.project.home.weather.service.controllers.parsers;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.models.InternalMeasurement;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.autoconfigure.metrics.export.elastic.ElasticMetricsExportAutoConfiguration;
import org.springframework.stereotype.Component;

import static com.pawbla.project.home.weather.service.utils.Constants.EMPTY;

@Component("internalParser")
public class InternalParser extends AbstractParser<InternalMeasurement> {

    private static final String TEMPERATURE = "temperature";
    private static final String HUMIDITY = "humidity";

    private final HandlerInterface internal;
    private String temperature;
    private String humidity;

    public InternalParser(@Qualifier("internal") HandlerInterface internal) {
        this.internal = internal;
        this.temperature = EMPTY;
        this.humidity = EMPTY;
    }

    @Override
    protected void parse() {
        InternalMeasurement measurement = getMeasurement(internal);
        isError = measurement.isError();
        temperature = measurement.getTemperature();
        humidity = measurement.getHumidity();
    }

    @Override
    protected JSONObject getParsed() {
        return new JSONObject()
                .put(TEMPERATURE, getValue(temperature, isError))
                .put(HUMIDITY, getValue(humidity, isError));
    }
}
