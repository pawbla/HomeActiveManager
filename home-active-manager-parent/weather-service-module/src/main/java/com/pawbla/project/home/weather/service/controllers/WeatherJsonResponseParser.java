package com.pawbla.project.home.weather.service.controllers;

import com.pawbla.project.home.weather.service.controllers.parsers.ResponseParser;
import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.old.MoonPhaseMeasurement;
import com.pawbla.project.home.weather.service.parsers.old.MoonPhaseParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class WeatherJsonResponseParser implements Renderer {
    private ResponseParser sunRiseSetParser;
    private ResponseParser internalParser;
    private ResponseParser weatherParser;
    private ResponseParser outWeatherParser;
    private ResponseParser airPollutionParser;
    private ResponseParser historyParser;

    //TODO OLD

    private HandlerInterface moonPhase;

    @Autowired
    public WeatherJsonResponseParser(@Qualifier("sunRiseSetParser") ResponseParser sunRiseSetParser,
                                     @Qualifier("internalParser") ResponseParser internalParser,
                                     @Qualifier("weather") ResponseParser weatherParser,
                                     @Qualifier("outWeather") ResponseParser outWeatherParser,
                                     @Qualifier("airpollution") ResponseParser airPollutionParser,
                                     @Qualifier("moonPhase") HandlerInterface moonPhase,
                                     @Qualifier("history") ResponseParser historyParser) {
        this.weatherParser = weatherParser;
        this.moonPhase = moonPhase;
        this.sunRiseSetParser = sunRiseSetParser;
        this.internalParser = internalParser;
        this.outWeatherParser = outWeatherParser;
        this.airPollutionParser = airPollutionParser;
        this.historyParser = historyParser;
    }

    @Override
    public String getJSON() {
        JSONObject response = new JSONObject()
                .put("in", internalParser.getParsedObject())
                .put("out", outWeatherParser.getParsedObject())
                .put("weather", weatherParser.getParsedObject())
                .put("airPolution", airPollutionParser.getParsedObject())
                .put("sun", sunRiseSetParser.getParsedObject())
                .put("history", historyParser.getParsedObject())
                .put("moon", new JSONObject()
                        .put(MoonPhaseParser.MoonPhaseValues.TEXT.getValue(),
                                setMeasureObj(getMoonPhaseMeasurement(), this.getMoonPhaseMeasurement().getText()))
                        .put(MoonPhaseParser.MoonPhaseValues.PICTURE.getValue(),
                                setMeasureObj(getMoonPhaseMeasurement(), this.getMoonPhaseMeasurement().getPictureNo())));
        return response.toString();
    }

    private JSONObject setMeasureObj(Measurement measurement, String value) { //TODO OLD
        return new JSONObject()
                .put("value", value)
                .put("isError", measurement.isError());
    }

    private MoonPhaseMeasurement getMoonPhaseMeasurement() {
        return (MoonPhaseMeasurement) moonPhase.getMeasurement();
    }
}
