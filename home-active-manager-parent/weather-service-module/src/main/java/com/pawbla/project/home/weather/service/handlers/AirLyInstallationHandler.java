package com.pawbla.project.home.weather.service.handlers;

import com.pawbla.project.home.weather.service.connectors.ConnectorInterface;
import com.pawbla.project.home.weather.service.parsers.old.ParserInterface;
import com.pawbla.project.home.weather.service.registry.ConnectorsRegistryInterface;
import com.pawbla.project.home.weather.service.rest.RestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AirLyInstallationHandler extends AbstractHandler {

    @Autowired
    public AirLyInstallationHandler(RestInterface restConnector, @Qualifier("AirLy") ParserInterface parser,
                                    @Qualifier("AirLy") ConnectorInterface connector, ConnectorsRegistryInterface registry) {
        super(restConnector, registry);
        this.setConnector(connector.getConnector());
        this.setParser(parser);
    }
}
