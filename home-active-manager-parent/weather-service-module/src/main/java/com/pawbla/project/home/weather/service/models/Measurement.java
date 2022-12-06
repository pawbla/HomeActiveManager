package com.pawbla.project.home.weather.service.models;

public class Measurement {
    protected static final String DEFAULT_VALUE = "-";

    private boolean isError;
    private String date;

    public Measurement() {
        this.isError = false;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public boolean isError() {
        return isError;
    }
}
