package com.pawbla.project.home.embedded.sensor.handler;

import com.pawbla.project.home.embedded.sensor.model.DHT;
import com.pawbla.project.home.embedded.sensor.reader.Reader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
public class DHTHandlerTest {

    @MockBean
    private Reader reader;

    private DHTHandler handler;

    private static final int MAX_ACCEPTABLE_CONSECUTIVE_ERRORS = 3;

    @Before
    public void setUp() {
        handler = new DHTHandler(1, reader);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void beforeFirstHandleTest() {
        Assert.assertEquals("Initialize temperature", BigDecimal.valueOf(0), handler.getTemperature());
        Assert.assertEquals("Initialize humidity", BigDecimal.valueOf(0), handler.getHumidity());
        Assert.assertEquals("Initialize date", LocalDateTime.of(2000, 1, 1, 1, 1, 1),
                handler.getLastCorrectReadData());
        Assert.assertTrue("Is error", handler.isError());
        Assert.assertEquals("Initialization error code", -9, handler.getErrorCode());
    }

    @Test
    public void handleTest() throws InterruptedException {
        //before
        Mockito.when(reader.getDht()).thenReturn(new DHT(BigDecimal.valueOf(12.3), BigDecimal.valueOf(35.4)));
        //when
        handler.handle();
        //then
        Assert.assertEquals("Temperature", BigDecimal.valueOf(12.3), handler.getTemperature());
        Assert.assertEquals("Humidity", BigDecimal.valueOf(35.4), handler.getHumidity());
        Assert.assertFalse("Is Error", handler.isError());
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
        Assert.assertEquals("Temperature", BigDecimal.valueOf(12.2), handler.getTemperature());
        Assert.assertEquals("Humidity", BigDecimal.valueOf(22.2), handler.getHumidity());
        Assert.assertFalse("Is Error", handler.isError());
    }
}