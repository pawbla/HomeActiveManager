package com.pawbla.project.home.embedded.sensor.handler;

import com.pawbla.project.home.embedded.sensor.model.DHT;
import com.pawbla.project.home.embedded.sensor.reader.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(SpringExtension.class)
public class DHTHandlerTest {

    @MockBean
    private Reader reader;

    private DHTHandler handler;

    private static final int MAX_ACCEPTABLE_CONSECUTIVE_ERRORS = 3;

    @BeforeEach
    public void setUp() {
        handler = new DHTHandler(1, reader);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void beforeFirstHandleTest() {
        assertEquals("Initialize temperature", BigDecimal.valueOf(0), handler.getTemperature());
        assertEquals("Initialize humidity", BigDecimal.valueOf(0), handler.getHumidity());
        assertEquals("Initialize date", LocalDateTime.of(2000, 1, 1, 1, 1, 1),
                handler.getLastCorrectReadData());
        assertTrue("Is error", handler.isError());
        assertEquals("Initialization error code", -9, handler.getErrorCode());
    }

    @Test
    public void handleTest() throws InterruptedException {
        //before
        Mockito.when(reader.getDht()).thenReturn(new DHT(BigDecimal.valueOf(12.3), BigDecimal.valueOf(35.4)));
        //when
        handler.handle();
        //then
        assertEquals("Temperature", BigDecimal.valueOf(12.3), handler.getTemperature());
        assertEquals("Humidity", BigDecimal.valueOf(35.4), handler.getHumidity());
        assertFalse("Is Error", handler.isError());
    }

    @Test
    public void calculateAvgValuesTest() throws InterruptedException {
        //before
        Mockito.when(reader.getDht()).thenReturn(new DHT(BigDecimal.valueOf(10.5), BigDecimal.valueOf(11.4)));
        handler.handle();
        Mockito.when(reader.getDht()).thenReturn(new DHT(BigDecimal.valueOf(11.0), BigDecimal.valueOf(43.2)));
        handler.handle();
        Mockito.when(reader.getDht()).thenReturn(new DHT(BigDecimal.valueOf(15.1), BigDecimal.valueOf(12.0)));
        //when
        handler.handle();
        //then
        assertEquals("Temperature", BigDecimal.valueOf(12.2), handler.getTemperature());
        assertEquals("Humidity", BigDecimal.valueOf(22.2), handler.getHumidity());
        assertFalse("Is Error", handler.isError());
    }
}