package com.pawbla.project.home.weather.service.models;

public class AccWeMeasurement implements Measurement {
    private String weatherText;
    private String weatherIcon;
    private String windDirection;
    private String windDirectionDeg;
    private String windSpeed;
    private String uvIndexValue;
    private String uvIndexDescription;
    private String uvIndexColor;
    private String visibility;
    private String cloudCover;

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindDirectionDeg() {
        return windDirectionDeg;
    }

    public void setWindDirectionDeg(String windDirectionDeg) {
        this.windDirectionDeg = windDirectionDeg;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getUvIndexValue() {
        return uvIndexValue;
    }

    public void setUvIndexValue(String uvIndexValue) {
        this.uvIndexValue = uvIndexValue;
    }

    public String getUvIndexDescription() {
        return uvIndexDescription;
    }

    public void setUvIndexDescription(String uvIndexDescription) {
        this.uvIndexDescription = uvIndexDescription;
    }

    public String getUvIndexColor() {
        return uvIndexColor;
    }

    public void setUvIndexColor(String uvIndexColor) {
        this.uvIndexColor = uvIndexColor;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover;
    }

    public String getCeiling() {
        return ceiling;
    }

    public void setCeiling(String ceiling) {
        this.ceiling = ceiling;
    }

    private String ceiling;
}
