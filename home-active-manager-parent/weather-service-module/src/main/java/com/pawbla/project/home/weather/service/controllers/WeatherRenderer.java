package com.pawbla.project.home.weather.service.controllers;

import com.pawbla.project.home.weather.service.models.Response;
import com.pawbla.project.home.weather.service.parsers.*;
import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.parsers.AccuWeatherParser;
import com.pawbla.project.home.weather.service.parsers.AirLyParser;
import com.pawbla.project.home.weather.service.parsers.SunRiseSetParser;
import com.pawbla.project.home.weather.service.parsers.Values;
import com.pawbla.project.home.weather.service.parsers.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class WeatherRenderer implements Renderer {
    private HandlerInterface internal;
    private HandlerInterface airLy;
    private HandlerInterface sunRiseSet;
    private HandlerInterface accuWeather;

    @Autowired
    public WeatherRenderer(@Qualifier("internal") HandlerInterface internal,
                           @Qualifier("AirLy") HandlerInterface airLy, @Qualifier("sunRiseSet") HandlerInterface sunRiseSet,
                           @Qualifier("accuWeather") HandlerInterface accuWeather) {
        this.internal = internal;
        this.airLy = airLy;
        this.sunRiseSet = sunRiseSet;
        this.accuWeather = accuWeather;
    }

    @Override
    public String getJSON() {
        JSONObject response = new JSONObject()
                .put("in", new JSONObject()
                        .put(InternalParser.InternalValues.TEMPERATURE.getValue(),  this.setMeasureObj(internal, InternalParser.InternalValues.TEMPERATURE))
                        .put(InternalParser.InternalValues.HUMIDITY.getValue(), this.setMeasureObj(internal, InternalParser.InternalValues.HUMIDITY)))
                .put("out", new JSONObject()
                        .put(AirLyParser.AirLyValues.TEMPERATURE.getValue(), this.setMeasureObj(airLy, AirLyParser.AirLyValues.TEMPERATURE))
                        .put(AirLyParser.AirLyValues.HUMIDITY.getValue(), this.setMeasureObj(airLy, AirLyParser.AirLyValues.HUMIDITY)))
                .put("weather", new JSONObject()
                        .put(AirLyParser.AirLyValues.PRESSURE.getValue(), this.setMeasureObj(airLy, AirLyParser.AirLyValues.PRESSURE))
                        .put(AccuWeatherParser.AccuWeatherValues.WEATHER_ICON.getValue(), this.setMeasureObj(accuWeather, AccuWeatherParser.AccuWeatherValues.WEATHER_ICON))
                        .put(AccuWeatherParser.AccuWeatherValues.WEATHER_TEXT.getValue(), this.setMeasureObj(accuWeather, AccuWeatherParser.AccuWeatherValues.WEATHER_TEXT))
                        .put(AccuWeatherParser.AccuWeatherValues.CLOUD_COVER.getValue(), this.setMeasureObj(accuWeather, AccuWeatherParser.AccuWeatherValues.CLOUD_COVER))
                        .put(AccuWeatherParser.AccuWeatherValues.CEILING.getValue(), this.setMeasureObj(accuWeather, AccuWeatherParser.AccuWeatherValues.CEILING))
                        .put(AccuWeatherParser.AccuWeatherValues.VISIBILITY.getValue(), this.setMeasureObj(accuWeather, AccuWeatherParser.AccuWeatherValues.VISIBILITY))
                        .put(AccuWeatherParser.AccuWeatherValues.WIND_DIRECTION.getValue(), this.setMeasureObj(accuWeather, AccuWeatherParser.AccuWeatherValues.WIND_DIRECTION))
                        .put(AccuWeatherParser.AccuWeatherValues.WIND_DIRECTION_DEG.getValue(), this.setMeasureObj(accuWeather, AccuWeatherParser.AccuWeatherValues.WIND_DIRECTION_DEG))
                        .put(AccuWeatherParser.AccuWeatherValues.WIND_SPEED.getValue(), this.setMeasureObj(accuWeather, AccuWeatherParser.AccuWeatherValues.WIND_SPEED))
                        .put(AccuWeatherParser.AccuWeatherValues.UV_INDEX_DESCRIPTION.getValue(), this.setMeasureObj(accuWeather, AccuWeatherParser.AccuWeatherValues.UV_INDEX_DESCRIPTION))
                        .put(AccuWeatherParser.AccuWeatherValues.UV_INDEX_VALUE.getValue(), this.setMeasureObj(accuWeather, AccuWeatherParser.AccuWeatherValues.UV_INDEX_VALUE))
                        .put(AccuWeatherParser.AccuWeatherValues.UV_INDEX_COLOR.getValue(), this.setMeasureObj(accuWeather, AccuWeatherParser.AccuWeatherValues.UV_INDEX_COLOR)))
                .put("airPolution", new JSONObject()
                        .put(AirLyParser.AirLyValues.CAQI.getValue(), this.setMeasureObj(airLy, AirLyParser.AirLyValues.CAQI))
                        .put(AirLyParser.AirLyValues.CAQI_COLOR.getValue(), this.setMeasureObj(airLy, AirLyParser.AirLyValues.CAQI_COLOR))
                        .put(AirLyParser.AirLyValues.PM_1.getValue(), this.setMeasureObj(airLy, AirLyParser.AirLyValues.PM_1))
                        .put(AirLyParser.AirLyValues.PM_10.getValue(), this.setMeasureObj(airLy, AirLyParser.AirLyValues.PM_10))
                        .put(AirLyParser.AirLyValues.PM_10_PERCENT.getValue(), this.setMeasureObj(airLy, AirLyParser.AirLyValues.PM_10_PERCENT))
                        .put(AirLyParser.AirLyValues.PM_25.getValue(), this.setMeasureObj(airLy, AirLyParser.AirLyValues.PM_25))
                        .put(AirLyParser.AirLyValues.PM_25_PERCENT.getValue(), this.setMeasureObj(airLy, AirLyParser.AirLyValues.PM_10_PERCENT)))
                .put("sun", new JSONObject()
                        .put(SunRiseSetParser.SunValues.SUN_RISE.getValue(), this.setMeasureObj(sunRiseSet, SunRiseSetParser.SunValues.SUN_RISE))
                        .put(SunRiseSetParser.SunValues.SUN_SET.getValue(), this.setMeasureObj(sunRiseSet, SunRiseSetParser.SunValues.SUN_SET))
                        .put(SunRiseSetParser.SunValues.DAY_LENGTH.getValue(), this.setMeasureObj(sunRiseSet, SunRiseSetParser.SunValues.DAY_LENGTH)));
        return response.toString();
    }

    private JSONObject setMeasureObj(HandlerInterface connector, Values value) {
        Response response = connector.getResponse();
        JSONObject obj = new JSONObject()
                .put("value", connector.getResponseValue(value.getValue()))
                .put("isError", response.isError())
                .put("date", response.getDate());
        return obj;
    }
}
