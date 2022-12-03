package com.pawbla.project.home.weather.service.models.accuweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pawbla.project.home.weather.service.models.accuweather.Metric;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Visibility {
    @JsonProperty("Metric")
    private Metric metric;

    public Metric getMetric() {
        return metric;
    }
}
