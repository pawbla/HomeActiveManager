package com.pawbla.project.home.history.module.dao;

import com.pawbla.project.home.history.module.model.WeatherMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface WeatherHistoryDao extends JpaRepository<WeatherMeasurement, Long> {

    List<WeatherMeasurement> findAll();

    WeatherMeasurement save(WeatherMeasurement entity);

    List<WeatherMeasurement> findByEpochDateTimeStampBetween(Long startTimeStamp, Long endTimeStamp);

    WeatherMeasurement findByEpochDateTimeStamp(Long startTimeStamp);
}
