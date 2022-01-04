package com.pawbla.project.home.weather.service.models;

public class AirlyMeasurement implements Measurement {
    private String temperature;
    private String humidity;
    private String pressure;
    private String pm1;
    private String pm10;
    private String pm25;
    private String caqi;
    private String caqiColour;
    private String pm10percent;

    public String getCaqiColour() {
        return caqiColour;
    }

    public void setCaqiColour(String caqiColour) {
        this.caqiColour = caqiColour;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getPm1() {
        return pm1;
    }

    public void setPm1(String pm1) {
        this.pm1 = pm1;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getCaqi() {
        return caqi;
    }

    public void setCaqi(String caqi) {
        this.caqi = caqi;
    }

    public String getPm10percent() {
        return pm10percent;
    }

    public void setPm10percent(String pm10percent) {
        this.pm10percent = pm10percent;
    }

    public String getPm25percent() {
        return pm25percent;
    }

    public void setPm25percent(String pm25percent) {
        this.pm25percent = pm25percent;
    }

    private String pm25percent;

}
