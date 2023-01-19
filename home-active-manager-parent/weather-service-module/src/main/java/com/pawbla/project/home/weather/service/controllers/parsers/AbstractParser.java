package com.pawbla.project.home.weather.service.controllers.parsers;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.models.airly.Value;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.List;

import static com.pawbla.project.home.weather.service.utils.Constants.EMPTY;

public abstract class AbstractParser<T> implements ResponseParser {
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    protected static final double DEFAULT_VALUE = 0.0;
    protected boolean isError = true;

    @Override
    public JSONObject getParsedObject() {
        try {
            parse();
        } catch (NullPointerException e) {
            log.warn("NullPointerException returned for parsed object");
        }
        return getParsed();
    }

    protected abstract void parse();

    protected abstract JSONObject getParsed();

    protected T getMeasurement(HandlerInterface handlerInterface) {
        return (T) handlerInterface.getMeasurement();
    }

    protected double getValueByName(final List<Value> valueList, String name) {
        return valueList.stream().filter(v -> v.getName().equals(name)).map(Value::getValue).findFirst().orElse(DEFAULT_VALUE);
    }

    protected JSONObject getRoundedValue(String value, boolean isError) {
        return getValue(roundString(value), isError);
    }

    protected JSONObject getRoundedValue(double value, boolean isError) {
        return getValue(roundDouble(value), isError);
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

    protected String roundDouble(double value) {
        return String.valueOf(Math.round(value));
    }

    private String roundString(String value) {
        return roundDouble(Double.parseDouble(value));
    }
}
