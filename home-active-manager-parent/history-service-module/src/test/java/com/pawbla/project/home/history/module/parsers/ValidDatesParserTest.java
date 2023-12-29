package com.pawbla.project.home.history.module.parsers;

import com.pawbla.project.home.history.module.dao.DateTimeHistoryDao;
import com.pawbla.project.home.history.module.model.ValidData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ValidDatesParserTest {

    @Mock
    private DateTimeHistoryDao dao;

    private ValidDatesParser parser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        parser = new ValidDatesParser(dao);
        Mockito.when(dao.findValidData()).thenReturn(prepareTestData());
    }

    @Test
    void shouldPrepareParsedResponse() {
        String actual = parser.getParsed();
        String expected = getExpected();
        JSONAssert.assertEquals(expected, actual,false);
    }

    private List<ValidData> prepareTestData() {
        List<ValidData> validDataList = new ArrayList<>();
        validDataList.add(getValidData("2022", "10"));
        validDataList.add(getValidData("2022", "11"));
        validDataList.add(getValidData("2022", "12"));
        validDataList.add(getValidData("2023", "01"));
        validDataList.add(getValidData("2023", "02"));
        validDataList.add(getValidData("2023", "03"));
        validDataList.add(getValidData("2023", "04"));
        return validDataList;
    }

    private ValidData getValidData(String validYear, String validMonth) {
        return new ValidData() {
            @Override
            public String getValidYear() {
                return validYear;
            }

            @Override
            public String getValidMonth() {
                return validMonth;
            }
        };
    }

    private String getExpected() {
        JSONArray years = new JSONArray();
        years.put(getExpectedYear("2022", Arrays.asList("10", "11", "12")));
        years.put(getExpectedYear("2023", Arrays.asList("01", "02", "03", "04")));
        JSONObject values = new JSONObject();
        values.put("values", years);
        return values.toString();
    }

    private JSONObject getExpectedYear(String year, List<String> months) {
        JSONObject object = new JSONObject();
        object.put("year", year);
        JSONArray array = new JSONArray();
        months.forEach(array::put);
        object.put("months", array);
        return object;
    }

}