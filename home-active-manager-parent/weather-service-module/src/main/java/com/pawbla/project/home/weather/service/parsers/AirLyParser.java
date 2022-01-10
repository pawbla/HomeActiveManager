package com.pawbla.project.home.weather.service.parsers;

import com.pawbla.project.home.weather.service.models.AirPollutionForecast;
import com.pawbla.project.home.weather.service.models.AirlyMeasurement;
import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
@Qualifier("AirLy")
public class AirLyParser extends AbstractParser {

    /**
     * Logger
     */
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private final AirlyMeasurement airlyMeasurement;

    public AirLyParser() {
        this.airlyMeasurement = new AirlyMeasurement();
    }

    public enum AirLyValues implements Values {

        TEMPERATURE("temperature"),
        HUMIDITY("humidity"),
        PRESSURE("pressure"),
        PM_1("pm1"),
        PM_10("pm10"),
        PM_25("pm25"),
        CAQI("caqi"),
        CAQI_COLOR("caqiColor"),
        PM_10_PERCENT("pm10percent"),
        PM_25_PERCENT("pm25percent"),
        DATE_TIME("dateTime");

        public final String value;

        private AirLyValues(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * Constants
     */
    private static final String CURRENT_KEY = "current";
    private static final String VALUES_KEY = "values";
    private static final String INDEXES_KEY = "indexes";
    private static final String STANDARDS_KEY = "standards";
    private static final String FORECAST_KEY = "forecast";
    private static final String VALUE_KEY = "value";
    private static final String COLOR_KEY = "color";
    private static final String PERCENT_KEY = "percent";
    private static final int CAQI_POS = 0;
    private static final int PM1_POS = 0;
    private static final int PM25_PERCENT_POS = 0;
    private static final int PM10_PERCENT_POS = 1;
    private static final int PM25_POS = 1;
    private static final int PM10_POS = 2;
    private static final int PRESSURE_POS = 3;
    private static final int HUMIDITY_POS = 4;
    private static final int TEMPERATURE_POS = 5;

    @Override
    public Measurement parse (Response response) throws JSONException {
        try {
            if (!response.isError()) {
                JSONObject jsonObject = new JSONObject(response.getBody()).getJSONObject(CURRENT_KEY);
                JSONArray values = jsonObject.getJSONArray(VALUES_KEY);
                JSONArray indexes = jsonObject.getJSONArray(INDEXES_KEY);
                JSONArray standards = jsonObject.getJSONArray(STANDARDS_KEY);
                JSONArray forecast = new JSONObject(response.getBody()).getJSONArray(FORECAST_KEY);
                String pm1 = getRoundedDouble(values.getJSONObject(PM1_POS).getDouble(VALUE_KEY));
                String pm10 = getRoundedDouble(values.getJSONObject(PM10_POS).getDouble(VALUE_KEY));
                String pm25 = getRoundedDouble(values.getJSONObject(PM25_POS).getDouble(VALUE_KEY));
                String caqi = getRoundedDouble(indexes.getJSONObject(CAQI_POS).getDouble(VALUE_KEY));
                String caqiColor = indexes.getJSONObject(CAQI_POS).getString(COLOR_KEY);
                String pm10perc = getRoundedDouble(standards.getJSONObject(PM10_PERCENT_POS).getDouble(PERCENT_KEY));
                String pm25per = getRoundedDouble(standards.getJSONObject(PM25_PERCENT_POS).getDouble(PERCENT_KEY));
                String humidity = getRoundedDouble(values.getJSONObject(HUMIDITY_POS).getDouble(VALUE_KEY));
                String pressure = getRoundedDouble(values.getJSONObject(PRESSURE_POS).getDouble(VALUE_KEY));
                String temperature = getRoundedDouble(values.getJSONObject(TEMPERATURE_POS).getDouble(VALUE_KEY));
                List<AirPollutionForecast> airPollutionForecasts = prepareAirPollutionForecastList(forecast);

                airlyMeasurement.setMeasurements(temperature, humidity, pressure, pm1, pm10, pm25, caqi, caqiColor,
                        pm10perc, pm25per, airPollutionForecasts);
                airlyMeasurement.setDate(response.getDate());
            }
        } catch (JSONException e) {
            logger.error("An error has occured during JSON conversion" + e.getMessage());
        } finally {
            airlyMeasurement.setError(response.isError());
        }
        return airlyMeasurement;
    }

    private List<AirPollutionForecast> prepareAirPollutionForecastList(JSONArray forecast) {
        List<AirPollutionForecast> airPollutionForecastList = new ArrayList<>();
        forecast.forEach(item -> {
            airPollutionForecastList.add(new AirPollutionForecast(getDate((JSONObject) item) , getCaqi((JSONObject) item),
                    getCaqiColour((JSONObject) item)));
        });
        return airPollutionForecastList;
    }

    private String getDate(JSONObject item) {
        return item.getString("tillDateTime");
    }

    private String getCaqi(JSONObject item) {
        return getRoundedDouble(item.getJSONArray(INDEXES_KEY).getJSONObject(0).getDouble(VALUE_KEY));
    }

    private String getCaqiColour(JSONObject item) {
        return item.getJSONArray(INDEXES_KEY).getJSONObject(0).getString(COLOR_KEY);
    }
}