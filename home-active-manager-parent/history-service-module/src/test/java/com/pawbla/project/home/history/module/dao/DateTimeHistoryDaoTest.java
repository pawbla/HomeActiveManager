package com.pawbla.project.home.history.module.dao;

import com.pawbla.project.home.history.module.config.DbConfiguration;
import com.pawbla.project.home.history.module.config.DbSourceConfigurationDev;
import com.pawbla.project.home.history.module.model.StatsValue;
import com.pawbla.project.home.history.module.model.ValidData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DbConfiguration.class, DbSourceConfigurationDev.class})
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DateTimeHistoryDaoTest {

    @Autowired
    private DateTimeHistoryDao dateTimeHistoryDao;

    @Test
    void shouldProvideStatisticsPerDayForTemperatureIn() {
        List<StatsValue> valueList = dateTimeHistoryDao.findTempInPerDay("2023", "01");
        assertEquals(2, valueList.size());
        StatsValue statsValue0 = valueList.get(0);
        StatsValue statsValue1 = valueList.get(1);
        assertEquals("2023", statsValue0.getTmpYear());
        assertEquals("01", statsValue0.getTmpMonth());
        assertEquals("01", statsValue0.getTmpDay());
        assertEquals(BigDecimal.valueOf(21.5).stripTrailingZeros(), statsValue0.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(21.0).stripTrailingZeros(), statsValue0.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(22.0).stripTrailingZeros(), statsValue0.getMaxVal().stripTrailingZeros());
        assertEquals("2023", statsValue1.getTmpYear());
        assertEquals("01", statsValue1.getTmpMonth());
        assertEquals("02", statsValue1.getTmpDay());
        assertEquals(BigDecimal.valueOf(19.95).stripTrailingZeros(), statsValue1.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(19.91).stripTrailingZeros(), statsValue1.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(20.01).stripTrailingZeros(), statsValue1.getMaxVal().stripTrailingZeros());
    }

    @Test
    void shouldProvideStatisticsPerDayForHumidityIn() {
        List<StatsValue> valueList = dateTimeHistoryDao.findHumInPerDay("2023", "01");
        assertEquals(2, valueList.size());
        StatsValue statsValue0 = valueList.get(0);
        StatsValue statsValue1 = valueList.get(1);
        assertEquals("2023", statsValue0.getTmpYear());
        assertEquals("01", statsValue0.getTmpMonth());
        assertEquals("01", statsValue0.getTmpDay());
        assertEquals(BigDecimal.valueOf(35.67).stripTrailingZeros(), statsValue0.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(35.2).stripTrailingZeros(), statsValue0.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(36.1).stripTrailingZeros(), statsValue0.getMaxVal().stripTrailingZeros());
        assertEquals("2023", statsValue1.getTmpYear());
        assertEquals("01", statsValue1.getTmpMonth());
        assertEquals("02", statsValue1.getTmpDay());
        assertEquals(BigDecimal.valueOf(33.83).stripTrailingZeros(), statsValue1.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(33.1).stripTrailingZeros(), statsValue1.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(35.0).stripTrailingZeros(), statsValue1.getMaxVal().stripTrailingZeros());
    }

    @Test
    void shouldProvideStatisticsPerDayForTemperatureOut() {
        List<StatsValue> valueList = dateTimeHistoryDao.findTempOutPerDay("2023", "01");
        assertEquals(2, valueList.size());
        StatsValue statsValue0 = valueList.get(0);
        StatsValue statsValue1 = valueList.get(1);
        assertEquals("2023", statsValue0.getTmpYear());
        assertEquals("01", statsValue0.getTmpMonth());
        assertEquals("01", statsValue0.getTmpDay());
        assertEquals(BigDecimal.valueOf(11.3).stripTrailingZeros(), statsValue0.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(10.3).stripTrailingZeros(), statsValue0.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(12.4).stripTrailingZeros(), statsValue0.getMaxVal().stripTrailingZeros());
        assertEquals("2023", statsValue1.getTmpYear());
        assertEquals("01", statsValue1.getTmpMonth());
        assertEquals("02", statsValue1.getTmpDay());
        assertEquals(BigDecimal.valueOf(6.77).stripTrailingZeros(), statsValue1.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(-4.3).stripTrailingZeros(), statsValue1.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(12.5).stripTrailingZeros(), statsValue1.getMaxVal().stripTrailingZeros());
    }

    @Test
    void shouldProvideStatisticsPerDayForHumidityOut() {
        List<StatsValue> valueList = dateTimeHistoryDao.findHumOutPerDay("2023", "01");
        assertEquals(2, valueList.size());
        StatsValue statsValue0 = valueList.get(0);
        StatsValue statsValue1 = valueList.get(1);
        assertEquals("2023", statsValue0.getTmpYear());
        assertEquals("01", statsValue0.getTmpMonth());
        assertEquals("01", statsValue0.getTmpDay());
        assertEquals(BigDecimal.valueOf(81.03).stripTrailingZeros(), statsValue0.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(80.0).stripTrailingZeros(), statsValue0.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(82.1).stripTrailingZeros(), statsValue0.getMaxVal().stripTrailingZeros());
        assertEquals("2023", statsValue1.getTmpYear());
        assertEquals("01", statsValue1.getTmpMonth());
        assertEquals("02", statsValue1.getTmpDay());
        assertEquals(BigDecimal.valueOf(76.76).stripTrailingZeros(), statsValue1.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(76.5).stripTrailingZeros(), statsValue1.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(77.01).stripTrailingZeros(), statsValue1.getMaxVal().stripTrailingZeros());
    }

    @Test
    void shouldProvideStatisticsPerDayForPressure() {
        List<StatsValue> valueList = dateTimeHistoryDao.findPressurePerDay("2023", "01");
        assertEquals(2, valueList.size());
        StatsValue statsValue0 = valueList.get(0);
        StatsValue statsValue1 = valueList.get(1);
        assertEquals("2023", statsValue0.getTmpYear());
        assertEquals("01", statsValue0.getTmpMonth());
        assertEquals("01", statsValue0.getTmpDay());
        assertEquals(BigDecimal.valueOf(1014.40).stripTrailingZeros(), statsValue0.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(1000.0).stripTrailingZeros(), statsValue0.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(1023.21).stripTrailingZeros(), statsValue0.getMaxVal().stripTrailingZeros());
        assertEquals("2023", statsValue1.getTmpYear());
        assertEquals("01", statsValue1.getTmpMonth());
        assertEquals("02", statsValue1.getTmpDay());
        assertEquals(BigDecimal.valueOf(1021.56).stripTrailingZeros(), statsValue1.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(1020.45).stripTrailingZeros(), statsValue1.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(1023.11).stripTrailingZeros(), statsValue1.getMaxVal().stripTrailingZeros());
    }

    @Test
    void shouldProvideStatisticsPerMonthForTemperatureIn() {
        List<StatsValue> valueList = dateTimeHistoryDao.findTempInPerMonth("2023");
        assertEquals(2, valueList.size());
        StatsValue statsValue0 = valueList.get(0);
        StatsValue statsValue1 = valueList.get(1);
        assertEquals("2023", statsValue0.getTmpYear());
        assertEquals("01", statsValue0.getTmpMonth());
        assertNull(statsValue0.getTmpDay());
        assertEquals(BigDecimal.valueOf(20.72).stripTrailingZeros(), statsValue0.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(19.91).stripTrailingZeros(), statsValue0.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(22.0).stripTrailingZeros(), statsValue0.getMaxVal().stripTrailingZeros());
        assertEquals("2023", statsValue1.getTmpYear());
        assertEquals("02", statsValue1.getTmpMonth());
        assertNull(statsValue1.getTmpDay());
        assertEquals(BigDecimal.valueOf(1.01).stripTrailingZeros(), statsValue1.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(1.01).stripTrailingZeros(), statsValue1.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(1.01).stripTrailingZeros(), statsValue1.getMaxVal().stripTrailingZeros());
    }

    @Test
    void shouldProvideStatisticsPerMonthForHumidityIn() {
        List<StatsValue> valueList = dateTimeHistoryDao.findHumInPerMonth("2023");
        assertEquals(2, valueList.size());
        StatsValue statsValue0 = valueList.get(0);
        StatsValue statsValue1 = valueList.get(1);
        assertEquals("2023", statsValue0.getTmpYear());
        assertEquals("01", statsValue0.getTmpMonth());
        assertNull(statsValue0.getTmpDay());
        assertEquals(BigDecimal.valueOf(34.75).stripTrailingZeros(), statsValue0.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(33.1).stripTrailingZeros(), statsValue0.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(36.1).stripTrailingZeros(), statsValue0.getMaxVal().stripTrailingZeros());
        assertEquals("2023", statsValue1.getTmpYear());
        assertEquals("02", statsValue1.getTmpMonth());
        assertNull(statsValue1.getTmpDay());
        assertEquals(BigDecimal.valueOf(32.0).stripTrailingZeros(), statsValue1.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(32.0).stripTrailingZeros(), statsValue1.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(32.0).stripTrailingZeros(), statsValue1.getMaxVal().stripTrailingZeros());
    }

    @Test
    void shouldProvideStatisticsPerMonthForTemperatureOut() {
        List<StatsValue> valueList = dateTimeHistoryDao.findTempOutPerMonth("2023");
        assertEquals(2, valueList.size());
        StatsValue statsValue0 = valueList.get(0);
        StatsValue statsValue1 = valueList.get(1);
        assertEquals("2023", statsValue0.getTmpYear());
        assertEquals("01", statsValue0.getTmpMonth());
        assertNull(statsValue0.getTmpDay());
        assertEquals(BigDecimal.valueOf(9.03).stripTrailingZeros(), statsValue0.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(-4.3).stripTrailingZeros(), statsValue0.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(12.5).stripTrailingZeros(), statsValue0.getMaxVal().stripTrailingZeros());
        assertEquals("2023", statsValue1.getTmpYear());
        assertEquals("02", statsValue1.getTmpMonth());
        assertNull(statsValue1.getTmpDay());
        assertEquals(BigDecimal.valueOf(2.1).stripTrailingZeros(), statsValue1.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(2.1).stripTrailingZeros(), statsValue1.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(2.1).stripTrailingZeros(), statsValue1.getMaxVal().stripTrailingZeros());
    }

    @Test
    void shouldProvideStatisticsPerMonthForHumidityOut() {
        List<StatsValue> valueList = dateTimeHistoryDao.findHumOutPerMonth("2023");
        assertEquals(2, valueList.size());
        StatsValue statsValue0 = valueList.get(0);
        StatsValue statsValue1 = valueList.get(1);
        assertEquals("2023", statsValue0.getTmpYear());
        assertEquals("01", statsValue0.getTmpMonth());
        assertNull(statsValue0.getTmpDay());
        assertEquals(BigDecimal.valueOf(79.32).stripTrailingZeros(), statsValue0.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(76.5).stripTrailingZeros(), statsValue0.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(82.1).stripTrailingZeros(), statsValue0.getMaxVal().stripTrailingZeros());
        assertEquals("2023", statsValue1.getTmpYear());
        assertEquals("02", statsValue1.getTmpMonth());
        assertNull(statsValue1.getTmpDay());
        assertEquals(BigDecimal.valueOf(69.01).stripTrailingZeros(), statsValue1.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(69.01).stripTrailingZeros(), statsValue1.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(69.01).stripTrailingZeros(), statsValue1.getMaxVal().stripTrailingZeros());
    }

    @Test
    void shouldProvideStatisticsPerMonthForPressure() {
        List<StatsValue> valueList = dateTimeHistoryDao.findPressurePerMonth("2023");
        assertEquals(2, valueList.size());
        StatsValue statsValue0 = valueList.get(0);
        StatsValue statsValue1 = valueList.get(1);
        assertEquals("2023", statsValue0.getTmpYear());
        assertEquals("01", statsValue0.getTmpMonth());
        assertNull(statsValue0.getTmpDay());
        assertEquals(BigDecimal.valueOf(1017.98).stripTrailingZeros(), statsValue0.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(1000.00).stripTrailingZeros(), statsValue0.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(1023.21).stripTrailingZeros(), statsValue0.getMaxVal().stripTrailingZeros());
        assertEquals("2023", statsValue1.getTmpYear());
        assertEquals("02", statsValue1.getTmpMonth());
        assertNull(statsValue1.getTmpDay());
        assertEquals(BigDecimal.valueOf(1015.45).stripTrailingZeros(), statsValue1.getMeanVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(1015.45).stripTrailingZeros(), statsValue1.getMinVal().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(1015.45).stripTrailingZeros(), statsValue1.getMaxVal().stripTrailingZeros());
    }

    @Test
    void shouldReadValidDates() {
        List<ValidData> validDataList = dateTimeHistoryDao.findValidData();
        assertEquals(3, validDataList.size());
        ValidData validData0 = validDataList.get(0);
        ValidData validData1 = validDataList.get(1);
        ValidData validData2 = validDataList.get(2);
        assertEquals("2022", validData0.getValidYear());
        assertEquals("12", validData0.getValidMonth());
        assertEquals("2023", validData1.getValidYear());
        assertEquals("01", validData1.getValidMonth());
        assertEquals("2023", validData2.getValidYear());
        assertEquals("02", validData2.getValidMonth());
    }

    @Test
    void shouldReturnEmptyListWhenNoDayData() {
        List<StatsValue> valueListTempIn = dateTimeHistoryDao.findTempInPerDay("2020", "01");
        assertEquals(0, valueListTempIn.size());
        List<StatsValue> valueListHumIn = dateTimeHistoryDao.findHumInPerDay("2020", "01");
        assertEquals(0, valueListHumIn.size());
        List<StatsValue> valueListTempOut = dateTimeHistoryDao.findTempOutPerDay("2020", "01");
        assertEquals(0, valueListTempOut.size());
        List<StatsValue> valueListHumOut = dateTimeHistoryDao.findHumOutPerDay("2020", "01");
        assertEquals(0, valueListHumOut.size());
        List<StatsValue> valueListPressure = dateTimeHistoryDao.findPressurePerDay("2020", "01");
        assertEquals(0, valueListPressure.size());
    }

    @Test
    void shouldReturnEmptyListWhenNoMonthData() {
        List<StatsValue> valueListTempIn = dateTimeHistoryDao.findTempInPerMonth("2020");
        assertEquals(0, valueListTempIn.size());
        List<StatsValue> valueListHumIn = dateTimeHistoryDao.findHumInPerMonth("2020");
        assertEquals(0, valueListHumIn.size());
        List<StatsValue> valueListTempOut = dateTimeHistoryDao.findTempOutPerMonth("2020");
        assertEquals(0, valueListTempOut.size());
        List<StatsValue> valueListHumOut = dateTimeHistoryDao.findHumOutPerMonth("2020");
        assertEquals(0, valueListHumOut.size());
        List<StatsValue> valueListPressure = dateTimeHistoryDao.findPressurePerMonth("2020");
        assertEquals(0, valueListPressure.size());
    }
}
