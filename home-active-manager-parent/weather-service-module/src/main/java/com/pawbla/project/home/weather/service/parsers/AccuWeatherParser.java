package com.pawbla.project.home.weather.service.parsers;

import com.pawbla.project.home.weather.service.models.AccWeMeasurement;
import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@Qualifier("accuWeather")
public class AccuWeatherParser extends AbstractParser {

    /**
     * Logger
     */
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private final AccWeMeasurement accWeMeasurement;

    public AccuWeatherParser() {
        this.accWeMeasurement = new AccWeMeasurement();
    }

    public enum AccuWeatherValues implements Values {

        WEATHER_TEXT("weatherText"),
        WEATHER_ICON("weatherIcon"),
        WIND_DIRECTION("windDirection"),
        WIND_DIRECTION_DEG("windDirectionDeg"),
        WIND_SPEED("windSpeed"),
        UV_INDEX_VALUE("uvIndexValue"),
        UV_INDEX_DESCRIPTION("uvIndexDescription"),
        UV_INDEX_COLOR("uvIndexColor"),
        VISIBILITY("visibility"),
        CLOUD_COVER("cloudCover"),
        CEILING("ceiling");

        public final String value;

        private AccuWeatherValues(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * Constants
     */
    private static final String WEATHER_TEXT_KEY = "WeatherText";
    private static final String WEATHER_ICON_KEY = "WeatherIcon";
    private static final String WIND_KEY = "Wind";
    private static final String WIND_DIRECTION_KEY = "Direction";
    private static final String WIND_LOCALIZED_KEY = "Localized";
    private static final String WIND_DEGREE_KEY = "Degrees";
    private static final String WIND_SPEED_KEY = "Speed";
    private static final String METRIC_KEY = "Metric";
    private static final String VALUE_KEY = "Value";
    private static final String UV_INDEX_KEY = "UVIndex";
    private static final String UV_INDEX_TEXT_KEY = "UVIndexText";
    private static final String VISIBILITY_KEY = "Visibility";
    private static final String CLOUD_COVER_KEY = "CloudCover";
    private static final String CEILING_KEY = "Ceiling";
    private static final String VIOLET_HEX = "#B803FF";

    @Override
    public void parse (Response response) throws JSONException {
        try {
            JSONObject mainObj = (JSONObject) new JSONArray(response.getBody()).get(0);
            /* General */
            accWeMeasurement.setWeatherText(mainObj.getString(WEATHER_TEXT_KEY));
            accWeMeasurement.setWeatherIcon(Integer.toString(mainObj.getInt(WEATHER_ICON_KEY)));

            /* Wind */
            JSONObject wind = mainObj.getJSONObject(WIND_KEY);
            JSONObject direction = wind.getJSONObject(WIND_DIRECTION_KEY);
            accWeMeasurement.setWindDirection(direction.getString(WIND_LOCALIZED_KEY));
            accWeMeasurement.setWindDirectionDeg(Integer.toString(direction.getInt(WIND_DEGREE_KEY)));
            accWeMeasurement.setWindSpeed(getRoundedDouble(wind.getJSONObject(WIND_SPEED_KEY).getJSONObject(METRIC_KEY).getDouble(VALUE_KEY)));
            /* UV indexes and visibility */
            accWeMeasurement.setUvIndexValue(Integer.toString(mainObj.getInt(UV_INDEX_KEY)));
            accWeMeasurement.setUvIndexDescription(mainObj.getString(UV_INDEX_TEXT_KEY));
            accWeMeasurement.setUvIndexColor(this.getUvIndexColor(mainObj.getInt(UV_INDEX_KEY)));
            accWeMeasurement.setVisibility(getRoundedDouble(mainObj.getJSONObject(VISIBILITY_KEY).getJSONObject(METRIC_KEY).getDouble(VALUE_KEY)));
            /* Cloud cover and ceiling */
            accWeMeasurement.setCloudCover(Integer.toString(mainObj.getInt(CLOUD_COVER_KEY)));
            accWeMeasurement.setCeiling(Integer.toString(mainObj.getJSONObject(CEILING_KEY).getJSONObject(METRIC_KEY).getInt(VALUE_KEY)));
        } catch (JSONException e) {
            logger.error("An error has occured during JSON conversion" + e.getMessage());
        }
    }

    @Override
    public Measurement getParsedAsObject() {
        return accWeMeasurement;
    }

    private String getUvIndexColor(int uvIndexValue) {
        Color color = Color.GREEN;
        if (uvIndexValue >= 3 && uvIndexValue <= 5) {
            color = Color.decode("#e0d900");
        } else if (uvIndexValue >= 6 && uvIndexValue <= 7 ) {
            color = Color.ORANGE;
        } else if (uvIndexValue >= 8 && uvIndexValue <= 10 ) {
            color = Color.RED;
        } else if (uvIndexValue >= 11 ) {
            color = Color.decode(VIOLET_HEX);
        }
        return "#"+Integer.toHexString(color.getRGB()).substring(2);
    }


}