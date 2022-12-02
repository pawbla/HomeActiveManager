package com.pawbla.project.home.weather.service.controllers.parsers;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import org.json.JSONObject;

import static com.pawbla.project.home.weather.service.utils.Constants.EMPTY;

public abstract class AbstractParser<T> implements ResponseParser {

    protected T getMeasurement(HandlerInterface handlerInterface) {
        return (T) handlerInterface.getMeasurement();
    }

    protected JSONObject getRoundedValue(String value, boolean isError) {
        return getValue(roundString(value), isError);
    }

    protected JSONObject getValue(String value, boolean isError) {
        return new JSONObject()
                .put("value", value != null ? value : EMPTY )
                .put("isError", isError);
    }

    protected JSONObject getValue(boolean value, boolean isError) {
        return new JSONObject()
                .put("value", value)
                .put("isError", isError);
    }

    private String roundString(String value) {
        double valueInt = Double.parseDouble(value);
        return String.valueOf(Math.round(valueInt));
    }
}
