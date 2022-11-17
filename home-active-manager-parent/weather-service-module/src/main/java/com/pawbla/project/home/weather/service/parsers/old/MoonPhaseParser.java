package com.pawbla.project.home.weather.service.parsers.old;

import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.old.MoonPhaseMeasurement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("moonPhase")
public class MoonPhaseParser extends AbstractParser {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private MoonPhaseMeasurement measurement;

    public enum MoonPhaseValues implements Values {

        TEXT("text"),
        PICTURE("picture");

        public final String value;

        private MoonPhaseValues(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public MoonPhaseParser() {
        measurement = new MoonPhaseMeasurement();
    }

    @Override
    public Measurement parse(int value) throws JSONException {
        measurement.setMeasurements(getText(value), getPictureNumber(value));
        return measurement;
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
