package com.pawbla.project.home.weather.service.models;

import static com.pawbla.project.home.weather.service.utils.Constants.EMPTY;

public class SunRiseSetMeasurement extends Measurement {
    private String SunRise = EMPTY;
    private String SunSet = EMPTY;
    private String DayLength = EMPTY;

    public void setMeasurements(String sunRise, String sunSet, String dayLength) {
        SunRise = sunRise;
        SunSet = sunSet;
        DayLength = dayLength;
    }

    public String getSunRise() {
        return SunRise;
    }

    public String getSunSet() {
        return SunSet;
    }

    public String getDayLength() {
        return DayLength;
    }


}
