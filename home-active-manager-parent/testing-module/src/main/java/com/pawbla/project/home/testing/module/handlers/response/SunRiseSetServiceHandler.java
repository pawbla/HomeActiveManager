package com.pawbla.project.home.testing.module.handlers.response;

import com.pawbla.project.home.testing.module.view.panel.response.ScenarioPanel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("sunRiseSet")
public class SunRiseSetServiceHandler extends AbstractServiceHandler {


    public SunRiseSetServiceHandler(@Qualifier("sunRiseSetPanel") ScenarioPanel sunRiseSetPanel) {
        super(sunRiseSetPanel);
    }

    @Override
    public String getBody() {
        return "{\"results\":{\"sunrise\":\"2019-10-05T04:46:45+00:00\",\"sunset\":\"2019-10-05T16:33:24+00:00\"," +
                "\"solar_noon\":\"2019-10-05T10:28:35+00:00\",\"day_length\":41019,\"civil_twilight_begin\":" +
                "\"2019-10-05T04:14:35+00:00\",\"civil_twilight_end\":\"2019-10-05T16:42:34+00:00\"," +
                "\"nautical_twilight_begin\":\"2019-10-05T03:37:08+00:00\",\"nautical_twilight_end\":" +
                "\"2019-10-05T17:20:01+00:00\",\"astronomical_twilight_begin\":\"2019-10-05T02:59:05+00:00\"," +
                "\"astronomical_twilight_end\":\"2019-10-05T17:58:04+00:00\"},\"status\":\"OK\"}";
    }
}
