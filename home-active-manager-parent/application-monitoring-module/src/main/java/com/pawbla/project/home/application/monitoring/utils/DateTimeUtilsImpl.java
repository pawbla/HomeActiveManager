package com.pawbla.project.home.application.monitoring.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateTimeUtilsImpl implements DateTimeUtils {

    @Override
    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDateTime getTimeOf(String dateTime) {
        return LocalDateTime.parse(dateTime);
    }
}
