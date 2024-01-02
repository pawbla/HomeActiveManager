package com.pawbla.project.home.history.module.parsers;

import com.pawbla.project.home.history.module.model.StatsValue;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import static com.pawbla.project.home.history.module.utils.AppConstants.*;
import static com.pawbla.project.home.history.module.utils.AppConstants.MIN;

public class AbstractWeatherHistoryParser {

    protected JSONArray getHistory(List<StatsValue> statsValueList, String type) {
        JSONArray array = new JSONArray();
        statsValueList.forEach(m -> array.put(getMonthValue(m, type)));
        return array;
    }

    private JSONObject getMonthValue(StatsValue statsValue, String type) {
        return new JSONObject()
                .put(type, type.equals(MONTH) ? statsValue.getTmpMonth() : statsValue.getTmpDay())
                .put(MEAN, statsValue.getMeanVal())
                .put(MAX, statsValue.getMaxVal())
                .put(MIN, statsValue.getMinVal());
    }
}
