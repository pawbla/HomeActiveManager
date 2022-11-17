package com.pawbla.project.home.weather.service.handlers;

import com.pawbla.project.home.weather.service.models.Connector;
import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.Response;
import com.pawbla.project.home.weather.service.parsers.old.ParserInterface;
import com.pawbla.project.home.weather.service.processor.MoonPhaseProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("moonPhase")
public class MoonPhaseHandler implements HandlerInterface {

    private MoonPhaseProcessor processor;
    private ParserInterface parser;
    private Measurement measurement;

    @Autowired
    public MoonPhaseHandler(MoonPhaseProcessor processor, @Qualifier("moonPhase") ParserInterface parser) {
        this.processor = processor;
        this.parser = parser;
    }

    @Override
    public void execute() {
        int moonPhase = processor.calc();
        measurement = parser.parse(moonPhase);
    }

    @Override
    public void setParser(ParserInterface parser) {
        //nothing to do
    }

    @Override
    public void setConnector(Connector Connector) {
        //nothing to do
    }

    @Override
    public void setRecovery(int delay, int confTime) {
        //nothing to do
    }

    @Override
    public Response getResponse() {
        return null;
    }

    @Override
    public Measurement getMeasurement() {
        return this.measurement;
    }
}
