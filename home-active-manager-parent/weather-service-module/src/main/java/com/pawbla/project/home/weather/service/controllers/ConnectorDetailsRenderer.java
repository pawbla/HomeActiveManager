package com.pawbla.project.home.weather.service.controllers;

import com.pawbla.project.home.weather.service.models.RequestCounter;
import com.pawbla.project.home.weather.service.registry.ConnectorsRegistryInterface;
import com.pawbla.project.home.weather.service.models.Connector;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectorDetailsRenderer implements Renderer {

    private ConnectorsRegistryInterface register;

    @Autowired
    public ConnectorDetailsRenderer(ConnectorsRegistryInterface register) {
        this.register = register;
    }

    @Override
    public String getJSON() {
        JSONObject response = new JSONObject();
        JSONArray array = new JSONArray();
        register.getNamesList().forEach(name -> {
            array.put(populateValue(name));
        });
        return response.put("connectors", array).toString();
    }

    private JSONObject populateValue(String name) {
        JSONObject response = new JSONObject();
        Connector connector = register.getConnector(name);
        response.put("name", connector.getName())
                .put("provider", connector.getProvider())
                .put("link", connector.getLinkToProviderPage())
                .put("position", connector.getSensorPosition())
                .put("responseCode", connector.getResponse().getResponseCode())
                .put("isError", connector.getResponse().isError())
                .put("errorMessage", connector.getResponse().getErrorMsg())
                .put("date", connector.getResponse().getDate())
                .put("dailyCounter", getCounter(connector.getDailyRequestCounter()))
                .put("sumCounter", getCounter(connector.getSumRequestCounter()));
        return response;
    }

    private JSONObject getCounter(RequestCounter requestCounter) {
        return new JSONObject()
                .put("requests", requestCounter.getRequestsCnt())
                .put("errors", requestCounter.getErrorResponseCnt());
    }

}