package com.pawbla.project.home.parsers;

import com.pawbla.project.home.models.Response;
import org.json.JSONException;

import java.util.Map;

public interface ParserInterface {
    void parse(Response response) throws JSONException;
    Map<String, String> getParsed();
}
