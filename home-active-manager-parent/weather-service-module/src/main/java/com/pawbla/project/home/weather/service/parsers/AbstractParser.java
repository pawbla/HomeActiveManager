package com.pawbla.project.home.weather.service.parsers;

import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.Response;
import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractParser implements ParserInterface {

    private Map<String, String> parsed;

    public AbstractParser() {
        parsed = new HashMap<String, String>();
    }

    @Override
    public Measurement parse(Response response) throws JSONException {
        return null;
    }

    @Override
    public Measurement parse(int value) throws JSONException {
        return null;
    }

    public String getRoundedDouble(Double value) {
        DecimalFormat df = new DecimalFormat("#");
        return df.format(value).toString();
    }
}
