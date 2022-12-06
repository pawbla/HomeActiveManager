package com.pawbla.project.home.weather.service.controllers;

import com.pawbla.project.home.weather.service.controllers.parsers.ResponseParser;
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
    private ResponseParser moonPhaseParser;

    @Autowired
    public WeatherJsonResponseParser(@Qualifier("sunRiseSetParser") ResponseParser sunRiseSetParser,
                                     @Qualifier("internalParser") ResponseParser internalParser,
                                     @Qualifier("weather") ResponseParser weatherParser,
                                     @Qualifier("outWeather") ResponseParser outWeatherParser,
                                     @Qualifier("airpollution") ResponseParser airPollutionParser,
                                     @Qualifier("moonPhase") ResponseParser moonPhaseParser,
                                     @Qualifier("history") ResponseParser historyParser) {
        this.weatherParser = weatherParser;
        this.moonPhaseParser = moonPhaseParser;
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
                .put("moon", moonPhaseParser.getParsedObject());
        return response.toString();
    }
}
