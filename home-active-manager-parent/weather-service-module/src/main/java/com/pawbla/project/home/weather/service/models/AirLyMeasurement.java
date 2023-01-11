package com.pawbla.project.home.weather.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pawbla.project.home.weather.service.models.airly.Current;
import com.pawbla.project.home.weather.service.models.airly.Item;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AirLyMeasurement extends Measurement {
    @JsonProperty("current")
    private Current current;
    @JsonProperty("history")
    private List<Item> history;
    @JsonProperty("forecast")
    private List<Item> forecast;

    public Current getCurrent() {
        return current;
    }

    public List<Item> getHistory() {
        return history;
    }

    public List<Item> getForecast() {
        return forecast;
    }
}
