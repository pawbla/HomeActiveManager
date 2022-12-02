package com.pawbla.project.home.weather.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WindSpeed {
    @JsonProperty("Metric")
    private Metric metric;

    public Metric getMetric() {
        return metric;
    }
}
