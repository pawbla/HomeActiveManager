package com.pawbla.project.home.weather.service.controllers.parsers;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import com.pawbla.project.home.weather.service.models.AirLyMeasurement;
import com.pawbla.project.home.weather.service.models.airly.Item;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("history")
public class HistoryParser extends AbstractParser<AirLyMeasurement> {

    private static final String PRESSURE_OBJ_NAME = "PRESSURE";

    private final HandlerInterface airLy;

    public HistoryParser(@Qualifier("AirLy") HandlerInterface airLy) {
        this.airLy = airLy;
    }

    @Override
    public JSONObject getParsedObject() {
        AirLyMeasurement measurement = getMeasurement(airLy);
        return new JSONObject()
                .put("isError", measurement.isError())
                .put("pressure", prepareHistory(measurement.getHistory()));
    }

    private JSONArray prepareHistory(List<Item> items) {
        JSONArray history = new JSONArray();
        items.stream().forEach(item -> history.put(prepareHistoryObj(item)));
        return history;
    }

    private JSONObject prepareHistoryObj(final Item item) {
        return new JSONObject()
                .put("date", item.getToDateTime())
                .put("value", roundDouble(getValueByName(item.getValues(), PRESSURE_OBJ_NAME)));
    }
}
