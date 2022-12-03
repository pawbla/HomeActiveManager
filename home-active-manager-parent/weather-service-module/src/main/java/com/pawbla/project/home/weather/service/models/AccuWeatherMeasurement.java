package com.pawbla.project.home.weather.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pawbla.project.home.weather.service.models.accuweather.Ceiling;
import com.pawbla.project.home.weather.service.models.accuweather.Visibility;
import com.pawbla.project.home.weather.service.models.accuweather.Wind;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccuWeatherMeasurement extends Measurement {
    @JsonProperty("WeatherText")
    private String weatherText;
    @JsonProperty("WeatherIcon")
    private String weatherIcon;
    @JsonProperty("EpochTime")
    private String epochTime;
    @JsonProperty("UVIndex")
    private String uvIndexValue;
    @JsonProperty("UVIndexText")
    private String uvIndexText;
    @JsonProperty("CloudCover")
    private String cloudCover;
    @JsonProperty("IsDayTime")
    private boolean isDayTime;
    @JsonProperty("HasPrecipitation")
    private boolean isPrecipitation;
    @JsonProperty("PrecipitationType")
    private String precipitationType;
    @JsonProperty("Wind")
    private Wind wind;
    @JsonProperty("Visibility")
    private Visibility visibility;
    @JsonProperty("Ceiling")
    private Ceiling ceiling;

    public String getWeatherText() {
        return weatherText;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public String getEpochTime() {
        return epochTime;
    }

    public String getUvIndexValue() {
        return uvIndexValue;
    }

    public String getUvIndexText() {
        return uvIndexText;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public boolean isDayTime() {
        return isDayTime;
    }

    public boolean isPrecipitation() {
        return isPrecipitation;
    }

    public String getPrecipitationType() {
        return precipitationType;
    }

    public Wind getWind() {
        return wind;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public Ceiling getCeiling() {
        return ceiling;
    }
}
