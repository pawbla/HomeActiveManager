package com.pawbla.project.home.testing.module.handlers.response;

import com.pawbla.project.home.testing.module.view.panel.response.ScenarioPanel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("accuWeather")
public class AccuWeatherHandler extends AbstractServiceHandler {
    public AccuWeatherHandler(@Qualifier("accuWeatherPanel") ScenarioPanel scenarioPanel) {
        super(scenarioPanel);
    }

    @Override
    public String getBody() {
        return "[{\"LocalObservationDateTime\":\"2019-10-05T16:55:00+02:00\",\"EpochTime\""
                + ":1570287300,\"WeatherText\":\"Czesciowo slonecznie \",\"WeatherIcon\":7,\"HasPrecipitation\":false,\"PrecipitationType\":null"
                + ",\"IsDayTime\":true,\"Temperature\":{\"Metric\":{\"Value\":8.9,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value"
                + "\":48.0,\"Unit\":\"F\",\"UnitType\":18}},\"RealFeelTemperature\":{\"Metric\":{\"Value\":4.0,\"Unit\":\"C\",\"UnitType"
                + "\":17},\"Imperial\":{\"Value\":39.0,\"Unit\":\"F\",\"UnitType\":18}},\"RealFeelTemperatureShade\":{\"Metric\":{\"Value"
                + "\":4.0,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":39.0,\"Unit\":\"F\",\"UnitType\":18}},\"RelativeHumidity"
                + "\":93,\"DewPoint\":{\"Metric\":{\"Value\":7.8,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":46.0,\"Unit\":\"F"
                + ""
                + "\",\"UnitType\":18}},\"Wind\":{\"Direction\":{\"Degrees\":45,\"Localized\":\"NE\",\"English\":\"NE\"},\"Speed\":{\"Metric"
                + "\":{\"Value\":18.5,\"Unit\":\"km/h\",\"UnitType\":7},\"Imperial\":{\"Value\":11.5,\"Unit\":\"mi/h\",\"UnitType\":9}}},\""
                + "WindGust\":{\"Speed\":{\"Metric\":{\"Value\":18.5,\"Unit\":\"km/h\",\"UnitType\":7},\"Imperial\":{\"Value\":11.5,\"Unit\""
                + ":\"mi/h\",\"UnitType\":9}}},\"UVIndex\":7,\"UVIndexText\":\"Niska\",\"Visibility\":{\"Metric\":{\"Value\":9.7,\"Unit\":\""
                + "km\",\"UnitType\":6},\"Imperial\":{\"Value\":6.0,\"Unit\":\"mi\",\"UnitType\":2}},\"ObstructionsToVisibility\":\"R-\",\""
                + "CloudCover\":100,\"Ceiling\":{\"Metric\":{\"Value\":457.0,\"Unit\":\"m\",\"UnitType\":5},\"Imperial\":{\"Value\":1500.0,"
                + "\"Unit\":\"ft\",\"UnitType\":0}},\"Pressure\":{\"Metric\":{\"Value\":1007.0,\"Unit\":\"mb\",\"UnitType\":14},\"Imperial\":{"
                + "\"Value\":29.74,\"Unit\":\"inHg\",\"UnitType\":12}},\"PressureTendency\":{\"LocalizedText\":\"Rośnie\",\"Code\":\"R\"},"
                + "\"Past24HourTemperatureDeparture\":{\"Metric\":{\"Value\":-3.3,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":-6.0,"
                + "\"Unit\":\"F\",\"UnitType\":18}},\"ApparentTemperature\":{\"Metric\":{\"Value\":10.6,\"Unit\":\"C\",\"UnitType\":17},\"Imperial"
                + "\":{\"Value\":51.0,\"Unit\":\"F\",\"UnitType\":18}},\"WindChillTemperature\":{\"Metric\":{\"Value\":6.1,\"Unit\":\"C\","
                + "\"UnitType\":17},\"Imperial\":{\"Value\":43.0,\"Unit\":\"F\",\"UnitType\":18}},\"WetBulbTemperature\":{\"Metric\":{\"Value"
                + "\":8.3,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":47.0,\"Unit\":\"F\",\"UnitType\":18}},\"Precip1hr\":{\"Metric"
                + "\":{\"Value\":0.5,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.02,\"Unit\":\"in\",\"UnitType\":1}},\"PrecipitationSummary"
                + "\":{\"Precipitation\":{\"Metric\":{\"Value\":0.5,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.02,\"Unit\":\"in\","
                + "\"UnitType\":1}},\"PastHour\":{\"Metric\":{\"Value\":0.5,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.02,\"Unit"
                + "\":\"in\",\"UnitType\":1}},\"Past3Hours\":{\"Metric\":{\"Value\":1.0,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value"
                + "\":0.04,\"Unit\":\"in\",\"UnitType\":1}},\"Past6Hours\":{\"Metric\":{\"Value\":2.2,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial"
                + "\":{\"Value\":0.09,\"Unit\":\"in\",\"UnitType\":1}},\"Past9Hours\":{\"Metric\":{\"Value\":4.1,\"Unit\":\"mm\",\"UnitType\":3},"
                + "\"Imperial\":{\"Value\":0.16,\"Unit\":\"in\",\"UnitType\":1}},\"Past12Hours\":{\"Metric\":{\"Value\":5.6,\"Unit\":\"mm\","
                + "\"UnitType\":3},\"Imperial\":{\"Value\":0.22,\"Unit\":\"in\",\"UnitType\":1}},\"Past18Hours\":{\"Metric\":{\"Value\":6.1,"
                + "\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.24,\"Unit\":\"in\",\"UnitType\":1}},\"Past24Hours\":{\"Metric"
                + "\":{\"Value\":6.1,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.24,\"Unit\":\"in\",\"UnitType\":1}}},"
                + "\"TemperatureSummary\":{\"Past6HourRange\":{\"Minimum\":{\"Metric\":{\"Value\":8.1,\"Unit\":\"C\",\"UnitType\":17},"
                + "\"Imperial\":{\"Value\":46.0,\"Unit\":\"F\",\"UnitType\":18}},\"Maximum\":{\"Metric\":{\"Value\":9.0,\"Unit\":\"C\",\"UnitType"
                + "\":17},\"Imperial\":{\"Value\":48.0,\"Unit\":\"F\",\"UnitType\":18}}},\"Past12HourRange\":{\"Minimum\":{\"Metric\":{\"Value"
                + "\":7.2,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":45.0,\"Unit\":\"F\",\"UnitType\":18}},\"Maximum\":{\"Metric"
                + "\":{\"Value\":9.0,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":48.0,\"Unit\":\"F\",\"UnitType\":18}}},\"Past24HourRange"
                + "\":{\"Minimum\":{\"Metric\":{\"Value\":7.2,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":45.0,\"Unit\":\"F\","
                + "\"UnitType\":18}},\"Maximum\":{\"Metric\":{\"Value\":12.2,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":54.0,"
                + "\"Unit\":\"F\",\"UnitType\":18}}}},\"MobileLink\":\"http://m.accuweather.com/\",\"Link\":\"http://www.accuweather\"}]";
    }
}
