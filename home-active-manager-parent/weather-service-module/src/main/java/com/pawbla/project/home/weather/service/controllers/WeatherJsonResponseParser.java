package com.pawbla.project.home.weather.service.controllers;

import com.pawbla.project.home.weather.service.controllers.parsers.ResponseParser;
import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.old.AirLyHistory;
import com.pawbla.project.home.weather.service.models.old.AirPollutionForecast;
import com.pawbla.project.home.weather.service.models.old.AirlyMeasurement;
import com.pawbla.project.home.weather.service.models.old.MoonPhaseMeasurement;
import com.pawbla.project.home.weather.service.parsers.old.AirLyParser;
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
    private ResponseParser internalParser;
    private ResponseParser weatherParser;

    //TODO OLD
    private HandlerInterface airLy;
    private HandlerInterface moonPhase;

    @Autowired
    public WeatherJsonResponseParser(@Qualifier("sunRiseSetParser") ResponseParser sunRiseSetParser,
                                     @Qualifier("internalParser") ResponseParser internalParser,
                                     @Qualifier("AirLy") HandlerInterface airLy,
                                     @Qualifier("weather") ResponseParser weatherParser,
                                     @Qualifier("moonPhase") HandlerInterface moonPhase) {
        this.airLy = airLy;
        this.weatherParser = weatherParser;
        this.moonPhase = moonPhase;
        this.sunRiseSetParser = sunRiseSetParser;
        this.internalParser = internalParser;
    }

    @Override
    public String getJSON() {
        JSONObject response = new JSONObject()
                .put("in", internalParser.getParsedObject())
                .put("out", new JSONObject()
                        .put(AirLyParser.AirLyValues.TEMPERATURE.getValue(),
                                this.setMeasureObj(getAirLyMeasurement(), getAirLyMeasurement().getTemperature()))
                        .put(AirLyParser.AirLyValues.HUMIDITY.getValue(),
                                this.setMeasureObj(getAirLyMeasurement(), getAirLyMeasurement().getHumidity())))
                .put("weather", weatherParser.getParsedObject())
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
