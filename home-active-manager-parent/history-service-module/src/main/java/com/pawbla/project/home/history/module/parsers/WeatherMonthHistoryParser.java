package com.pawbla.project.home.history.module.parsers;

import com.pawbla.project.home.history.module.dao.DateTimeHistoryDao;
import com.pawbla.project.home.history.module.model.StatsValue;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.pawbla.project.home.history.module.utils.AppConstants.*;

@Component
public class WeatherMonthHistoryParser extends AbstractWeatherHistoryParser {

    private final DateTimeHistoryDao dao;

    public WeatherMonthHistoryParser(DateTimeHistoryDao dao) {
        this.dao = dao;
    }

    public String getParsed(String type, String year) {
        List<StatsValue> statsValueList = getStatsValueBasedOnType(type, year);

        return new JSONObject()
                .put(YEAR, year)
                .put(PERIOD, YEAR)
                .put(TYPE, getType(type))
                .put(HISTORY, getHistory(statsValueList, MONTH)).toString();
    }

    private List<StatsValue> getStatsValueBasedOnType(String type, String year) {
        return switch (type) {
            case TEMP_IN_TYPE -> dao.findTempInPerMonth(year);
            case HUM_IN_TYPE -> dao.findHumInPerMonth(year);
            case TEMP_OUT_TYPE -> dao.findTempOutPerMonth(year);
            case HUM_OUT_TYPE -> dao.findHumOutPerMonth(year);
            case PRESSURE_TYPE -> dao.findPressurePerMonth(year);
            default -> throw new IllegalArgumentException(INCORRECT_TYPE_VALUE);
        };
    }
}
