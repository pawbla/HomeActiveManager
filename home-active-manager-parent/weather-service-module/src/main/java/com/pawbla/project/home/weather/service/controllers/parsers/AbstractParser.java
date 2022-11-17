package com.pawbla.project.home.weather.service.controllers.parsers;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import org.json.JSONObject;

public abstract class AbstractParser<T> implements ResponseParser {

    protected T getMeasurement(HandlerInterface handlerInterface) {
        return (T) handlerInterface.getMeasurement();
    }

    protected JSONObject getValue(String value, boolean isError) {
        return new JSONObject()
                .put("value", value)
                .put("isError", isError);
    }
}
