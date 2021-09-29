package com.pawbla.project.home.handlers;

import com.pawbla.project.home.connectors.ConnectorInterface;
import com.pawbla.project.home.parsers.ParserInterface;
import com.pawbla.project.home.registry.ConnectorsRegistryInterface;
import com.pawbla.project.home.rest.RestInterface;
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
