package com.pawbla.project.home.history.module.parsers;

import com.pawbla.project.home.history.module.model.StatsValue;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractHistoryParserTest {

    protected List<StatsValue> getInputData(List<List<String>> input) {
        return input.stream()
                .map(m -> getStatusValue(m.get(0), m.get(1), m.get(2), m.get(3), m.get(4), m.get(5)))
                .collect(Collectors.toList());
    }

    protected String getExpected(String type, List<List<String>> inputData) {
        JSONObject expected = new JSONObject()
                .put("year", inputData.get(0).get(0))
                .put("history", getExpectedStats(type, inputData));

        if (type.equals("day")) {
            expected.put("month", inputData.get(0).get(1));
        }

        return expected.toString();
    }

    private StatsValue getStatusValue(String year, String month, String day, String avgVal, String minVal, String maxVal) {
        return new StatsValue() {
            @Override
            public String getTmpYear() {
                return year;
            }

            @Override
            public String getTmpMonth() {
                return month;
            }

            @Override
            public String getTmpDay() {
                return day;
            }

            @Override
            public BigDecimal getMeanVal() {
                return new BigDecimal(avgVal);
            }

            @Override
            public BigDecimal getMinVal() {
                return new BigDecimal(minVal);
            }

            @Override
            public BigDecimal getMaxVal() {
                return new BigDecimal(maxVal);
            }
        };
    }

    private JSONArray getExpectedStats(String type, List<List<String>> input) {
        int type_pos = type.equals("month") ? 1 : 2;
        JSONArray array = new JSONArray();
        input.forEach(m -> array.put(expectedMonth(type, m.get(type_pos), m.get(3), m.get(4), m.get(5))));
        return array;
    }

    private JSONObject expectedMonth(String type, String month, String mean, String max, String min) {
        return new JSONObject()
                .put(type, month)
                .put("mean", new BigDecimal(mean))
                .put("min", new BigDecimal(max))
                .put("max", new BigDecimal(min));
    }
}
