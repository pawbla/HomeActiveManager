package com.pawbla.project.home.weather.service.processor;

import com.pawbla.project.home.weather.service.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MoonPhaseProcessor {

    private final DateTimeUtils dateTimeUtils;
    private int moonPhaseValue;

    @Autowired
    public MoonPhaseProcessor(DateTimeUtils dateTimeUtils) {
        this.dateTimeUtils = dateTimeUtils;
        this.moonPhaseValue = 0;
    }

    public void calc() {
        int year = dateTimeUtils.getCurrentYear();
        int month = dateTimeUtils.getCurrentMonth();
        int day = dateTimeUtils.getCurrentDay();
        int hour = dateTimeUtils.getCurrentHour();
        int minute = dateTimeUtils.getCurrentMinute();
        int seconds = dateTimeUtils.getCurrentSecond();

        if (month <= 2) {
            month = month + 12;
            year = year - 1;
        }

        int A = (int) year / 100;
        int b = 2 - A + (int) (A / 4);

        double jdp = (int) (365.25 * (year + 4716)) + (int) (30.6001 * (month + 1)) + day + b +
                ((hour + minute/60.0 + seconds/3600.0) / 24) - 1524.5;

        double phi1 = calcPhi1(jdp);
        double phi2 = calcPhi2(jdp);

        if ((phi2 - phi1) < 0) {
            phi1 = -1 * phi1;
        }

        moonPhaseValue =  (int) (100 * phi1);
    }

    public int getMoonPhaseValue() {
        return moonPhaseValue;
    }

    private double calcPhi1(double jdp) {
        double tzd = (jdp - 2451545) / 36525;
        double elm = rang(297.8502042 + 445267.1115168 * tzd - (0.00163 * tzd * tzd) + tzd*tzd*tzd
                / 545868 - tzd*tzd*tzd*tzd / 113065000);
        double ams = rang(357.5291092 + 35999.0502909 * tzd - 0.0001536 * tzd * tzd + tzd*tzd*tzd / 24490000);
        double aml = rang(134.9634114 + 477198.8676313 * tzd - 0.008997 * tzd * tzd + tzd*tzd*tzd
                / 69699 - tzd*tzd*tzd*tzd / 14712000);
        double asd = 180 - elm -   (6.289 * Math.sin((3.1415926535 / 180) * ((aml)))) +
                (2.1 * Math.sin((3.1415926535 / 180) * ((ams)))) -
                (1.274 * Math.sin((3.1415926535 / 180) * (((2 * elm) - aml)))) -
                (0.658 * Math.sin((3.1415926535 / 180) * ((2 * elm)))) -
                (0.214 * Math.sin((3.1415926535 / 180) * ((2 * aml)))) -
                (0.11 * Math.sin((3.1415926535 / 180) * ((elm))));
        return (1 + Math.cos((3.1415926535 / 180) * (asd))) / 2;
    }

    private double calcPhi2(double jdp) {
        double tzd = (jdp + (0.5 / 24) - 2451545) / 36525;
        double elm = rang(297.8502042 + 445267.1115168 * tzd - (0.00163 * tzd * tzd) +
                tzd*tzd*tzd / 545868 - tzd*tzd*tzd*tzd / 113065000);
        double ams = rang(357.5291092 + 35999.0502909 * tzd - 0.0001536 * tzd * tzd + tzd*tzd*tzd / 24490000);
        double aml = rang(134.9634114 + 477198.8676313 * tzd - 0.008997 * tzd * tzd +
                tzd*tzd*tzd / 69699 - tzd*tzd*tzd*tzd / 14712000);
        double asd = 180 - elm -   (6.289 * Math.sin((3.1415926535 / 180) * ((aml)))) +
                (2.1 * Math.sin((3.1415926535 / 180) * ((ams)))) -
                (1.274 * Math.sin((3.1415926535 / 180) * (((2 * elm) - aml)))) -
                (0.658 * Math.sin((3.1415926535 / 180) * ((2 * elm)))) -
                (0.214 * Math.sin((3.1415926535 / 180) * ((2 * aml)))) -
                (0.11 * Math.sin((3.1415926535 / 180) * ((elm))));
        return (1 + Math.cos((3.1415926535 / 180) * (asd))) / 2;
    }

    private double rang(double value) {
        double b = value / 360;
        double A = 360 * (b - (int)b);
        if (A < 0) {
            A = A + 360;
        }
        return A;
    }
}