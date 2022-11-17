package com.pawbla.project.home.weather.service.models.old;

import com.pawbla.project.home.weather.service.models.Measurement;

import java.util.ArrayList;
import java.util.List;

import static com.pawbla.project.home.weather.service.utils.Constants.EMPTY;

public class AirlyMeasurement extends Measurement {
    private String temperature = EMPTY;
    private String humidity = EMPTY;
    private String pressure = EMPTY;
    private String pm1 = EMPTY;
    private String pm10 = EMPTY;
    private String pm25 = EMPTY;
    private String caqi = EMPTY;
    private String caqiColour = EMPTY;
    private String pm10percent = EMPTY;
    private String pm25percent = EMPTY;
    private List<AirPollutionForecast> airPollutionForecast = new ArrayList<>();
    private List<AirLyHistory> airLyHistory = new ArrayList<>();

    public void setMeasurements(String temperature, String humidity, String pressure, String pm1,
                            String pm10, String pm25, String caqi, String caqiColour, String pm10percent,
                            String pm25percent, List<AirPollutionForecast> airPollutionForecast,
                            List<AirLyHistory> airLyHistory) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.pm1 = pm1;
        this.pm10 = pm10;
        this.pm25 = pm25;
        this.caqi = caqi;
        this.caqiColour = caqiColour;
        this.pm10percent = pm10percent;
        this.pm25percent = pm25percent;
        this.airPollutionForecast = airPollutionForecast;
        this.airLyHistory = airLyHistory;
    }

    public String getCaqiColour() {
        return caqiColour;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getPm1() {
        return pm1;
    }

    public String getPm10() {
        return pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public String getCaqi() {
        return caqi;
    }

    public String getPm10percent() {
        return pm10percent;
    }

    public String getPm25percent() {
        return pm25percent;
    }

    public List<AirPollutionForecast> getAirPollutionForecast() {
        return airPollutionForecast;
    }

    public List<AirLyHistory> getAirLyHistory() {
        return airLyHistory;
    }
}
