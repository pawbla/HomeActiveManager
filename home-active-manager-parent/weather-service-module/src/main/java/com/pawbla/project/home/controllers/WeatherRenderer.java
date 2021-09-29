package com.pawbla.project.home.controllers;

import com.pawbla.project.home.handlers.HandlerInterface;
import com.pawbla.project.home.models.Response;
import com.pawbla.project.home.parsers.AccuWeatherParser.AccuWeatherValues;
import com.pawbla.project.home.parsers.AirLyParser.AirLyValues;
import com.pawbla.project.home.parsers.InternalParser.InternalValues;
import com.pawbla.project.home.parsers.SunRiseSetParser.SunValues;
import com.pawbla.project.home.parsers.Values;
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
                        .put(InternalValues.TEMPERATURE.getValue(),  this.setMeasureObj(internal, InternalValues.TEMPERATURE))
                        .put(InternalValues.HUMIDITY.getValue(), this.setMeasureObj(internal, InternalValues.HUMIDITY)))
                .put("out", new JSONObject()
                        .put(AirLyValues.TEMPERATURE.getValue(), this.setMeasureObj(airLy, AirLyValues.TEMPERATURE))
                        .put(AirLyValues.HUMIDITY.getValue(), this.setMeasureObj(airLy, AirLyValues.HUMIDITY)))
                .put("weather", new JSONObject()
                        .put(AirLyValues.PRESSURE.getValue(), this.setMeasureObj(airLy, AirLyValues.PRESSURE))
                        .put(AccuWeatherValues.WEATHER_ICON.getValue(), this.setMeasureObj(accuWeather, AccuWeatherValues.WEATHER_ICON))
                        .put(AccuWeatherValues.WEATHER_TEXT.getValue(), this.setMeasureObj(accuWeather, AccuWeatherValues.WEATHER_TEXT))
                        .put(AccuWeatherValues.CLOUD_COVER.getValue(), this.setMeasureObj(accuWeather, AccuWeatherValues.CLOUD_COVER))
                        .put(AccuWeatherValues.CEILING.getValue(), this.setMeasureObj(accuWeather, AccuWeatherValues.CEILING))
                        .put(AccuWeatherValues.VISIBILITY.getValue(), this.setMeasureObj(accuWeather, AccuWeatherValues.VISIBILITY))
                        .put(AccuWeatherValues.WIND_DIRECTION.getValue(), this.setMeasureObj(accuWeather, AccuWeatherValues.WIND_DIRECTION))
                        .put(AccuWeatherValues.WIND_DIRECTION_DEG.getValue(), this.setMeasureObj(accuWeather, AccuWeatherValues.WIND_DIRECTION_DEG))
                        .put(AccuWeatherValues.WIND_SPEED.getValue(), this.setMeasureObj(accuWeather, AccuWeatherValues.WIND_SPEED))
                        .put(AccuWeatherValues.UV_INDEX_DESCRIPTION.getValue(), this.setMeasureObj(accuWeather, AccuWeatherValues.UV_INDEX_DESCRIPTION))
                        .put(AccuWeatherValues.UV_INDEX_VALUE.getValue(), this.setMeasureObj(accuWeather, AccuWeatherValues.UV_INDEX_VALUE))
                        .put(AccuWeatherValues.UV_INDEX_COLOR.getValue(), this.setMeasureObj(accuWeather, AccuWeatherValues.UV_INDEX_COLOR)))
                .put("airPolution", new JSONObject()
                        .put(AirLyValues.CAQI.getValue(), this.setMeasureObj(airLy, AirLyValues.CAQI))
                        .put(AirLyValues.CAQI_COLOR.getValue(), this.setMeasureObj(airLy, AirLyValues.CAQI_COLOR))
                        .put(AirLyValues.PM_1.getValue(), this.setMeasureObj(airLy, AirLyValues.PM_1))
                        .put(AirLyValues.PM_10.getValue(), this.setMeasureObj(airLy, AirLyValues.PM_10))
                        .put(AirLyValues.PM_10_PERCENT.getValue(), this.setMeasureObj(airLy, AirLyValues.PM_10_PERCENT))
                        .put(AirLyValues.PM_25.getValue(), this.setMeasureObj(airLy, AirLyValues.PM_25))
                        .put(AirLyValues.PM_25_PERCENT.getValue(), this.setMeasureObj(airLy, AirLyValues.PM_10_PERCENT)))
                .put("sun", new JSONObject()
                        .put(SunValues.SUN_RISE.getValue(), this.setMeasureObj(sunRiseSet, SunValues.SUN_RISE))
                        .put(SunValues.SUN_SET.getValue(), this.setMeasureObj(sunRiseSet, SunValues.SUN_SET))
                        .put(SunValues.DAY_LENGTH.getValue(), this.setMeasureObj(sunRiseSet, SunValues.DAY_LENGTH)));
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
