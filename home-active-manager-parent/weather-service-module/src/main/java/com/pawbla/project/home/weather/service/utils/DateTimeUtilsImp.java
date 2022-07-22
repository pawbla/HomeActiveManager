package com.pawbla.project.home.weather.service.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DateTimeUtilsImp implements DateTimeUtils {
    @Override
    public int getCurrentYear() {
        return LocalDateTime.now().getYear();
    }

    @Override
    public int getCurrentMonth() {
        return LocalDateTime.now().getMonthValue();
    }

    @Override
    public int getCurrentDay() {
        return LocalDateTime.now().getDayOfMonth();
    }

    @Override
    public int getCurrentHour() {
        return LocalDateTime.now().getHour();
    }

    @Override
    public int getCurrentMinute() {
        return LocalDateTime.now().getMinute();
    }

    @Override
    public int getCurrentSecond() {
        return LocalDateTime.now().getSecond();
    }
}
