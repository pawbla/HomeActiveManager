package com.pawbla.project.home.weather.service.parsers;

import com.pawbla.project.home.weather.service.models.old.MoonPhaseMeasurement;;
import com.pawbla.project.home.weather.service.parsers.old.MoonPhaseParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MoonPhaseParserTest {

    private MoonPhaseParser parser;

    @BeforeEach
    void setUp() {
        parser = new MoonPhaseParser();
    }

    @Test
    void newMoonTest() {
        MoonPhaseMeasurement actual = (MoonPhaseMeasurement) parser.parse(-5);
        assertEquals("0", actual.getPictureNo());
        assertEquals("Nów", actual.getText());
    }

    @Test
    void waxingCrescentTest() {
        MoonPhaseMeasurement actual = (MoonPhaseMeasurement) parser.parse(18);
        assertEquals("1", actual.getPictureNo());
        assertEquals("Przybywający sierp", actual.getText());
    }

    @Test
    void firstQuarterTest() {
        MoonPhaseMeasurement actual = (MoonPhaseMeasurement) parser.parse(55);
        assertEquals("2", actual.getPictureNo());
        assertEquals("Pierwsza kwadra", actual.getText());
    }

    @Test
    void waxingGibbousTest() {
        MoonPhaseMeasurement actual = (MoonPhaseMeasurement) parser.parse(85);
        assertEquals("3", actual.getPictureNo());
        assertEquals("Przybywający garb", actual.getText());
    }

    @Test
    void fullMoonTest() {
        MoonPhaseMeasurement actual = (MoonPhaseMeasurement) parser.parse(-95);
        assertEquals("4", actual.getPictureNo());
        assertEquals("Pełnia", actual.getText());
    }
    @Test
    void wannngGibbousTest() {
        MoonPhaseMeasurement actual = (MoonPhaseMeasurement) parser.parse(-94);
        assertEquals("5", actual.getPictureNo());
        assertEquals("Ubywający garb", actual.getText());
    }

    @Test
    void lastQuarterTest() {
        MoonPhaseMeasurement actual = (MoonPhaseMeasurement) parser.parse(-45);
        assertEquals("6", actual.getPictureNo());
        assertEquals("Ostatnia kwadra", actual.getText());
    }

    @Test
    void wannngCrescentTest() {
        MoonPhaseMeasurement actual = (MoonPhaseMeasurement) parser.parse(-6);
        assertEquals("7", actual.getPictureNo());
        assertEquals("Ubywający sierp", actual.getText());
    }

}