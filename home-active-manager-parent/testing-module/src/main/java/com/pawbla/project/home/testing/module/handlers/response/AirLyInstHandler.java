package com.pawbla.project.home.testing.module.handlers.response;

import com.pawbla.project.home.testing.module.view.panel.response.ScenarioPanel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("airLyInst")
public class AirLyInstHandler extends AbstractServiceHandler {
    public AirLyInstHandler(@Qualifier("airLyInstPanel") ScenarioPanel scenarioPanel) {
        super(scenarioPanel);
    }

    @Override
    public String getBody() {
        return "{" +
                "  \"id\": 204," +
                "  \"location\": {" +
                "    \"latitude\": 50.062006," +
                "    \"longitude\": 19.940984" +
                "  }," +
                "  \"address\": {" +
                "    \"country\": \"Poland\"," +
                "    \"city\": \"Kraków\"," +
                "    \"street\": \"Mikołajska\"," +
                "    \"number\": \"4B\"," +
                "    \"displayAddress1\": \"Kraków\"," +
                "    \"displayAddress2\": \"Mikołajska\"" +
                "  }," +
                "  \"elevation\": 220.38," +
                "  \"airly\": true," +
                "  \"sponsor\": {" +
                "    \"name\": \"KrakówOddycha\"," +
                "    \"description\": \"Airly Sensor is part of action\"," +
                "    \"logo\": \"https://cdn.airly.eu/logo/KrakówOddycha.jpg\"," +
                "    \"link\": \"https://sponsor_home_address.pl\"" +
                "  }" +
                "}" +
                "";
    }
}
