package com.pawbla.project.home.weather.service.registry;

import com.pawbla.project.home.weather.service.models.Connector;

import java.util.Set;

public interface ConnectorsRegistryInterface {

    public void registerConnector(Connector connector);
    public Connector getConnector(String name);
    public Set<String> getNamesList();
}
