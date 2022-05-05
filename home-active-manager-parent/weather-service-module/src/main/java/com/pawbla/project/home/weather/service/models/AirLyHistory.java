package com.pawbla.project.home.weather.service.models;

import static com.pawbla.project.home.weather.service.utils.Constants.EMPTY;

public class AirLyHistory {
    private String pressure = EMPTY;
    private String date = EMPTY;

    public AirLyHistory(String date, String pressure) {
        this.pressure = pressure;
        this.date = date;
    }

    public String getPressure() {
        return pressure;
    }

    public String getDate() {
        return date;
    }
}
