package com.pawbla.project.home.weather.service.controllers.parsers;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.models.AccuWeatherMeasurement;
import com.pawbla.project.home.weather.service.models.AirLyMeasurement;
import com.pawbla.project.home.weather.service.models.accuweather.Wind;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component("weather")
public class WeatherParser extends AbstractParser<AccuWeatherMeasurement> {

    private static final String WEATHER_TEXT = "weatherText";
    private static final String WEATHER_ICON = "weatherIcon";
    private static final String WIND_DIRECTION = "windDirection";
    private static final String WIND_DIRECTION_DEG = "windDirectionDeg";
    private static final String WIND_SPEED = "windSpeed";
    private static final String UV_INDEX_VALUE = "uvIndexValue";
    private static final String UV_INDEX_DESCRIPTION = "uvIndexDescription";
    private static final String UV_INDEX_COLOR = "uvIndexColor";
    private static final String VISIBILITY = "visibility";
    private static final String CLOUD_COVER = "cloudCover";
    private static final String CEILING = "ceiling";
    private static final String IS_PRECIPITATION = "isPrecipitation";
    private static final String PRECIPITATION_TYPE = "precipitationType";
    private static final String IS_DAY_TIME = "isDayTime";
    private static final String PRESSURE = "pressure";
    private static final String VIOLET_HEX = "#B803FF";

    private static final String PRESSURE_OBJ_NAME = "PRESSURE";

    private final HandlerInterface accuWeather;
    private final HandlerInterface airLy;

    public WeatherParser(@Qualifier("accuWeather") HandlerInterface accuWeather, @Qualifier("AirLy") HandlerInterface airLy) {
        this.accuWeather = accuWeather;
        this.airLy = airLy;
    }

    @Override
    public JSONObject getParsedObject() {
        AccuWeatherMeasurement accuWeatherMeasurement = getMeasurement(accuWeather);
        AirLyMeasurement airLyMeasurement = (AirLyMeasurement) airLy.getMeasurement();
        return new JSONObject()
                .put(PRESSURE, getRoundedValue(getValueByName(airLyMeasurement.getCurrent().getValues(), PRESSURE_OBJ_NAME),
                        airLyMeasurement.isError()))
                .put(WEATHER_ICON, getValue(accuWeatherMeasurement.getWeatherIcon(), accuWeatherMeasurement.isError()))
                .put(WEATHER_TEXT, getValue(accuWeatherMeasurement.getWeatherText(), accuWeatherMeasurement.isError()))
                .put(CLOUD_COVER, getValue(accuWeatherMeasurement.getCloudCover(), accuWeatherMeasurement.isError()))
                .put(CEILING, getRoundedValue(accuWeatherMeasurement.getCeiling().getMetric().getValue(), accuWeatherMeasurement.isError()))
                .put(VISIBILITY, getRoundedValue(accuWeatherMeasurement.getVisibility().getMetric().getValue(), accuWeatherMeasurement.isError()))
                .put(WIND_DIRECTION, getValue(getWind(accuWeatherMeasurement).getDirection().getWindDirection(), accuWeatherMeasurement.isError()))
                .put(WIND_DIRECTION_DEG, getValue(getWind(accuWeatherMeasurement).getDirection().getWindDirectionDeg(), accuWeatherMeasurement.isError()))
                .put(WIND_SPEED, getRoundedValue(getWind(accuWeatherMeasurement).getSpeed().getMetric().getValue(), accuWeatherMeasurement.isError()))
                .put(UV_INDEX_DESCRIPTION, getValue(accuWeatherMeasurement.getUvIndexText(), accuWeatherMeasurement.isError()))
                .put(UV_INDEX_VALUE, getValue(accuWeatherMeasurement.getUvIndexValue(), accuWeatherMeasurement.isError()))
                .put(IS_PRECIPITATION, getValue(accuWeatherMeasurement.isPrecipitation(), accuWeatherMeasurement.isError()))
                .put(PRECIPITATION_TYPE, getValue(accuWeatherMeasurement.getPrecipitationType(), accuWeatherMeasurement.isError()))
                .put(IS_DAY_TIME, getValue(accuWeatherMeasurement.isDayTime(), accuWeatherMeasurement.isError()))
                .put(UV_INDEX_COLOR, getValue(getUvIndexColor(accuWeatherMeasurement.getUvIndexValue()), accuWeatherMeasurement.isError()));
    }

    private Wind getWind(AccuWeatherMeasurement accuWeatherMeasurement) {
        return accuWeatherMeasurement.getWind();
    }

    private String getUvIndexColor(String uvIndexValue) {
        int uvIndexValueInt = Integer.parseInt(uvIndexValue);
        Color color = Color.GREEN;
        if (uvIndexValueInt >= 3 && uvIndexValueInt <= 5) {
            color = Color.decode("#e0d900");
        } else if (uvIndexValueInt >= 6 && uvIndexValueInt <= 7 ) {
            color = Color.ORANGE;
        } else if (uvIndexValueInt >= 8 && uvIndexValueInt <= 10 ) {
            color = Color.RED;
        } else if (uvIndexValueInt >= 11 ) {
            color = Color.decode(VIOLET_HEX);
        }
        return "#"+Integer.toHexString(color.getRGB()).substring(2);
    }
}

/*
.put(AirLyParser.AirLyValues.PRESSURE.getValue(),
                                this.setMeasureObj(getAirLyMeasurement(), getAirLyMeasurement().getPressure()))
                        .put(AirLyParser.AirLyValues.HISTORY.getValue(), prepareAirLyHistory(getAirLyMeasurement()))
 */
