package com.pawbla.project.home.history.module.dao;

import com.pawbla.project.home.history.module.model.DateTimeHistory;
import com.pawbla.project.home.history.module.model.StatsValue;
import com.pawbla.project.home.history.module.model.ValidData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pawbla.project.home.history.module.dao.QueryUtils.*;

@Repository
@Transactional
public interface DateTimeHistoryDao extends JpaRepository<DateTimeHistory, Long> {

    @Query(STAT_TEMP_IN_PER_DAY)
    List<StatsValue> findTempInPerDay(@Param("tmpYear") String tmpYear, @Param("tmpMonth") String tmpMonth);

    @Query(STAT_HUMIDITY_IN_PER_DAY)
    List<StatsValue> findHumInPerDay(@Param("tmpYear") String tmpYear, @Param("tmpMonth") String tmpMonth);

    @Query(STAT_TEMP_OUT_PER_DAY)
    List<StatsValue> findTempOutPerDay(@Param("tmpYear") String tmpYear, @Param("tmpMonth") String tmpMonth);

    @Query(STAT_HUMIDITY_OUT_PER_DAY)
    List<StatsValue> findHumOutPerDay(@Param("tmpYear") String tmpYear, @Param("tmpMonth") String tmpMonth);

    @Query(STAT_PRESSURE_PER_DAY)
    List<StatsValue> findPressurePerDay(@Param("tmpYear") String tmpYear, @Param("tmpMonth") String tmpMonth);

    @Query(STAT_TEMP_IN_PER_MONTH)
    List<StatsValue> findTempInPerMonth(@Param("tmpYear") String tmpYear);

    @Query(STAT_HUMIDITY_IN_PER_MONTH)
    List<StatsValue> findHumInPerMonth(@Param("tmpYear") String tmpYear);

    @Query(STAT_TEMP_OUT_PER_MONTH)
    List<StatsValue> findTempOutPerMonth(@Param("tmpYear") String tmpYear);

    @Query(STAT_HUMIDITY_OUT_PER_MONTH)
    List<StatsValue> findHumOutPerMonth(@Param("tmpYear") String tmpYear);

    @Query(STAT_PRESSURE_PER_MONTH)
    List<StatsValue> findPressurePerMonth(@Param("tmpYear") String tmpYear);

    @Query(VALID_DATES)
    List<ValidData> findValidData();
}
