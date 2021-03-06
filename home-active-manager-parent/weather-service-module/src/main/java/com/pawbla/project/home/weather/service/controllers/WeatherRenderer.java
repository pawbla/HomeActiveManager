package com.pawbla.project.home.weather.service.controllers;

import com.pawbla.project.home.weather.service.models.*;
import com.pawbla.project.home.weather.service.parsers.*;
import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.parsers.AccuWeatherParser;
import com.pawbla.project.home.weather.service.parsers.AirLyParser;
import com.pawbla.project.home.weather.service.parsers.SunRiseSetParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherRenderer implements Renderer {
    private HandlerInterface internal;
    private HandlerInterface airLy;
    private HandlerInterface sunRiseSet;
    private HandlerInterface accuWeather;
    private HandlerInterface moonPhase;

    @Autowired
    public WeatherRenderer(@Qualifier("internal") HandlerInterface internal,
                           @Qualifier("AirLy") HandlerInterface airLy, @Qualifier("sunRiseSet") HandlerInterface sunRiseSet,
                           @Qualifier("accuWeather") HandlerInterface accuWeather, @Qualifier("moonPhase") HandlerInterface moonPhase) {
        this.internal = internal;
        this.airLy = airLy;
        this.sunRiseSet = sunRiseSet;
        this.accuWeather = accuWeather;
        this.moonPhase = moonPhase;
    }

    @Override
    public String getJSON() {
        JSONObject response = new JSONObject()
                .put("in", new JSONObject()
                        .put(InternalParser.InternalValues.TEMPERATURE.getValue(),
                                this.setMeasureObj(getInternalMeasurement(), getInternalMeasurement().getTemperature()))
                        .put(InternalParser.InternalValues.HUMIDITY.getValue(),
                                this.setMeasureObj(getInternalMeasurement(), getInternalMeasurement().getHumidity())))
                .put("out", new JSONObject()
                        .put(AirLyParser.AirLyValues.TEMPERATURE.getValue(),
                                this.setMeasureObj(getAirLyMeasurement(), getAirLyMeasurement().getTemperature()))
                        .put(AirLyParser.AirLyValues.HUMIDITY.getValue(),
                                this.setMeasureObj(getAirLyMeasurement(), getAirLyMeasurement().getHumidity())))
                .put("weather", new JSONObject()
                        .put(AirLyParser.AirLyValues.PRESSURE.getValue(),
                                this.setMeasureObj(getAirLyMeasurement(), getAirLyMeasurement().getPressure()))
                        .put(AccuWeatherParser.AccuWeatherValues.WEATHER_ICON.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().getWeatherIcon()))
                        .put(AccuWeatherParser.AccuWeatherValues.WEATHER_TEXT.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().getWeatherText()))
                        .put(AccuWeatherParser.AccuWeatherValues.CLOUD_COVER.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().getCloudCover()))
                        .put(AccuWeatherParser.AccuWeatherValues.CEILING.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().getCeiling()))
                        .put(AccuWeatherParser.AccuWeatherValues.VISIBILITY.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().getVisibility()))
                        .put(AccuWeatherParser.AccuWeatherValues.WIND_DIRECTION.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().getWindDirection()))
                        .put(AccuWeatherParser.AccuWeatherValues.WIND_DIRECTION_DEG.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().getWindDirectionDeg()))
                        .put(AccuWeatherParser.AccuWeatherValues.WIND_SPEED.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().getWindSpeed()))
                        .put(AccuWeatherParser.AccuWeatherValues.UV_INDEX_DESCRIPTION.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().getUvIndexDescription()))
                        .put(AccuWeatherParser.AccuWeatherValues.UV_INDEX_VALUE.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().getUvIndexValue()))
                        .put(AccuWeatherParser.AccuWeatherValues.UV_INDEX_COLOR.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().getUvIndexColor())))
                        .put(AirLyParser.AirLyValues.HISTORY.getValue(), prepareAirLyHistory(getAirLyMeasurement()))
                .put("airPolution", new JSONObject()
                        .put(AirLyParser.AirLyValues.CAQI.getValue(),
                                this.setMeasureObj(getAirLyMeasurement(), getAirLyMeasurement().getCaqi()))
                        .put(AirLyParser.AirLyValues.CAQI_COLOR.getValue(),
                                this.setMeasureObj(getAirLyMeasurement(), getAirLyMeasurement().getCaqiColour()))
                        .put(AirLyParser.AirLyValues.PM_1.getValue(),
                                this.setMeasureObj(getAirLyMeasurement(), getAirLyMeasurement().getPm1()))
                        .put(AirLyParser.AirLyValues.PM_10.getValue(),
                                this.setMeasureObj(getAirLyMeasurement(), getAirLyMeasurement().getPm10()))
                        .put(AirLyParser.AirLyValues.PM_10_PERCENT.getValue(),
                                this.setMeasureObj(getAirLyMeasurement(), getAirLyMeasurement().getPm10percent()))
                        .put(AirLyParser.AirLyValues.PM_25.getValue(),
                                this.setMeasureObj(getAirLyMeasurement(), getAirLyMeasurement().getPm25()))
                        .put(AirLyParser.AirLyValues.PM_25_PERCENT.getValue(),
                                this.setMeasureObj(getAirLyMeasurement(), getAirLyMeasurement().getPm25percent()))
                        .put(AirLyParser.AirLyValues.FORECAST.getValue(), prepareAirPollutionForecast(getAirLyMeasurement())))
                .put("sun", new JSONObject()
                        .put(SunRiseSetParser.SunValues.SUN_RISE.getValue(),
                                this.setMeasureObj(getSunMeasurement(), this.getSunMeasurement().getSunRise()))
                        .put(SunRiseSetParser.SunValues.SUN_SET.getValue(),
                                this.setMeasureObj(getSunMeasurement(), this.getSunMeasurement().getSunSet()))
                        .put(SunRiseSetParser.SunValues.DAY_LENGTH.getValue(),
                                this.setMeasureObj(getSunMeasurement(), this.getSunMeasurement().getDayLength())))
                .put("moon", new JSONObject()
                        .put(MoonPhaseParser.MoonPhaseValues.TEXT.getValue(),
                                setMeasureObj(getMoonPhaseMeasurement(), this.getMoonPhaseMeasurement().getText()))
                        .put(MoonPhaseParser.MoonPhaseValues.PICTURE.getValue(),
                                setMeasureObj(getMoonPhaseMeasurement(), this.getMoonPhaseMeasurement().getPictureNo())));
        return response.toString();
    }

    private JSONObject setMeasureObj(Measurement measurement, String value) {
        return new JSONObject()
                .put("value", value)
                .put("isError", measurement.isError())
                .put("date", measurement.getDate());
    }

    private AirlyMeasurement getAirLyMeasurement() {
        return (AirlyMeasurement) airLy.getMeasurement();
    }

    private SunRiseSetMeasurement getSunMeasurement() {
        return (SunRiseSetMeasurement) sunRiseSet.getMeasurement();
    }

    private InternalMeasurement getInternalMeasurement() {
        return (InternalMeasurement) internal.getMeasurement();
    }

    private AccWeMeasurement getAccuWeatherMeasurement() {
        return (AccWeMeasurement) accuWeather.getMeasurement();
    }

    private MoonPhaseMeasurement getMoonPhaseMeasurement() {
        return (MoonPhaseMeasurement) moonPhase.getMeasurement();
    }

    private JSONObject prepareAirPollutionForecast(AirlyMeasurement measurement) {
        return new JSONObject()
                .put("isError", measurement.isError())
                .put("date", measurement.getDate())
                .put("values", airPollutionForecastArr(measurement.getAirPollutionForecast()));
    }

    private JSONObject prepareAirLyHistory(AirlyMeasurement measurement) {
        return new JSONObject()
                .put("isError", measurement.isError())
                .put("date", measurement.getDate())
                .put("pressure", preparePressureHistoryArr(measurement.getAirLyHistory()));
    }

    private JSONArray airPollutionForecastArr(List<AirPollutionForecast> airPollutionForecastList) {
        JSONArray airPollutionForecast = new JSONArray();
        airPollutionForecastList.forEach(item -> airPollutionForecast.put(prepareForecastObj(item)));
        return airPollutionForecast;
    }

    private JSONObject prepareForecastObj(AirPollutionForecast item) {
        return new JSONObject()
                .put("date", item.getDate())
                .put("caqi", item.getCaqi())
                .put("caqiColour", item.getCaqiColour());
    }

    private JSONArray preparePressureHistoryArr(final List<AirLyHistory> airLyHistoryList) {
        JSONArray airLyPressureHistory = new JSONArray();
        airLyHistoryList.forEach(item -> airLyPressureHistory.put(preparePressureHistoryObj(item)));
        return airLyPressureHistory;
    }

    private JSONObject preparePressureHistoryObj(final AirLyHistory item) {
        return new JSONObject()
                .put("date", item.getDate())
                .put("value", item.getPressure());
    }
}
