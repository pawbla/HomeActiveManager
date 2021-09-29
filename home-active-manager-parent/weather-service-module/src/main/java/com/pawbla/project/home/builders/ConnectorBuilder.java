package com.pawbla.project.home.builders;

import com.pawbla.project.home.models.Connector;
import com.pawbla.project.home.models.Request;

public class ConnectorBuilder {

    private String name;
    private String provider;
    private String linkToProviderPage;
    private Request request;

    public ConnectorBuilder addName(String name) {
        this.name = name;
        return this;
    }

    public ConnectorBuilder addProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public ConnectorBuilder addLinkToProvider(String linkToProviderPage) {
        this.linkToProviderPage = linkToProviderPage;
        return this;
    }

    public ConnectorBuilder addRequest(Request request) {
        this.request = request;
        return this;
    }

    public Connector build() {
        return new Connector(name, provider, linkToProviderPage, request);
    }
}