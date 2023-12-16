package com.pawbla.project.home.weather.service.utils;

public interface DateTimeUtils {
    int getCurrentYear();
    int getCurrentMonth();
    int getCurrentDay();
    int getCurrentHour();
    int getCurrentMinute();
    int getCurrentSecond();
    Long getNowEpoch();
}
