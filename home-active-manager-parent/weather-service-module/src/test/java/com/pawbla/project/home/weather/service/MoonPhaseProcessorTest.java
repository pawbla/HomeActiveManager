package com.pawbla.project.home.weather.service;

import com.pawbla.project.home.weather.service.processor.MoonPhaseProcessor;
import com.pawbla.project.home.weather.service.utils.DateTimeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoonPhaseProcessorTest {

    @Mock
    private DateTimeUtils dataTimeUtils;

    private MoonPhaseProcessor moonPhaseProcessor;

    @BeforeEach
    void setUp() {
        moonPhaseProcessor = new MoonPhaseProcessor(dataTimeUtils);
        when(dataTimeUtils.getCurrentYear()).thenReturn(2022);
        when(dataTimeUtils.getCurrentHour()).thenReturn(12);
        when(dataTimeUtils.getCurrentMinute()).thenReturn(0);
        when(dataTimeUtils.getCurrentSecond()).thenReturn(0);
    }

    @Test
    void newMoonTest() {
        when(dataTimeUtils.getCurrentMonth()).thenReturn(1);
        when(dataTimeUtils.getCurrentDay()).thenReturn(2);
        double actual = moonPhaseProcessor.calc();
        Assertions.assertEquals(0.0, actual, "New moon");
    }

    @Test
    void waxingCrescentcentTest() {
        when(dataTimeUtils.getCurrentMonth()).thenReturn(2);
        when(dataTimeUtils.getCurrentDay()).thenReturn(4);
        double actual = moonPhaseProcessor.calc();
        Assertions.assertEquals(13.0, actual, "Waxing crescent");
    }

    @Test
    void firstQuarterTest() {
        when(dataTimeUtils.getCurrentMonth()).thenReturn(3);
        when(dataTimeUtils.getCurrentDay()).thenReturn(10);
        double actual = moonPhaseProcessor.calc();
        Assertions.assertEquals(50.0, actual, "First quarter");
    }

    @Test
    void waxingGibbousTest() {
        when(dataTimeUtils.getCurrentMonth()).thenReturn(7);
        when(dataTimeUtils.getCurrentDay()).thenReturn(10);
        double actual = moonPhaseProcessor.calc();
        Assertions.assertEquals(84.0, actual, "Waxing gibbous");
    }

    @Test
    void fullMoonTest() {
        when(dataTimeUtils.getCurrentMonth()).thenReturn(6);
        when(dataTimeUtils.getCurrentDay()).thenReturn(14);
        double actual = moonPhaseProcessor.calc();
        Assertions.assertEquals(-99.0, actual, "Full moon");
    }

    @Test
    void wanningGibbousTest() {
        when(dataTimeUtils.getCurrentMonth()).thenReturn(11);
        when(dataTimeUtils.getCurrentDay()).thenReturn(12);
        double actual = moonPhaseProcessor.calc();
        Assertions.assertEquals(-84.0, actual, "Wanning Gibous");
    }

    @Test
    void lastQuarterGibbousTest() {
        when(dataTimeUtils.getCurrentMonth()).thenReturn(5);
        when(dataTimeUtils.getCurrentDay()).thenReturn(22);
        double actual = moonPhaseProcessor.calc();
        Assertions.assertEquals(-53.0, actual, "New moon");
    }

    @Test
    void wanningCactualcentTest() {
        when(dataTimeUtils.getCurrentMonth()).thenReturn(9);
        when(dataTimeUtils.getCurrentDay()).thenReturn(21);
        double actual = moonPhaseProcessor.calc();
        Assertions.assertEquals(-18.0, actual, "New moon");
    }

}