package com.pawbla.project.home.weather.service.models;

public class AirPollutionForecast {
    private String date;
    private String caqi;
    private String caqiColour;

    public AirPollutionForecast(String date, String caqi, String caqiColour) {
        this.date = date;
        this.caqi = caqi;
        this.caqiColour = caqiColour;
    }

    public String getDate() {
        return date;
    }

    public String getCaqi() {
        return caqi;
    }

    public String getCaqiColour() {
        return caqiColour;
    }
}
