package com.pawbla.project.home.weather.service.controllers;

import com.pawbla.project.home.weather.service.controllers.parsers.ResponseParser;
import com.pawbla.project.home.weather.service.models.old.AccWeMeasurement;
import com.pawbla.project.home.weather.service.models.old.AirLyHistory;
import com.pawbla.project.home.weather.service.models.old.AirPollutionForecast;
import com.pawbla.project.home.weather.service.models.old.AirlyMeasurement;
import com.pawbla.project.home.weather.service.models.old.InternalMeasurement;
import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.old.MoonPhaseMeasurement;
import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.parsers.old.AccuWeatherParser;
import com.pawbla.project.home.weather.service.parsers.old.AirLyParser;
import com.pawbla.project.home.weather.service.parsers.old.InternalParser;
import com.pawbla.project.home.weather.service.parsers.old.MoonPhaseParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class WeatherJsonResponseParser implements Renderer {
    private ResponseParser sunRiseSetParser;

    //TODO OLD
    private HandlerInterface internal;
    private HandlerInterface airLy;
    private HandlerInterface accuWeather;
    private HandlerInterface moonPhase;

    @Autowired
    public WeatherJsonResponseParser(@Qualifier("sunRiseSetParser") ResponseParser sunRiseSetParser, @Qualifier("internal") HandlerInterface internal,
                                     @Qualifier("AirLy") HandlerInterface airLy,
                                     @Qualifier("accuWeather") HandlerInterface accuWeather, @Qualifier("moonPhase") HandlerInterface moonPhase) {
        this.internal = internal;
        this.airLy = airLy;
        this.accuWeather = accuWeather;
        this.moonPhase = moonPhase;
        this.sunRiseSetParser = sunRiseSetParser;
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
                        .put(AccuWeatherParser.AccuWeatherValues.IS_PRECIPATION.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().isPrecipation()))
                        .put(AccuWeatherParser.AccuWeatherValues.PRECIPATION_TYPE.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().getPrecipationType()))
                        .put(AccuWeatherParser.AccuWeatherValues.IS_DAY_TIME.getValue(),
                                this.setMeasureObj(getAccuWeatherMeasurement(), this.getAccuWeatherMeasurement().isDayTime()))
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
                .put("sun", sunRiseSetParser.getParsedObject())
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

    private JSONObject setMeasureObj(Measurement measurement, boolean value) {
        return new JSONObject()
                .put("value", value)
                .put("isError", measurement.isError());
    }

    private AirlyMeasurement getAirLyMeasurement() {
        return (AirlyMeasurement) airLy.getMeasurement();
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
                .put("values", airPollutionForecastArr(measurement.getAirPollutionForecast()));
    }

    private JSONObject prepareAirLyHistory(AirlyMeasurement measurement) {
        return new JSONObject()
                .put("isError", measurement.isError())
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
