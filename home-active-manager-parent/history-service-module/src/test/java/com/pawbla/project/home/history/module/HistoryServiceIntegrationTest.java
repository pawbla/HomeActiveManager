package com.pawbla.project.home.history.module;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.pawbla.project.home.history.module.dao.WeatherHistoryDao;
import com.pawbla.project.home.history.module.model.WeatherMeasurement;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = AFTER_CLASS)
@ActiveProfiles("test")
class HistoryServiceIntegrationTest {

    private static final String FILE_PATH = "integration/mocks";
    private static final String WEATHER_SIMPLIFIED_URL = "/api/v1/weather/simplifiedMeasurement";

    @Autowired
    private WeatherHistoryDao weatherHistoryDao;

    static WireMockServer mockHttpServer = new WireMockServer(8082);

    public HistoryServiceIntegrationTest() throws IOException {
        mockHttpServer.start();
        setupMockWeatherServiceResponse(mockHttpServer);
    }

    @BeforeEach
    void init() throws InterruptedException {
        Thread.sleep(12000);
    }

    @AfterClass
    public static void teardown() throws Exception {
        mockHttpServer.stop();
    }

    @Test
    void shouldCorrectlyStoreData() {
        List<WeatherMeasurement> actualList = weatherHistoryDao.findAll();
        WeatherMeasurement actual1 = getByTimestamp(actualList, 1702318304L);
        WeatherMeasurement actual2 = getByTimestamp(actualList, 1702319304L);
        assertNotNull(actual1);
        assertNotNull(actual2);
        assertEquals(actual1.getTemperatureIn().stripTrailingZeros(), BigDecimal.valueOf(1.20).stripTrailingZeros());
        assertEquals(actual1.getHumidityIn().stripTrailingZeros(), BigDecimal.valueOf(35.3).stripTrailingZeros());
        assertEquals(actual1.getTemperatureOut().stripTrailingZeros(), BigDecimal.valueOf(8.56).stripTrailingZeros());
        assertEquals(actual1.getHumidityOut().stripTrailingZeros(), BigDecimal.valueOf(95.40).stripTrailingZeros());
        assertEquals(actual1.getPressure().stripTrailingZeros(), BigDecimal.valueOf(1007.43).stripTrailingZeros());

        assertEquals(actual2.getTemperatureIn().stripTrailingZeros(), BigDecimal.valueOf(32.10).stripTrailingZeros());
        assertNull(actual2.getHumidityIn());
        assertEquals(actual2.getTemperatureOut().stripTrailingZeros(), BigDecimal.valueOf(-2.01).stripTrailingZeros());
        assertEquals(actual2.getHumidityOut().stripTrailingZeros(), BigDecimal.valueOf(80.00).stripTrailingZeros());
        assertEquals(actual2.getPressure().stripTrailingZeros(), BigDecimal.valueOf(1250.21).stripTrailingZeros());
    }

    private static void setupMockWeatherServiceResponse(WireMockServer mockHttpServer) throws IOException {
        mockHttpServer
                .stubFor(get(urlPathMatching(WEATHER_SIMPLIFIED_URL))
                        .inScenario("Weather")
                        .whenScenarioStateIs(STARTED)
                        .willReturn(prepareOkResponse("/weather1.json"))
                        .willSetStateTo("Bad request"));
        mockHttpServer
                .stubFor(get(urlPathMatching(WEATHER_SIMPLIFIED_URL))
                        .inScenario("Weather")
                        .whenScenarioStateIs("Bad request")
                        .willReturn(badRequest())
                        .willSetStateTo("Second response"));
        mockHttpServer
                .stubFor(get(urlPathMatching(WEATHER_SIMPLIFIED_URL))
                        .inScenario("Weather")
                        .whenScenarioStateIs("Second response")
                        .willReturn(prepareOkResponse("/weather2.json")));
    }

    private static ResponseDefinitionBuilder prepareOkResponse(String fileName) throws IOException {
        return aResponse().withBody(readFile(FILE_PATH + fileName))
                .withStatus(200)
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }

    private static WeatherMeasurement getByTimestamp(List<WeatherMeasurement> actualList, Long timestamp) {
        return actualList.stream()
                .filter(m -> m.getEpochDateTimeStamp().longValue() == timestamp.longValue())
                .findAny().orElse(null);
    }

    private static String readFile(String path) throws IOException {
        File resource = new ClassPathResource(path).getFile();
        return Files.readString(resource.toPath());
    }

}