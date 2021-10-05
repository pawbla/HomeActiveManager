package com.pawbla.project.home.rest;

import com.pawbla.project.home.models.Connector;

public interface RestInterface {
    void setConnector(Connector connector);
    void execute();
}
