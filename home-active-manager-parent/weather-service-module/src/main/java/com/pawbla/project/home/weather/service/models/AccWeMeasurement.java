package com.pawbla.project.home.weather.service.models;

import static com.pawbla.project.home.weather.service.utils.Constants.EMPTY;

public class AccWeMeasurement extends Measurement {

    private String weatherText = EMPTY;
    private String weatherIcon = EMPTY;
    private String windDirection = EMPTY;
    private String windDirectionDeg = EMPTY;
    private String windSpeed = EMPTY;
    private String uvIndexValue = EMPTY;
    private String uvIndexDescription = EMPTY;
    private String uvIndexColor = EMPTY;
    private String visibility = EMPTY;
    private String cloudCover = EMPTY;
    private String ceiling = EMPTY;

    public void setMeasurements(String weatherText, String weatherIcon, String windDirection,
                            String windDirectionDeg, String windSpeed, String uvIndexValue, String uvIndexDescription,
                            String uvIndexColor, String visibility, String cloudCover, String ceiling) {
        this.weatherText = weatherText;
        this.weatherIcon = weatherIcon;
        this.windDirection = windDirection;
        this.windDirectionDeg = windDirectionDeg;
        this.windSpeed = windSpeed;
        this.uvIndexValue = uvIndexValue;
        this.uvIndexDescription = uvIndexDescription;
        this.uvIndexColor = uvIndexColor;
        this.visibility = visibility;
        this.cloudCover = cloudCover;
        this.ceiling = ceiling;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getWindDirectionDeg() {
        return windDirectionDeg;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getUvIndexValue() {
        return uvIndexValue;
    }

    public String getUvIndexDescription() {
        return uvIndexDescription;
    }

    public String getUvIndexColor() {
        return uvIndexColor;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public String getCeiling() {
        return ceiling;
    }


}
