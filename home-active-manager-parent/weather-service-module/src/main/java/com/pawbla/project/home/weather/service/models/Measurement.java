package com.pawbla.project.home.weather.service.models;

public class Measurement {
    protected static final String DEFAULT_VALUE = "-";

    private boolean isError;
    private String date;

    public Measurement() {
        this.isError = false;
        this.date = "2000-00-00";
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public boolean isError() {
        return isError;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
