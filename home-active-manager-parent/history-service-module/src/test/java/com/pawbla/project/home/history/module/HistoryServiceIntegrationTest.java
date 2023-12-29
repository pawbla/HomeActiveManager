package com.pawbla.project.home.history.module;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.pawbla.project.home.history.module.dao.WeatherHistoryDao;
import com.pawbla.project.home.history.module.model.WeatherMeasurement;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = AFTER_CLASS)
@ActiveProfiles("test")
class HistoryServiceIntegrationTest {

    private static final String FILE_PATH = "integration/mocks";
    private static final String WEATHER_SIMPLIFIED_URL = "/api/v1/weather/simplifiedMeasurement";
    private static final String EXPECTED_PATH = "integration/expected/";

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

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

    @Test
    void shouldGetValidData() throws IOException {
        String actual = restTemplate.getForObject(getUri("validData"), String.class);
        String expected = readFile(EXPECTED_PATH + "validData.json");
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void shouldGetYearTempInData() throws IOException {
        String actual = restTemplate.getForObject(getUri("year?type=tempIn&year=2023"), String.class);
        String expected = readFile(EXPECTED_PATH + "tempInYear.json");
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void shouldGetYearHumInData() throws IOException {
        String actual = restTemplate.getForObject(getUri("year?type=humIn&year=2023"), String.class);
        String expected = readFile(EXPECTED_PATH + "humInYear.json");
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void shouldGetYearTempOutData() throws IOException {
        String actual = restTemplate.getForObject(getUri("year?type=tempOut&year=2023"), String.class);
        String expected = readFile(EXPECTED_PATH + "tempOutYear.json");
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void shouldGetYearHumOutData() throws IOException {
        String actual = restTemplate.getForObject(getUri("year?type=humOut&year=2023"), String.class);
        String expected = readFile(EXPECTED_PATH + "humOutYear.json");
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void shouldGetYearPressureData() throws IOException {
        String actual = restTemplate.getForObject(getUri("year?type=pressure&year=2023"), String.class);
        String expected = readFile(EXPECTED_PATH + "pressureYear.json");
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void shouldHandleIncorrectTypeForYear() {
        ResponseEntity<String> response = restTemplate.getForEntity(getUri("year?type=incorrect&year=2023"), String.class);
        assertEquals(404, response.getStatusCode().value());
        assertEquals("Incorrect type value", response.getBody());
    }

    @Test
    void shouldGetMonthTempInData() throws IOException {
        String actual = restTemplate.getForObject(getUri("month?type=tempIn&year=2023&month=01"), String.class);
        String expected = readFile(EXPECTED_PATH + "tempInMonth.json");
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void shouldGetMonthHumInData() throws IOException {
        String actual = restTemplate.getForObject(getUri("month?type=humIn&year=2023&month=01"), String.class);
        String expected = readFile(EXPECTED_PATH + "humInMonth.json");
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void shouldGetMonthTempOutData() throws IOException {
        String actual = restTemplate.getForObject(getUri("month?type=tempOut&year=2023&month=01"), String.class);
        String expected = readFile(EXPECTED_PATH + "tempOutMonth.json");
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void shouldGetMonthHumOutData() throws IOException {
        String actual = restTemplate.getForObject(getUri("month?type=humOut&year=2023&month=01"), String.class);
        String expected = readFile(EXPECTED_PATH + "humOutMonth.json");
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void shouldGetMonthPressureData() throws IOException {
        String actual = restTemplate.getForObject(getUri("month?type=pressure&year=2023&month=01"), String.class);
        String expected = readFile(EXPECTED_PATH + "pressureMonth.json");
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void shouldHandleIncorrectTypeForMonth() {
        ResponseEntity<String> response = restTemplate.getForEntity(getUri("month?type=incorrect&year=2023&month=01"), String.class);
        assertEquals(404, response.getStatusCode().value());
        assertEquals("Incorrect type value", response.getBody());
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

    private String getUri(String path) {
        return "http://localhost:" + port + "/api/v1/history/weather/" + path;
    }

}