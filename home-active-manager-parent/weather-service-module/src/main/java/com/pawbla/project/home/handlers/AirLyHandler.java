package com.pawbla.project.home.handlers;

import com.pawbla.project.home.connectors.ConnectorInterface;
import com.pawbla.project.home.parsers.ParserInterface;
import com.pawbla.project.home.registry.ConnectorsRegistryInterface;
import com.pawbla.project.home.rest.RestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("AirLy")
public class AirLyHandler extends AbstractHandler{

    @Autowired
    public AirLyHandler(RestInterface restConnector, @Qualifier("AirLy") ParserInterface parser,
                        @Qualifier("AirLy") ConnectorInterface connector, ConnectorsRegistryInterface registry) {
        super(restConnector, registry);
        this.setConnector(connector.getConnector());
        this.setParser(parser);
    }
}
