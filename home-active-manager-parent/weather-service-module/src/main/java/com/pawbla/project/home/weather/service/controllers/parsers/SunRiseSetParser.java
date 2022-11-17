package com.pawbla.project.home.weather.service.controllers.parsers;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.models.SunRiseSetMeasurement;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


@Component("sunRiseSetParser")
public class SunRiseSetParser extends AbstractParser<SunRiseSetMeasurement> {
    private static final String SUN_RISE = "rise";
    private static final String SUN_SET = "set";
    private static final String DAY_LENGTH = "dayLength";
    private static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String OUT_DATE_FORMAT = "HH:mm";

    private final HandlerInterface sunRiseSet;

    private final DateTimeFormatter inFormatter;
    private final SimpleDateFormat outFormatter;
    private final SimpleDateFormat outFormatter2;

    public SunRiseSetParser(@Qualifier("sunRiseSet") HandlerInterface sunRiseSet) {
        this.sunRiseSet = sunRiseSet;
        inFormatter = DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT).withZoneUTC();
        outFormatter = new SimpleDateFormat(OUT_DATE_FORMAT);
        outFormatter.setTimeZone(this.getCurrentTimeZone());
        outFormatter2 = new SimpleDateFormat(OUT_DATE_FORMAT);
        outFormatter2.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public JSONObject getParsedObject() {
        SunRiseSetMeasurement measurement = getSunMeasurement(sunRiseSet);
        return new JSONObject()
                .put(SUN_RISE, getSunRiseObject(measurement))
                .put(SUN_SET, getSunSetObject(measurement))
                .put(DAY_LENGTH, getDayLengthObject(measurement));
    }

    private JSONObject getSunRiseObject(SunRiseSetMeasurement measurement) {
        return getValue(outFormatter.format(DateTime.parse(measurement.getSunrise(), inFormatter).toDate()), measurement.isError());
    }

    private JSONObject getSunSetObject(SunRiseSetMeasurement measurement) {
        return getValue(outFormatter.format(DateTime.parse(measurement.getSunset(), inFormatter).toDate()), measurement.isError());
    }

    private JSONObject getDayLengthObject(SunRiseSetMeasurement measurement) {
        return getValue(outFormatter2.format(new Date((long) (measurement.getDayLength() * 1000))), measurement.isError());
    }

    private TimeZone getCurrentTimeZone() {
        return Calendar.getInstance().getTimeZone();
    }

}
