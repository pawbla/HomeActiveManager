package com.pawbla.project.home.weather.service.parsers;

import com.pawbla.project.home.weather.service.models.SunRiseSetMeasurement;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component("sunRiseSet")
public class SunRiseSetMapper extends AbstractResponseMapper<SunRiseSetMeasurement> {

    @Override
    protected String prepareBody(String body) {
        return new JSONObject(body).getJSONObject("results").toString();
    }

    @Override
    protected Class getValueType() {
        return SunRiseSetMeasurement.class;
    }

    @Override
    protected String getName() {
        return "SunRiseSetParser";
    }
}
