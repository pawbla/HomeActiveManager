package com.pawbla.project.home.history.module.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name="weatherHistory")
public class WeatherMeasurement {

    @Id
    @Column(name="epochDateTimeStamp")
    @JsonProperty("timestamp")
    private Long epochDateTimeStamp;

    @Column(name="temperatureIn")
    private BigDecimal temperatureIn;

    @Column(name="humidityIn")
    private BigDecimal humidityIn;

    @Column(name="temperatureOut")
    private BigDecimal temperatureOut;

    @Column(name="humidityOut")
    private BigDecimal humidityOut;

    @Column(name="pressure")
    private BigDecimal pressure;

    public Long getEpochDateTimeStamp() {
        return epochDateTimeStamp;
    }

    public void setEpochDateTimeStamp(Long epochDateTimeStamp) {
        this.epochDateTimeStamp = epochDateTimeStamp;
    }

    public BigDecimal getTemperatureIn() {
        return temperatureIn;
    }

    public void setTemperatureIn(BigDecimal temperatureIn) {
        this.temperatureIn = temperatureIn;
    }

    public BigDecimal getHumidityIn() {
        return humidityIn;
    }

    public void setHumidityIn(BigDecimal humidityIn) {
        this.humidityIn = humidityIn;
    }

    public BigDecimal getTemperatureOut() {
        return temperatureOut;
    }

    public void setTemperatureOut(BigDecimal temperatureOut) {
        this.temperatureOut = temperatureOut;
    }

    public BigDecimal getHumidityOut() {
        return humidityOut;
    }

    public void setHumidityOut(BigDecimal humidityOut) {
        this.humidityOut = humidityOut;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public void setPressure(BigDecimal pressure) {
        this.pressure = pressure;
    }
}

