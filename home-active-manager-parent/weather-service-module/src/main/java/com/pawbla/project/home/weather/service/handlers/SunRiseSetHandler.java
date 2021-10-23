package com.pawbla.project.home.weather.service.handlers;

import com.pawbla.project.home.weather.service.connectors.ConnectorInterface;
import com.pawbla.project.home.weather.service.parsers.ParserInterface;
import com.pawbla.project.home.weather.service.registry.ConnectorsRegistryInterface;
import com.pawbla.project.home.weather.service.rest.RestInterface;
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