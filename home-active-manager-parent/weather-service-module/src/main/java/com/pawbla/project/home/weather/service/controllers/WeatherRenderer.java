package com.pawbla.project.home.weather.service.controllers;

import com.pawbla.project.home.weather.service.models.*;
import com.pawbla.project.home.weather.service.parsers.*;
import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.parsers.AccuWeatherParser;
import com.pawbla.project.home.weather.service.parsers.AirLyParser;
import com.pawbla.project.home.weather.service.parsers.SunRiseSetParser;
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
                        .put(InternalParser.InternalValues.TEMPERATURE.getValue(),
                                this.setMeasureObj(internal, getInternalMeasurement().getTemperature()))
                        .put(InternalParser.InternalValues.HUMIDITY.getValue(),
                                this.setMeasureObj(internal, getInternalMeasurement().getHumidity())))
                .put("out", new JSONObject()
                        .put(AirLyParser.AirLyValues.TEMPERATURE.getValue(),
                                this.setMeasureObj(airLy, getAirLyMeasurement().getTemperature()))
                        .put(AirLyParser.AirLyValues.HUMIDITY.getValue(),
                                this.setMeasureObj(airLy, getAirLyMeasurement().getHumidity())))
                .put("weather", new JSONObject()
                        .put(AirLyParser.AirLyValues.PRESSURE.getValue(),
                                this.setMeasureObj(airLy, getAirLyMeasurement().getPressure()))
                        .put(AccuWeatherParser.AccuWeatherValues.WEATHER_ICON.getValue(),
                                this.setMeasureObj(accuWeather, this.getAccuWeatherMeasurement().getWeatherIcon()))
                        .put(AccuWeatherParser.AccuWeatherValues.WEATHER_TEXT.getValue(),
                                this.setMeasureObj(accuWeather, this.getAccuWeatherMeasurement().getWeatherText()))
                        .put(AccuWeatherParser.AccuWeatherValues.CLOUD_COVER.getValue(),
                                this.setMeasureObj(accuWeather, this.getAccuWeatherMeasurement().getCloudCover()))
                        .put(AccuWeatherParser.AccuWeatherValues.CEILING.getValue(),
                                this.setMeasureObj(accuWeather, this.getAccuWeatherMeasurement().getCeiling()))
                        .put(AccuWeatherParser.AccuWeatherValues.VISIBILITY.getValue(),
                                this.setMeasureObj(accuWeather, this.getAccuWeatherMeasurement().getVisibility()))
                        .put(AccuWeatherParser.AccuWeatherValues.WIND_DIRECTION.getValue(),
                                this.setMeasureObj(accuWeather, this.getAccuWeatherMeasurement().getWindDirection()))
                        .put(AccuWeatherParser.AccuWeatherValues.WIND_DIRECTION_DEG.getValue(),
                                this.setMeasureObj(accuWeather, this.getAccuWeatherMeasurement().getWindDirectionDeg()))
                        .put(AccuWeatherParser.AccuWeatherValues.WIND_SPEED.getValue(),
                                this.setMeasureObj(accuWeather, this.getAccuWeatherMeasurement().getWindSpeed()))
                        .put(AccuWeatherParser.AccuWeatherValues.UV_INDEX_DESCRIPTION.getValue(),
                                this.setMeasureObj(accuWeather, this.getAccuWeatherMeasurement().getUvIndexDescription()))
                        .put(AccuWeatherParser.AccuWeatherValues.UV_INDEX_VALUE.getValue(),
                                this.setMeasureObj(accuWeather, this.getAccuWeatherMeasurement().getUvIndexValue()))
                        .put(AccuWeatherParser.AccuWeatherValues.UV_INDEX_COLOR.getValue(),
                                this.setMeasureObj(accuWeather, this.getAccuWeatherMeasurement().getUvIndexColor())))
                .put("airPolution", new JSONObject()
                        .put(AirLyParser.AirLyValues.CAQI.getValue(),
                                this.setMeasureObj(airLy, getAirLyMeasurement().getCaqi()))
                        .put(AirLyParser.AirLyValues.CAQI_COLOR.getValue(),
                                this.setMeasureObj(airLy, getAirLyMeasurement().getCaqiColour()))
                        .put(AirLyParser.AirLyValues.PM_1.getValue(),
                                this.setMeasureObj(airLy, getAirLyMeasurement().getPm1()))
                        .put(AirLyParser.AirLyValues.PM_10.getValue(),
                                this.setMeasureObj(airLy, getAirLyMeasurement().getPm10()))
                        .put(AirLyParser.AirLyValues.PM_10_PERCENT.getValue(),
                                this.setMeasureObj(airLy, getAirLyMeasurement().getPm10percent()))
                        .put(AirLyParser.AirLyValues.PM_25.getValue(),
                                this.setMeasureObj(airLy, getAirLyMeasurement().getPm25()))
                        .put(AirLyParser.AirLyValues.PM_25_PERCENT.getValue(),
                                this.setMeasureObj(airLy, getAirLyMeasurement().getPm25percent())))
                .put("sun", new JSONObject()
                        .put(SunRiseSetParser.SunValues.SUN_RISE.getValue(),
                                this.setMeasureObj(sunRiseSet, this.getSunMeasurement().getSunRise()))
                        .put(SunRiseSetParser.SunValues.SUN_SET.getValue(),
                                this.setMeasureObj(sunRiseSet, this.getSunMeasurement().getSunSet()))
                        .put(SunRiseSetParser.SunValues.DAY_LENGTH.getValue(),
                                this.setMeasureObj(sunRiseSet, this.getSunMeasurement().getDayLength())));
        return response.toString();
    }

    private JSONObject setMeasureObj(HandlerInterface connector, String value) {
        Response response = connector.getResponse();
        return new JSONObject()
                .put("value", value)
                .put("isError", response.isError())
                .put("date", response.getDate());
    }

    private AirlyMeasurement getAirLyMeasurement() {
        return (AirlyMeasurement) airLy.getResponseObject();
    }

    private SunRiseSetMeasurement getSunMeasurement() {
        return (SunRiseSetMeasurement) sunRiseSet.getResponseObject();
    }

    private InternalMeasurement getInternalMeasurement() {
        return (InternalMeasurement) internal.getResponseObject();
    }

    private AccWeMeasurement getAccuWeatherMeasurement() {
        return (AccWeMeasurement) accuWeather.getResponseObject();
    }
}
