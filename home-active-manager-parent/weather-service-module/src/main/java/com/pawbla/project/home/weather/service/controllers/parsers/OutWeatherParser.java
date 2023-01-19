package com.pawbla.project.home.weather.service.controllers.parsers;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.models.AirLyMeasurement;
import com.pawbla.project.home.weather.service.models.airly.Value;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

import static com.pawbla.project.home.weather.service.utils.Constants.DOUBLE_DEFAULT_VALUE;

@Component("outWeather")
public class OutWeatherParser extends AbstractParser<AirLyMeasurement> {

    private static final String TEMPERATURE = "temperature";
    private static final String HUMIDITY = "humidity";

    private static final String TEMPERATURE_OBJ_NAME = "TEMPERATURE";
    private static final String HUMIDITY_OBJ_NAME = "HUMIDITY";

    private final HandlerInterface airLy;

    private double temperature;
    private double humidity;

    public OutWeatherParser(@Qualifier("AirLy") HandlerInterface airLy) {
        this.airLy = airLy;
        this.temperature = DOUBLE_DEFAULT_VALUE;
        this.humidity = DOUBLE_DEFAULT_VALUE;
    }

    @Override
    protected void parse() {
        AirLyMeasurement measurement = getMeasurement(airLy);
        final List<Value> valueList = measurement.getCurrent().getValues();
        temperature = getValueByName(valueList, TEMPERATURE_OBJ_NAME);
        humidity = getValueByName(valueList, HUMIDITY_OBJ_NAME);
        isError = measurement.isError();

    }

    @Override
    protected JSONObject getParsed() {
        return new JSONObject()
                .put(TEMPERATURE, getValue(String.format (Locale.ROOT, "%.1f", temperature), isError))
                .put(HUMIDITY, getRoundedValue(humidity, isError));
    }
}
