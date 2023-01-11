package com.pawbla.project.home.weather.service.parsers;

import com.pawbla.project.home.weather.service.models.AirLyMeasurement;
import org.springframework.stereotype.Component;

@Component("AirLy")
public class AirLyMapper extends AbstractResponseMapper<AirLyMeasurement> {

    @Override
    protected Class<AirLyMeasurement> getValueType() {
        return AirLyMeasurement.class;
    }

    @Override
    protected String getName() {
        return "AirLy";
    }

    @Override
    protected AirLyMeasurement getMeasurementDefaultObject() {
        return new AirLyMeasurement();
    }
}
