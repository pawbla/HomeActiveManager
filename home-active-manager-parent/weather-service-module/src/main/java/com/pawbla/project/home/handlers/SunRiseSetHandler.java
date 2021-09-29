package com.pawbla.project.home.handlers;

import com.pawbla.project.home.connectors.ConnectorInterface;
import com.pawbla.project.home.parsers.ParserInterface;
import com.pawbla.project.home.registry.ConnectorsRegistryInterface;
import com.pawbla.project.home.rest.RestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("sunRiseSet")
public class SunRiseSetHandler extends AbstractHandler {

    @Autowired
    public SunRiseSetHandler(RestInterface restConnector, @Qualifier("sunRiseSet") ParserInterface parser,
                             @Qualifier("sunRiseSet") ConnectorInterface connector, ConnectorsRegistryInterface registry) {
        super(restConnector, registry);
        this.setConnector(connector.getConnector());
        this.setParser(parser);
    }
}