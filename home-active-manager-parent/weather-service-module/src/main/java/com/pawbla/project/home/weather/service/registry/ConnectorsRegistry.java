package com.pawbla.project.home.weather.service.registry;

import com.pawbla.project.home.weather.service.models.Connector;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ConnectorsRegistry implements ConnectorsRegistryInterface {

    public Map<String, Connector> registry;

    public ConnectorsRegistry() {
        registry = new HashMap<>();
    }

    @Override
    public void registerConnector(Connector connector) {
        registry.put(connector.getName(), connector);
    }

    @Override
    public Connector getConnector(String name) {
        return registry.get(name);
    }

    @Override
    public Set<String> getNamesList() {
        return registry.keySet();
    }

}
