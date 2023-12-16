package com.pawbla.project.home.weather.service.controllers;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.models.AirLyMeasurement;
import com.pawbla.project.home.weather.service.models.InternalMeasurement;
import com.pawbla.project.home.weather.service.models.airly.Value;
import com.pawbla.project.home.weather.service.utils.DateTimeUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.pawbla.project.home.weather.service.controllers.parsers.OutWeatherParser.HUMIDITY_OBJ_NAME;
import static com.pawbla.project.home.weather.service.controllers.parsers.OutWeatherParser.TEMPERATURE_OBJ_NAME;
import static com.pawbla.project.home.weather.service.controllers.parsers.WeatherParser.PRESSURE_OBJ_NAME;

@Component
public class SimplifiedWeatherRenderer implements Renderer {


    private final HandlerInterface airLy;
    private final HandlerInterface internal;
    private final DateTimeUtils dateTimeUtils;

    public SimplifiedWeatherRenderer(@Qualifier("AirLy") HandlerInterface airLy, @Qualifier("internal") HandlerInterface internal,
                                     DateTimeUtils dateTimeUtils) {
        this.airLy = airLy;
        this.internal = internal;
        this.dateTimeUtils = dateTimeUtils;
    }

    @Override
    public String getJSON() {
        AirLyMeasurement airLyMeasurement = (AirLyMeasurement)airLy.getMeasurement();
        InternalMeasurement internalMeasurement = (InternalMeasurement)internal.getMeasurement();

        boolean isErrorAirLy = airLyMeasurement.isError();
        boolean isErrorInternal = internalMeasurement.isError();

        JSONObject response = new JSONObject()
                .put("timestamp", dateTimeUtils.getNowEpoch())
                .put("temperatureIn", prepareValue(isErrorInternal, internalMeasurement.getTemperature()))
                .put("humidityIn", prepareValue(isErrorInternal, internalMeasurement.getHumidity()))
                .put("temperatureOut", prepareValue(isErrorAirLy, airLyMeasurement, TEMPERATURE_OBJ_NAME))
                .put("humidityOut", prepareValue(isErrorAirLy, airLyMeasurement, HUMIDITY_OBJ_NAME))
                .put("pressure", prepareValue(isErrorAirLy, airLyMeasurement, PRESSURE_OBJ_NAME));

        return response.toString();
    }

    private Double prepareValue(boolean isError, Double value) {
        if (isError || value == null) {
            return null;
        }
        return value;
    }

    private Double prepareValue(boolean isError, AirLyMeasurement measurement, String name) {
        List<Value> valueList = measurement.getCurrent().getValues();
        if (isError) {
            return null;
        }
        return getValueByName(valueList, name);
    }

    protected Double getValueByName(final List<Value> valueList, String name) {
        return valueList.stream().filter(v -> v.getName().equals(name)).map(Value::getValue).findFirst().orElse(null);
    }
}
