package com.pawbla.project.home.history.module.parsers;

import com.pawbla.project.home.history.module.dao.DateTimeHistoryDao;
import com.pawbla.project.home.history.module.model.StatsValue;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.pawbla.project.home.history.module.utils.AppConstants.*;

@Component
public class WeatherDayHistoryParser extends AbstractWeatherHistoryParser {

    private final DateTimeHistoryDao dao;

    public WeatherDayHistoryParser(DateTimeHistoryDao dao) {
        this.dao = dao;
    }

    public String getParsed(String type, String year, String month) {
        List<StatsValue> statsValueList = getStatsValueBasedOnType(type, year, month);

        return new JSONObject()
                .put(YEAR, year)
                .put(MONTH, month)
                .put(PERIOD, MONTH)
                .put(TYPE, getType(type))
                .put(HISTORY, getHistory(statsValueList, DAY)).toString();
    }

    private List<StatsValue> getStatsValueBasedOnType(String type, String year, String month) {
        return switch (type) {
            case TEMP_IN_TYPE -> dao.findTempInPerDay(year, month);
            case HUM_IN_TYPE -> dao.findHumInPerDay(year, month);
            case TEMP_OUT_TYPE -> dao.findTempOutPerDay(year, month);
            case HUM_OUT_TYPE -> dao.findHumOutPerDay(year, month);
            case PRESSURE_TYPE -> dao.findPressurePerDay(year, month);
            default -> throw new IllegalArgumentException(INCORRECT_TYPE_VALUE);
        };
    }
}
