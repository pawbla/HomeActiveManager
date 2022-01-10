package com.pawbla.project.home.weather.service.parsers;

import com.pawbla.project.home.weather.service.models.AirLyInstallationMeasurement;
import com.pawbla.project.home.weather.service.models.Measurement;
import com.pawbla.project.home.weather.service.models.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("AirLyInstallation")
public class AirLyInstalltionParser  extends AbstractParser {

    /**
     * Logger
     */
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private AirLyInstallationMeasurement airLyInstallationMeasurement;

    public enum AirLyInstalltionValues implements Values {

        COUNTRY("country"),
        CITY("city"),
        STREET("street");

        public final String value;

        private AirLyInstalltionValues(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * Constants
     */
    private static final String ADDRESS_KEY = "address";
    private static final String COUNTRY_KEY = "country";
    private static final String CITY_KEY = "city";
    private static final String STREET_KEY = "street";

    public AirLyInstalltionParser() {
        this.airLyInstallationMeasurement = new AirLyInstallationMeasurement();
    }

    @Override
    public Measurement parse(Response response) throws JSONException {
        try {
            if (!response.isError()) {
                JSONObject jsonArray = new JSONObject(response.getBody()).getJSONObject(ADDRESS_KEY);
                String country = jsonArray.getString(COUNTRY_KEY);
                String city = jsonArray.getString(CITY_KEY);
                String street = jsonArray.getString(STREET_KEY);
                airLyInstallationMeasurement.setDate(response.getDate());
                airLyInstallationMeasurement.setMeasurements(country, city, street);
            }
        } catch (JSONException e) {
            logger.error("An error has occured during JSON conversion" + e.getMessage());
        } finally {
            airLyInstallationMeasurement.setError(response.isError());

        }
        return airLyInstallationMeasurement;
    }
}
