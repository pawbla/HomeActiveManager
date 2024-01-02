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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WeatherDayHistoryParserTest extends AbstractHistoryParserTest {

    private static final List<List<String>> INPUT_DATA = Arrays.asList(
            Arrays.asList("2023", "11", "01", "12.01", "-2.0", "9.4"),
            Arrays.asList("2023", "11", "02", "12.92", "-1.1", "13.2"),
            Arrays.asList("2023", "11", "03", "13.91", "-0.2", "27.4"),
            Arrays.asList("2023", "11", "04", "15.21", "7.02", "25.2")
    );

    @Mock
    private DateTimeHistoryDao dao;

    private WeatherDayHistoryParser parser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        parser = new WeatherDayHistoryParser(dao);
    }

    @Test
    void shouldPrepareResponseForTempIn() {
        Mockito.when(dao.findTempInPerDay("2023", "11")).thenReturn(getInputData(INPUT_DATA));
        String actual = parser.getParsed("tempIn", "2023", "11");
        String expected = getExpected("day", INPUT_DATA);
        JSONAssert.assertEquals(expected, actual,false);
        verify(dao, times(1)).findTempInPerDay("2023", "11");
    }

    @Test
    void shouldPrepareResponseForHumIn() {
        Mockito.when(dao.findHumInPerDay("2023", "11")).thenReturn(getInputData(INPUT_DATA));
        String actual = parser.getParsed("humIn", "2023", "11");
        String expected = getExpected("day", INPUT_DATA);
        JSONAssert.assertEquals(expected, actual,false);
        verify(dao, times(1)).findHumInPerDay("2023", "11");
    }

    @Test
    void shouldPrepareResponseForTempOut() {
        Mockito.when(dao.findTempOutPerDay("2023", "11")).thenReturn(getInputData(INPUT_DATA));
        String actual = parser.getParsed("tempOut", "2023", "11");
        String expected = getExpected("day", INPUT_DATA);
        JSONAssert.assertEquals(expected, actual,false);
        verify(dao, times(1)).findTempOutPerDay("2023", "11");
    }

    @Test
    void shouldPrepareResponseForHumOut() {
        Mockito.when(dao.findHumOutPerDay("2023", "11")).thenReturn(getInputData(INPUT_DATA));
        String actual = parser.getParsed("humOut", "2023", "11");
        String expected = getExpected("day", INPUT_DATA);
        JSONAssert.assertEquals(expected, actual,false);
        verify(dao, times(1)).findHumOutPerDay("2023", "11");
    }

    @Test
    void shouldPrepareResponseForPressure() {
        Mockito.when(dao.findPressurePerDay("2023", "11")).thenReturn(getInputData(INPUT_DATA));
        String actual = parser.getParsed("pressure", "2023", "11");
        String expected = getExpected("day", INPUT_DATA);
        JSONAssert.assertEquals(expected, actual,false);
        verify(dao, times(1)).findPressurePerDay("2023", "11");
    }
}