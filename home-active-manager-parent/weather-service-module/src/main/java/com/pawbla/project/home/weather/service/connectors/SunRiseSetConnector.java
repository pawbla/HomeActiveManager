package com.pawbla.project.home.weather.service.connectors;

import com.pawbla.project.home.weather.service.builders.ConnectorBuilder;
import com.pawbla.project.home.weather.service.builders.RequestBuilder;
import com.pawbla.project.home.weather.service.models.Connector;
import com.pawbla.project.home.weather.service.models.Request;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * Connector to fetch datas from sun rise service
 * using SunRise API
 * @author blach
 *
 */
@Component
@Qualifier("sunRiseSet")
public class SunRiseSetConnector implements ConnectorInterface {

    /* Connector values */
    private static final String NAME = "sun";
    private static final String PROVIDER = "Sunrise Sunset";
    private static final String LINK = "https://sunrise-sunset.org/";

    private Connector connector;

    public SunRiseSetConnector(@Value("${custom.ipSunSetRise}") String url) {
        this.connector = this.buildConnector(this.buildRequest(url));
    }

    @Override
    public Connector getConnector() {
        return connector;
    }

    private Request buildRequest(String url) {
        Request request = new RequestBuilder()
                .setURL(url)
                .setHttpMethod(HttpMethod.GET)
                .addContentType(MediaType.APPLICATION_JSON)
                .build();
        return request;
    }

    private Connector buildConnector(Request request) {
        Connector connector = new ConnectorBuilder()
                .addName(NAME)
                .addProvider(PROVIDER)
                .addLinkToProvider(LINK)
                .addRequest(request)
                .build();
        return connector;
    }

}
