package com.pawbla.project.home.history.module.dao;

public class QueryUtils {

    public static final String STAT_TEMP_IN_PER_DAY = "SELECT tmpYear as tmpYear, tmpMonth as tmpMonth, tmpDay as tmpDay, " +
            "ROUND(AVG(weatherMeasurement.temperatureIn),2) as meanVal, MIN(weatherMeasurement.temperatureIn) as minVal, MAX(weatherMeasurement.temperatureIn) as maxVal " +
            "FROM DateTimeHistory WHERE tmpYear = :tmpYear AND tmpMonth = :tmpMonth GROUP BY tmpDay ORDER BY tmpDay";

    public static final String STAT_HUMIDITY_IN_PER_DAY = "SELECT tmpYear as tmpYear, tmpMonth as tmpMonth, tmpDay as tmpDay, " +
            "ROUND(AVG(weatherMeasurement.humidityIn),2) as meanVal, MIN(weatherMeasurement.humidityIn) as minVal, MAX(weatherMeasurement.humidityIn) as maxVal " +
            "FROM DateTimeHistory WHERE tmpYear = :tmpYear AND tmpMonth = :tmpMonth GROUP BY tmpDay ORDER BY tmpDay";

    public static final String STAT_TEMP_OUT_PER_DAY = "SELECT tmpYear as tmpYear, tmpMonth as tmpMonth, tmpDay as tmpDay, " +
            "ROUND(AVG(weatherMeasurement.temperatureOut),2) as meanVal, MIN(weatherMeasurement.temperatureOut) as minVal, MAX(weatherMeasurement.temperatureOut) as maxVal " +
            "FROM DateTimeHistory WHERE tmpYear = :tmpYear AND tmpMonth = :tmpMonth GROUP BY tmpDay ORDER BY tmpDay";

    public static final String STAT_HUMIDITY_OUT_PER_DAY = "SELECT tmpYear as tmpYear, tmpMonth as tmpMonth, tmpDay as tmpDay, " +
            "ROUND(AVG(weatherMeasurement.humidityOut),2) as meanVal, MIN(weatherMeasurement.humidityOut) as minVal, MAX(weatherMeasurement.humidityOut) as maxVal " +
            "FROM DateTimeHistory WHERE tmpYear = :tmpYear AND tmpMonth = :tmpMonth GROUP BY tmpDay ORDER BY tmpDay";

    public static final String STAT_PRESSURE_PER_DAY = "SELECT tmpYear as tmpYear, tmpMonth as tmpMonth, tmpDay as tmpDay, " +
            "ROUND(AVG(weatherMeasurement.pressure),2) as meanVal, MIN(weatherMeasurement.pressure) as minVal, MAX(weatherMeasurement.pressure) as maxVal " +
            "FROM DateTimeHistory WHERE tmpYear = :tmpYear AND tmpMonth = :tmpMonth GROUP BY tmpDay ORDER BY tmpDay";

    public static final String STAT_TEMP_IN_PER_MONTH = "SELECT tmpYear as tmpYear, tmpMonth as tmpMonth, " +
            "ROUND(AVG(weatherMeasurement.temperatureIn),2) as meanVal, MIN(weatherMeasurement.temperatureIn) as minVal, MAX(weatherMeasurement.temperatureIn) as maxVal " +
            "FROM DateTimeHistory WHERE tmpYear = :tmpYear GROUP BY tmpMonth ORDER BY tmpMonth";

    public static final String STAT_HUMIDITY_IN_PER_MONTH = "SELECT tmpYear as tmpYear, tmpMonth as tmpMonth, " +
            "ROUND(AVG(weatherMeasurement.humidityIn),2) as meanVal, MIN(weatherMeasurement.humidityIn) as minVal, MAX(weatherMeasurement.humidityIn) as maxVal " +
            "FROM DateTimeHistory WHERE tmpYear = :tmpYear  GROUP BY tmpMonth ORDER BY tmpMonth";

    public static final String STAT_TEMP_OUT_PER_MONTH = "SELECT tmpYear as tmpYear, tmpMonth as tmpMonth, " +
            "ROUND(AVG(weatherMeasurement.temperatureOut),2) as meanVal, MIN(weatherMeasurement.temperatureOut) as minVal, MAX(weatherMeasurement.temperatureOut) as maxVal " +
            "FROM DateTimeHistory WHERE tmpYear = :tmpYear GROUP BY tmpMonth ORDER BY tmpMonth";

    public static final String STAT_HUMIDITY_OUT_PER_MONTH = "SELECT tmpYear as tmpYear, tmpMonth as tmpMonth, " +
            "ROUND(AVG(weatherMeasurement.humidityOut),2) as meanVal, MIN(weatherMeasurement.humidityOut) as minVal, MAX(weatherMeasurement.humidityOut) as maxVal " +
            "FROM DateTimeHistory WHERE tmpYear = :tmpYear GROUP BY tmpMonth ORDER BY tmpMonth";

    public static final String STAT_PRESSURE_PER_MONTH = "SELECT tmpYear as tmpYear, tmpMonth as tmpMonth, " +
            "ROUND(AVG(weatherMeasurement.pressure),2) as meanVal, MIN(weatherMeasurement.pressure) as minVal, MAX(weatherMeasurement.pressure) as maxVal " +
            "FROM DateTimeHistory WHERE tmpYear = :tmpYear GROUP BY tmpMonth ORDER BY tmpMonth";

}
