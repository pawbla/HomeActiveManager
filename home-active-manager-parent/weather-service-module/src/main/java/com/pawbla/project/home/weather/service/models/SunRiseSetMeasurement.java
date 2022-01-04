package com.pawbla.project.home.weather.service.models;

public class SunRiseSetMeasurement implements Measurement {
    private String SunRise;
    private String SunSet;

    public String getSunRise() {
        return SunRise;
    }

    public void setSunRise(String sunRise) {
        SunRise = sunRise;
    }

    public String getSunSet() {
        return SunSet;
    }

    public void setSunSet(String sunSet) {
        SunSet = sunSet;
    }

    public String getDayLength() {
        return DayLength;
    }

    public void setDayLength(String dayLength) {
        DayLength = dayLength;
    }

    private String DayLength;
}
