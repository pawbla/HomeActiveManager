package com.pawbla.project.home.weather.service.rest;

import com.pawbla.project.home.weather.service.models.Connector;

public interface RestInterface {
    void setConnector(Connector connector);
    void execute();
}
