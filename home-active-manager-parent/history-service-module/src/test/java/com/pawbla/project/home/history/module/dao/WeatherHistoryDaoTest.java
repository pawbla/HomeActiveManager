package com.pawbla.project.home.history.module.dao;

import com.pawbla.project.home.history.module.config.DbConfiguration;
import com.pawbla.project.home.history.module.config.DbSourceConfigurationDev;
import com.pawbla.project.home.history.module.model.WeatherMeasurement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DbConfiguration.class, DbSourceConfigurationDev.class})
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class WeatherHistoryDaoTest {

    @Autowired
    private WeatherHistoryDao weatherHistoryDao;

    @Test
    void shouldReadRecord() {
        WeatherMeasurement weatherMeasurement = weatherHistoryDao.findByEpochDateTimeStamp(1701975600L);
        assertEquals(1701975600L, weatherMeasurement.getEpochDateTimeStamp());
        assertEquals(BigDecimal.valueOf(6.0).stripTrailingZeros(),
                weatherMeasurement.getTemperatureIn().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(46.1).stripTrailingZeros(),
                weatherMeasurement.getHumidityIn().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(-10.0).stripTrailingZeros(),
                weatherMeasurement.getTemperatureOut().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(55.0).stripTrailingZeros(),
                weatherMeasurement.getHumidityOut().stripTrailingZeros());
        assertNull(weatherMeasurement.getPressure());
    }

    @Test
    void shouldReadAllRecords() {
        List<WeatherMeasurement> weatherMeasurementList = weatherHistoryDao.findAll();
        assertEquals(6, weatherMeasurementList.size());
        List<Long> timestampsList = getTimestampsList(weatherMeasurementList);
        assertTrue(timestampsList.contains(1701957600L));
        assertTrue(timestampsList.contains(1701961200L));
        assertTrue(timestampsList.contains(1701964800L));
        assertTrue(timestampsList.contains(1701968400L));
        assertTrue(timestampsList.contains(1701972000L));
        assertTrue(timestampsList.contains(1701975600L));
    }

    @Test
    void shouldReadByTimestampRange() {
        List<WeatherMeasurement> weatherMeasurementList = weatherHistoryDao.findByEpochDateTimeStampBetween(1701961000L, 1701972100L);
        assertEquals(4, weatherMeasurementList.size());
        List<Long> timestampsList = getTimestampsList(weatherMeasurementList);
        assertFalse(timestampsList.contains(1701957600L));
        assertTrue(timestampsList.contains(1701961200L));
        assertTrue(timestampsList.contains(1701964800L));
        assertTrue(timestampsList.contains(1701968400L));
        assertTrue(timestampsList.contains(1701972000L));
        assertFalse(timestampsList.contains(1701975600L));
    }

    @Test
    void shouldSaveData() {
        WeatherMeasurement dataToSave = new WeatherMeasurement();
        dataToSave.setEpochDateTimeStamp(1701982800L);
        dataToSave.setTemperatureIn(BigDecimal.valueOf(10.0));
        dataToSave.setHumidityIn(BigDecimal.valueOf(56.2));
        dataToSave.setTemperatureOut(BigDecimal.valueOf(-7.32));
        dataToSave.setHumidityOut(BigDecimal.valueOf(12.0));
        dataToSave.setPressure(null);

        weatherHistoryDao.save(dataToSave);
        List<WeatherMeasurement> weatherMeasurementList = weatherHistoryDao.findAll();
        assertEquals(7, weatherMeasurementList.size());
        assertTrue(getTimestampsList(weatherMeasurementList).contains(1701982800L));
        WeatherMeasurement savedMeasurement = weatherMeasurementList.stream()
                .filter(m -> m.getEpochDateTimeStamp().equals(1701982800L))
                .findFirst().orElse(null);
        assertEquals(BigDecimal.valueOf(10.0).stripTrailingZeros(),
                savedMeasurement.getTemperatureIn().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(56.2).stripTrailingZeros(),
                savedMeasurement.getHumidityIn().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(-7.32).stripTrailingZeros(),
                savedMeasurement.getTemperatureOut().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(12.0).stripTrailingZeros(),
                savedMeasurement.getHumidityOut().stripTrailingZeros());
        assertNull(savedMeasurement.getPressure());
    }

    private List<Long> getTimestampsList(List<WeatherMeasurement> weatherMeasurementList) {
        return weatherMeasurementList.stream().map(WeatherMeasurement::getEpochDateTimeStamp).collect(Collectors.toList());
    }

}