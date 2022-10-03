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
        Assert.assertEquals("Initialize temperature", 0, handler.getTemperature());
        Assert.assertEquals("Initialize humidity", 0, handler.getHumidity());
        Assert.assertEquals("Initialize date", LocalDateTime.of(2000, 1, 1, 1, 1, 1),
                handler.getLastCorrectReadData());
        Assert.assertFalse("No error", handler.isError());
    }

    @Test
    public void handleTest() throws InterruptedException {
        //before
        Mockito.when(reader.getDht()).thenReturn(new DHT(12, 35));
        //when
        handler.handle();
        //then
        Assert.assertEquals("Temperature", 12, handler.getTemperature());
        Assert.assertEquals("Humidity", 35, handler.getHumidity());
        Assert.assertFalse("Is Error", handler.isError());
    }

    @Test
    public void calculateAvgValuesTest() throws InterruptedException {
        //before
        Mockito.when(reader.getDht()).thenReturn(new DHT(10, 11));
        handler.handle();
        Mockito.when(reader.getDht()).thenReturn(new DHT(11, 43));
        handler.handle();
        Mockito.when(reader.getDht()).thenReturn(new DHT(15, 12));
        //when
        handler.handle();
        //then
        Assert.assertEquals("Temperature", 12, handler.getTemperature());
        Assert.assertEquals("Humidity", 22, handler.getHumidity());
        Assert.assertFalse("Is Error", handler.isError());
    }
}