package com.pawbla.project.home.weather.service.models;

public class SunRiseSetMeasurement extends Measurement {
    private String SunRise;
    private String SunSet;
    private String DayLength;

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
