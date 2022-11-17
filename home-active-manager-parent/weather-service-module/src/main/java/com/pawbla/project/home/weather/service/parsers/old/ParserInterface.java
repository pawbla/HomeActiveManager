package com.pawbla.project.home.weather.service.parsers.old;

import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.Response;
import org.json.JSONException;

public interface ParserInterface {
    Measurement parse(Response response) throws JSONException;
    Measurement parse(int value) throws JSONException;
}
