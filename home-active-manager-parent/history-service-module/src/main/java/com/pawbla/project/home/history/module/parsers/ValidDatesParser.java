package com.pawbla.project.home.history.module.parsers;

import com.pawbla.project.home.history.module.dao.DateTimeHistoryDao;
import com.pawbla.project.home.history.module.model.ValidData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.pawbla.project.home.history.module.utils.AppConstants.*;

@Component
public class ValidDatesParser {

    private final DateTimeHistoryDao dao;

    public ValidDatesParser(DateTimeHistoryDao dao) {
        this.dao = dao;
    }

    public String getParsed() {
        List<ValidData> validDataList = dao.findValidData();
        Map<String, List<ValidData>> mapped = validDataList.stream().collect(Collectors.groupingBy(ValidData::getValidYear));
        JSONArray array = new JSONArray();
        mapped.keySet().forEach(key -> parseData(array, key, mapped));
        return new JSONObject().put(VALUES, array).toString();
    }

    private void parseData(JSONArray array, Object key, Map<String, List<ValidData>> mapped) {
        List<ValidData> validDataList = mapped.get(key);
        JSONObject object = new JSONObject();
        object.put(YEAR, key);
        JSONArray months = new JSONArray();
        validDataList.forEach(v -> months.put(v.getValidMonth()));
        object.put(MONTHS, months);
        array.put(object);

    }
}