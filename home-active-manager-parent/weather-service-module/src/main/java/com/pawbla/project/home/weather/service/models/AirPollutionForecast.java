package com.pawbla.project.home.weather.service.models;

import static com.pawbla.project.home.weather.service.utils.Constants.EMPTY;

public class AirPollutionForecast {
    private String date = EMPTY;
    private String caqi = EMPTY;
    private String caqiColour = EMPTY;

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
