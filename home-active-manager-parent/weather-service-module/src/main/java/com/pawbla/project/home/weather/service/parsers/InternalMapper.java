package com.pawbla.project.home.weather.service.parsers;

import com.pawbla.project.home.weather.service.models.InternalMeasurement;
import org.springframework.stereotype.Component;

@Component("internal")
public class InternalMapper extends AbstractResponseMapper<InternalMeasurement> {

    @Override
    protected Class getValueType() {
        return InternalMeasurement.class;
    }

    @Override
    protected String getName() {
        return "InternalParser";
    }
}
