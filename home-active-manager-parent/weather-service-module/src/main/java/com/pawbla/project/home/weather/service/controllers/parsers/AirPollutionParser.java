package com.pawbla.project.home.weather.service.controllers.parsers;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.models.AirLyMeasurement;
import com.pawbla.project.home.weather.service.models.airly.Index;
import com.pawbla.project.home.weather.service.models.airly.Item;
import com.pawbla.project.home.weather.service.models.airly.Standard;
import com.pawbla.project.home.weather.service.models.airly.Value;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("airpollution")
public class AirPollutionParser extends AbstractParser<AirLyMeasurement> {

    private static final String CAQI = "caqi";
    private static final String CAQI_COLOR = "caqiColor";
    private static final String FORECAST = "forecast";
    private static final String PM_1 = "pm1";
    private static final String PM_10 = "pm10";
    private static final String PM_10_PERCENT = "pm10percent";
    private static final String PM_25 = "pm25";
    private static final String PM_25_PERCENT = "pm25percent";

    private static final String PM1_OBJ_NAME = "PM1";
    private static final String PM10_OBJ_NAME = "PM10";
    private static final String PM25_OBJ_NAME = "PM25";

    private final HandlerInterface airLy;

    public AirPollutionParser(@Qualifier("AirLy") HandlerInterface airLy) {
        this.airLy = airLy;
    }

    @Override
    public JSONObject getParsedObject() {
        AirLyMeasurement measurement = getMeasurement(airLy);
        List<Value> valueList = getAirLyValueList(measurement);
        List<Standard> standardList = getAirLyStandardList(measurement);
        Index index = getAirLyIndexValue(measurement);
        return new JSONObject()
                .put(CAQI, getRoundedValue(index.getValue(), measurement.isError()))
                .put(CAQI_COLOR, getValue(index.getColor(), measurement.isError()))
                .put(PM_1, getRoundedValue(getValueByName(valueList, PM1_OBJ_NAME),
                        measurement.isError()))
                .put(PM_10, getRoundedValue(getValueByName(valueList, PM10_OBJ_NAME),
                        measurement.isError()))
                .put(PM_10_PERCENT, getRoundedValue(getPollutionByName(standardList, PM10_OBJ_NAME),
                        measurement.isError()))
                .put(PM_25, getRoundedValue(getValueByName(valueList, PM25_OBJ_NAME),
                        measurement.isError()))
                .put(PM_25_PERCENT, getRoundedValue(getPollutionByName(standardList, PM25_OBJ_NAME),
                        measurement.isError()))
                .put(FORECAST, prepareAirPollutionForecast(measurement));
    }

    private List<Value> getAirLyValueList(AirLyMeasurement measurement) {
        return measurement.getCurrent().getValues();
    }

    private List<Standard> getAirLyStandardList(AirLyMeasurement measurement) {
        return measurement.getCurrent().getStandards();
    }

    private Index getAirLyIndexValue(AirLyMeasurement measurement) {
        return measurement.getCurrent().getIndexes().get(0);
    }

    private double getPollutionByName(final List<Standard> standardList, String name) {
        return standardList.stream().filter(v -> v.getPollutant().equals(name)).map(Standard::getPercent).findFirst().orElse(DEFAULT_VALUE);
    }

    private JSONObject prepareAirPollutionForecast(AirLyMeasurement measurement) {
        return new JSONObject()
                .put("isError", measurement.isError())
                .put("values", prepareAirPollutionForecastArr(measurement.getForecast()));
    }

    private JSONArray prepareAirPollutionForecastArr(List<Item> items) {
        JSONArray airPollutionForecast = new JSONArray();
        items.stream().forEach(item -> airPollutionForecast.put(prepareForecastObj(item)));
        return airPollutionForecast;
    }

    private JSONObject prepareForecastObj(Item item) {
        Index index = item.getIndexes().get(0);
        return new JSONObject()
                .put("date", item.getToDateTime())
                .put("caqi", roundDouble(index.getValue()))
                .put("caqiColour", index.getColor());
    }
}
