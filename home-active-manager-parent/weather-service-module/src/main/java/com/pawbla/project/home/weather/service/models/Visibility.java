package com.pawbla.project.home.weather.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Visibility {
    @JsonProperty("Metric")
    private Metric metric;

    public Metric getMetric() {
        return metric;
    }
}
