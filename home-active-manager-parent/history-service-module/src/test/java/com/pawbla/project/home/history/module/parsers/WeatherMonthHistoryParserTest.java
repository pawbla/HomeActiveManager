package com.pawbla.project.home.history.module.parsers;

import com.pawbla.project.home.history.module.dao.DateTimeHistoryDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WeatherMonthHistoryParserTest extends AbstractHistoryParserTest {

    private static final List<List<String>> INPUT_DATA = Arrays.asList(
            Arrays.asList("2023", "01", "", "12.01", "-2.0", "9.4"),
            Arrays.asList("2023", "02", "", "12.92", "-1.1", "13.2"),
            Arrays.asList("2023", "03", "", "13.91", "-0.2", "27.4"),
            Arrays.asList("2023", "04", "", "15.21", "7.02", "25.2")
    );

    @Mock
    private DateTimeHistoryDao dao;

    private WeatherMonthHistoryParser parser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        parser = new WeatherMonthHistoryParser(dao);
    }

    @Test
    void shouldPrepareResponseForTempIn() {
        Mockito.when(dao.findTempInPerMonth("2023")).thenReturn(getInputData(INPUT_DATA));
        String actual = parser.getParsed("tempIn", "2023");
        String expected = getExpected("month", INPUT_DATA);
        JSONAssert.assertEquals(expected, actual,false);
        verify(dao, times(1)).findTempInPerMonth("2023");
    }

    @Test
    void shouldPrepareResponseForHumIn() {
        Mockito.when(dao.findHumInPerMonth("2023")).thenReturn(getInputData(INPUT_DATA));
        String actual = parser.getParsed("humIn", "2023");
        String expected = getExpected("month", INPUT_DATA);
        JSONAssert.assertEquals(expected, actual,false);
        verify(dao, times(1)).findHumInPerMonth("2023");
    }

    @Test
    void shouldPrepareResponseForTempOut() {
        Mockito.when(dao.findTempOutPerMonth("2023")).thenReturn(getInputData(INPUT_DATA));
        String actual = parser.getParsed("tempOut", "2023");
        String expected = getExpected("month", INPUT_DATA);
        JSONAssert.assertEquals(expected, actual,false);
        verify(dao, times(1)).findTempOutPerMonth("2023");
    }

    @Test
    void shouldPrepareResponseForHumOut() {
        Mockito.when(dao.findHumOutPerMonth("2023")).thenReturn(getInputData(INPUT_DATA));
        String actual = parser.getParsed("humOut", "2023");
        String expected = getExpected("month", INPUT_DATA);
        JSONAssert.assertEquals(expected, actual,false);
        verify(dao, times(1)).findHumOutPerMonth("2023");
    }

    @Test
    void shouldPrepareResponseForPressure() {
        Mockito.when(dao.findPressurePerMonth("2023")).thenReturn(getInputData(INPUT_DATA));
        String actual = parser.getParsed("pressure", "2023");
        String expected = getExpected("month", INPUT_DATA);
        JSONAssert.assertEquals(expected, actual,false);
        verify(dao, times(1)).findPressurePerMonth("2023");
    }
}