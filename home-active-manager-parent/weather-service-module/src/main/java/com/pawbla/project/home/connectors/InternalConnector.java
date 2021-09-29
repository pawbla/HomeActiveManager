package com.pawbla.project.home.connectors;

import com.pawbla.project.home.builders.ConnectorBuilder;
import com.pawbla.project.home.builders.RequestBuilder;
import com.pawbla.project.home.models.Connector;
import com.pawbla.project.home.models.Request;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * Connector to fetch datas from internal service
 * @author blach
 *
 */
@Component
@Qualifier("internal")
public class InternalConnector implements ConnectorInterface {

    /* Connector values */
    private static final String NAME = "internal";
    private static final String PROVIDER = "Internal ESP8266";
    private static final String LINK = "";

    private Connector connector;

    public InternalConnector (@Value("${custom.ipInternalSensor}") String url,
                              @Value("${custom.intSensorPassword}") String password) {
        this.connector = this.buildConnector(this.buildRequest(url, password));
    }

    @Override
    public Connector getConnector() {
        return connector;
    }

    private Request buildRequest(String url, String password) {
        Request request = new RequestBuilder()
                .setURL(url)
                .setHttpMethod(HttpMethod.GET)
                .addContentType(MediaType.APPLICATION_JSON)
                .addHeader("Authorization", password)
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