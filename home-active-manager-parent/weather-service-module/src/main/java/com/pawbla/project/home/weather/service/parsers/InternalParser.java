package com.pawbla.project.home.weather.service.parsers;

import com.pawbla.project.home.weather.service.models.InternalMeasurement;
import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("internal")
public class InternalParser extends AbstractParser {

    /**
     * Logger
     */
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private final InternalMeasurement internalMeasurement;

    public InternalParser() {
        this.internalMeasurement = new InternalMeasurement();
    }

    public enum InternalValues implements Values {

        TEMPERATURE("temperature"),
        HUMIDITY("humidity"),
        PRESSURE("pressure");

        public final String value;

        private InternalValues(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * Constants
     */
    private static final String TEMPERATURE_SENSOR_KEY = "temperature";
    private static final String HUMIDITY_SENSOR_KEY = "humidity";

    @Override
    public Measurement parse(Response response) throws JSONException {
        try {
            if (!response.isError()) {
                JSONObject jsonObject = new JSONObject(response.getBody());
                String humidity = Integer.toString(jsonObject.getInt(HUMIDITY_SENSOR_KEY));
                String temperature = Integer.toString(jsonObject.getInt(TEMPERATURE_SENSOR_KEY));
                internalMeasurement.setMeasurements(temperature, humidity);
                internalMeasurement.setDate(response.getDate());
            }
        } catch (JSONException e) {
            logger.error("An error has occured during JSON conversion" + e.getMessage());
        } finally {
            internalMeasurement.setError(response.isError());
        }
        return internalMeasurement;
    }
}