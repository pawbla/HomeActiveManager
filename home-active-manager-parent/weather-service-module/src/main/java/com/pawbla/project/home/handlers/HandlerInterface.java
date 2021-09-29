package com.pawbla.project.home.handlers;

import com.pawbla.project.home.models.Connector;
import com.pawbla.project.home.models.Response;
import com.pawbla.project.home.parsers.ParserInterface;

public interface HandlerInterface {

    void execute();
    void setParser(ParserInterface parser);
    void setConnector(Connector Connector);
    void setRecovery(int delay, int confTime);
    Response getResponse();
    String getResponseValue(String key);
}

