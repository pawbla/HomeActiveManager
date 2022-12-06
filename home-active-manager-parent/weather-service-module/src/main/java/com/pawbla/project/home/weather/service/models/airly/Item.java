package com.pawbla.project.home.weather.service.models.airly;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Item {

    @JsonProperty("fromDateTime")
    private String fromDateTime;
    @JsonProperty("tillDateTime")
    private String toDateTime;
    @JsonProperty("values")
    private List<Value> values;
    @JsonProperty("indexes")
    private List<Index> indexes;
    @JsonProperty("standards")
    private List<Standard> standards;

    public String getFromDateTime() {
        return fromDateTime;
    }

    public String getToDateTime() {
        return toDateTime;
    }

    public List<Value> getValues() {
        return values;
    }

    public List<Index> getIndexes() {
        return indexes;
    }

    public List<Standard> getStandards() {
        return standards;
    }
}
