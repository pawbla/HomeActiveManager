package com.pawbla.project.home.weather.service.models;

import static com.pawbla.project.home.weather.service.utils.Constants.EMPTY;

public class MoonPhaseMeasurement extends Measurement {
    private String text = EMPTY;
    private String pictureNo = EMPTY;

    public void setMeasurements(String text, String pictureNo) {
        this.text = text;
        this.pictureNo = pictureNo;
    }

    public String getText() {
        return text;
    }

    public String getPictureNo() {
        return pictureNo;
    }
}
