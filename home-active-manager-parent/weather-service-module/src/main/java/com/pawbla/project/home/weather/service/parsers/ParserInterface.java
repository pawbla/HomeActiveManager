package com.pawbla.project.home.weather.service.parsers;

import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.Response;
import org.json.JSONException;

import java.util.Map;

public interface ParserInterface {
    void parse(Response response) throws JSONException;
    Measurement getParsedAsObject();
}
