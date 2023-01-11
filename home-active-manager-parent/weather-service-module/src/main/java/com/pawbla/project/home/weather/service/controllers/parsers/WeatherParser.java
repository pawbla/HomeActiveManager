package com.pawbla.project.home.weather.service.controllers.parsers;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.models.AccuWeatherMeasurement;
import com.pawbla.project.home.weather.service.models.AirLyMeasurement;
import com.pawbla.project.home.weather.service.models.accuweather.Metric;
import com.pawbla.project.home.weather.service.models.accuweather.Wind;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.awt.*;

import static com.pawbla.project.home.weather.service.utils.Constants.*;

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

    private boolean isErrorAirLy;
    private double pressure;
    private String weatherText;
    private String weatherIcon;
    private String cloudCover;
    private String uvIndexText;
    private String uvIndexValue;
    private boolean isPrecipitation;
    private String precipitationType;
    private boolean isDayTime;
    private String ceiling;
    private String visibility;
    private String windDirection;
    private String windDirectionDeg;
    private String windSpeed;

    public WeatherParser(@Qualifier("accuWeather") HandlerInterface accuWeather, @Qualifier("AirLy") HandlerInterface airLy) {
        this.accuWeather = accuWeather;
        this.airLy = airLy;
        this.isErrorAirLy = true;
        this.pressure = DOUBLE_DEFAULT_VALUE;
        this.weatherText = EMPTY;
        this.weatherIcon = "1";
        this.cloudCover = STRING_DEFAULT_VALUE;
        this.uvIndexText = STRING_DEFAULT_VALUE;
        this.uvIndexValue = STRING_DEFAULT_VALUE;
        this.isPrecipitation = false;
        this.precipitationType = STRING_DEFAULT_VALUE;
        this.isDayTime = false;
        this.ceiling = STRING_DEFAULT_VALUE;
        this.visibility = STRING_DEFAULT_VALUE;
        this.windDirection = STRING_DEFAULT_VALUE;
        this.windDirectionDeg = STRING_DEFAULT_VALUE;
        this.windSpeed = STRING_DEFAULT_VALUE;
    }

    @Override
    protected void parse() {
        AccuWeatherMeasurement accuWeatherMeasurement = getMeasurement(accuWeather);
        AirLyMeasurement airLyMeasurement = (AirLyMeasurement) airLy.getMeasurement();
        isError = accuWeatherMeasurement.isError();
        isErrorAirLy = airLyMeasurement.isError();
        pressure =getValueByName(airLyMeasurement.getCurrent().getValues(), PRESSURE_OBJ_NAME);
        weatherText = accuWeatherMeasurement.getWeatherText();
        weatherIcon = accuWeatherMeasurement.getWeatherIcon();
        cloudCover = accuWeatherMeasurement.getCloudCover();
        uvIndexText = accuWeatherMeasurement.getUvIndexText();
        uvIndexValue = accuWeatherMeasurement.getUvIndexValue();
        isPrecipitation = accuWeatherMeasurement.isPrecipitation();
        precipitationType = accuWeatherMeasurement.getPrecipitationType();
        isDayTime = accuWeatherMeasurement.isDayTime();
        ceiling = accuWeatherMeasurement.getCeiling().getMetric().getValue();
        visibility = accuWeatherMeasurement.getVisibility().getMetric().getValue();
        windDirection = getWind(accuWeatherMeasurement).getDirection().getWindDirection();
        windDirectionDeg = getWind(accuWeatherMeasurement).getDirection().getWindDirectionDeg();
        windSpeed = getWind(accuWeatherMeasurement).getSpeed().getMetric().getValue();
    }

    @Override
    protected JSONObject getParsed() {
        return new JSONObject()
                .put(PRESSURE, getRoundedValue(pressure, isErrorAirLy))
                .put(WEATHER_ICON, getValue(weatherIcon, isError))
                .put(WEATHER_TEXT, getValue(weatherText, isError))
                .put(CLOUD_COVER, getValue(cloudCover, isError))
                .put(CEILING, getRoundedValue(ceiling, isError))
                .put(VISIBILITY, getRoundedValue(visibility, isError))
                .put(WIND_DIRECTION, getValue(windDirection, isError))
                .put(WIND_DIRECTION_DEG, getValue(windDirectionDeg, isError))
                .put(WIND_SPEED, getRoundedValue(windSpeed, isError))
                .put(UV_INDEX_DESCRIPTION, getValue(uvIndexText, isError))
                .put(UV_INDEX_VALUE, getValue(uvIndexValue, isError))
                .put(IS_PRECIPITATION, getValue(isPrecipitation, isError))
                .put(PRECIPITATION_TYPE, getValue(precipitationType, isError))
                .put(IS_DAY_TIME, getValue(isDayTime, isError))
                .put(UV_INDEX_COLOR, getValue(getUvIndexColor(uvIndexValue), isError));
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
        } else {
            color = Color.BLACK; //no matched value
        }
        return "#"+Integer.toHexString(color.getRGB()).substring(2);
    }
}