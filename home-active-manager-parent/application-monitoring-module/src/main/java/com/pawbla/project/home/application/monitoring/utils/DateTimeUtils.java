package com.pawbla.project.home.application.monitoring.utils;

import java.time.LocalDateTime;

public interface DateTimeUtils {
    LocalDateTime getNow();
    LocalDateTime getTimeOf(String dateTime);
}
