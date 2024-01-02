package com.pawbla.project.home.history.module.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT epochDateTimeStamp as timestamp, " +
        "FROM_UNIXTIME(epochDateTimeStamp, 'YYYY') as tmpYear, " +
        "FROM_UNIXTIME(epochDateTimeStamp, 'MM') as tmpMonth, " +
        "FROM_UNIXTIME(epochDateTimeStamp, 'dd') as tmpDay FROM weatherHistory")
public class DateTimeHistory {
    @Id
    @Column
    private Long timestamp;
    @Column
    private String tmpYear;
    @Column
    private String tmpMonth;
    @Column
    private String tmpDay;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "timestamp", referencedColumnName = "epochDateTimeStamp")
    private WeatherMeasurement weatherMeasurement;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTmpYear() {
        return tmpYear;
    }

    public void setTmpYear(String tmpYear) {
        this.tmpYear = tmpYear;
    }

    public String getTmpMonth() {
        return tmpMonth;
    }

    public void setTmpMonth(String tmpMonth) {
        this.tmpMonth = tmpMonth;
    }

    public String getTmpDay() {
        return tmpDay;
    }

    public void setTmpDay(String tmpDay) {
        this.tmpDay = tmpDay;
    }

    public WeatherMeasurement getWeatherMeasurement() {
        return weatherMeasurement;
    }

    public void setWeatherMeasurement(WeatherMeasurement weatherMeasurement) {
        this.weatherMeasurement = weatherMeasurement;
    }
}
