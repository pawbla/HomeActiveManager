package com.pawbla.project.home.weather.service.models.airly;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Current {

    private final String fromDateTime;
    private final String toDateTime;
    private final List<Value> values;
    private final List<Index> indexes;
    private final List<Standard> standards;

    public Current() {
        this.fromDateTime = "";
        this.toDateTime = "";
        this.values = null;
        this.indexes = null;
        this.standards = null;
    }

    @JsonCreator
    public Current(@JsonProperty("fromDateTime") String fromDateTime, @JsonProperty("tillDateTime") String toDateTime,
                   @JsonProperty("values") List<Value> values, @JsonProperty("indexes") List<Index> indexes,
                   @JsonProperty("standards") List<Standard> standards) throws Exception {
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.values = values;
        this.indexes = indexes;
        this.standards = standards;
        if (values.size() == 0 || standards.size() == 0) {
            throw new Exception("Parsing json error");
        }
    }

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
