package com.pawbla.project.home.weather.service.controllers.parsers;

import com.pawbla.project.home.weather.service.processor.MoonPhaseProcessor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component("moonPhase")
public class MoonPhaseParser implements ResponseParser {

    private static final String TEXT = "text";
    private static final String PICTURE = "picture";

    private MoonPhaseProcessor processor;

    public MoonPhaseParser(MoonPhaseProcessor processor) {
        this.processor = processor;
    }

    @Override
    public JSONObject getParsedObject() {
        int moonPhaseVal = processor.getMoonPhaseValue();
        return new JSONObject()
                .put(TEXT, getValue(getText(moonPhaseVal)))
                .put(PICTURE, getValue(getPictureNumber(moonPhaseVal)));
    }

    private JSONObject getValue(String value) {
        return new JSONObject()
                .put("value", value)
                .put("isError", false);
    }

    private String getText(int value) {
        String response = "";
        switch(preprocessMoonPhase(value)) {
            case 0:
                response = "Nów";
                break;
            case 1:
                response = "Przybywający sierp";
                break;
            case 2:
                response = "Pierwsza kwadra";
                break;
            case 3:
                response = "Przybywający garb";
                break;
            case 4:
                response = "Pełnia";
                break;
            case 5:
                response = "Ubywający garb";
                break;
            case 6:
                response = "Ostatnia kwadra";
                break;
            case 7:
                response = "Ubywający sierp";
                break;
        }
        return response;
    }

    private String getPictureNumber(int value) {
        return Integer.toString(preprocessMoonPhase(value));
    }

    private int preprocessMoonPhase(int value) {
        int number = 0;
        if (value > 5 && value < 45) {
            number = 1;
        } else if (value >= 45 && value <= 55 ) {
            number = 2;
        } else if (value > 55 && value < 95 ) {
            number = 3;
        } else if (value >=95 || value <= -95) {
            number = 4;
        } else if (value > -95 && value < -55) {
            number = 5;
        } else if (value >= -55 && value <= -45) {
            number = 6;
        } else if (value > -45 && value < -5) {
            number = 7;
        }
        return number;
    }
}
