package com.pawbla.project.home.weather.service.handlers;

import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.Response;
import com.pawbla.project.home.weather.service.models.Connector;

public interface HandlerInterface {

    void execute();
    void setConnector(Connector Connector);
    void setRecovery(int delay, int confTime);
    Response getResponse();
    Measurement getMeasurement();
}

