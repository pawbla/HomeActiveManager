package com.pawbla.project.home.weather.service.handlers;

import com.pawbla.project.home.weather.service.connectors.ConnectorInterface;
import com.pawbla.project.home.weather.service.registry.ConnectorsRegistryInterface;
import com.pawbla.project.home.weather.service.parsers.old.ParserInterface;
import com.pawbla.project.home.weather.service.rest.RestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("accuWeather")
public class AccuWeatherHandler extends AbstractHandler {

    @Autowired
    public AccuWeatherHandler(RestInterface restConnector, @Qualifier("accuWeather") ParserInterface parser,
                              @Qualifier("accuWeather") ConnectorInterface connector, ConnectorsRegistryInterface registry) {
        super(restConnector, registry);
        this.setConnector(connector.getConnector());
        this.setParser(parser);
    }
}