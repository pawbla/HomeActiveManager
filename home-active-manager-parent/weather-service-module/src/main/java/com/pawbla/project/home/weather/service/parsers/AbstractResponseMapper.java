package com.pawbla.project.home.weather.service.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractResponseMapper<T extends Measurement> implements ResponseMapper {
    private final Logger log = LogManager.getLogger(AbstractResponseMapper.class);

    ObjectMapper objectMapper;
    private Measurement measurement;

    public AbstractResponseMapper() {
        this.objectMapper = new ObjectMapper();
        this.measurement = getMeasurementDefaultObject();
    }

    @Override
    public Measurement map(Response response) {
        try {
            if (!response.isError()) {
                measurement = objectMapper.readValue(prepareBody(response.getBody()), getValueType());

            }
            measurement.setError(response.isError());
        } catch (JsonProcessingException e) {
            log.error("Parsing json failed for {}. Exception {}.", getName(), e.getMessage());
            measurement.setError(true);
        }
        return measurement;
    }

    protected String prepareBody(String body) {
        return body;
    }

    protected abstract Class<T> getValueType();

    protected abstract String getName();

    protected abstract T getMeasurementDefaultObject();
}
