package com.pawbla.project.home;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Integration test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherServiceMain.class)
public class IntegrationTest
{

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
