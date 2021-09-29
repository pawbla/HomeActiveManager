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
 * Connector to fetch datas from AirLy service
 * using AirLy API
 * @author blach
 *
 */
@Component
@Qualifier("AirLy")
public class AirLyConnector implements ConnectorInterface {

    /* Connector values */
    private static final String NAME = "airLy";
    private static final String PROVIDER = "AirLy";
    private static final String LINK = "https://www.airly.eu/";

    /*Request values */
    private static final String API_KEY_NAME = "apikey";
    private static final String ACCEPTED_LANG_KEY = "Accept-Language";
    private static final String ACCEPTED_LANG_VALUE = "pl";

    private Connector connector;

    public AirLyConnector(@Value("${custom.ipAirLy}") String url, @Value("${custom.apiKeyAirLy}") String apiKey) {
        this.connector = this.buildConnector(this.buildRequest(url, apiKey));
    }

    @Override
    public Connector getConnector() {
        return connector;
    }

    private Request buildRequest(String url, String apiKey) {
        Request request = new RequestBuilder()
                .setURL(url)
                .setHttpMethod(HttpMethod.GET)
                .addContentType(MediaType.APPLICATION_JSON)
                .addHeader(API_KEY_NAME, apiKey)
                .addHeader(ACCEPTED_LANG_KEY, ACCEPTED_LANG_VALUE)
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