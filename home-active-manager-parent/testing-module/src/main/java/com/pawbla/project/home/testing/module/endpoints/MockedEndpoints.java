package com.pawbla.project.home.testing.module.endpoints;

import com.pawbla.project.home.testing.module.handlers.response.ServiceHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test/")
public class MockedEndpoints {

    private final ServiceHandler internal;
    private final ServiceHandler airLy;
    private final ServiceHandler airLyInst;
    private final ServiceHandler accuWeather;

    public MockedEndpoints(@Qualifier("sunRiseSet") ServiceHandler internal, @Qualifier("airLy") ServiceHandler airLy,
                           @Qualifier("accuWeather") ServiceHandler accuWeather, @Qualifier("airLyInst") ServiceHandler airLyInst) {
        this.internal = internal;
        this.airLy = airLy;
        this.accuWeather = accuWeather;
        this.airLyInst = airLyInst;
    }

    @GetMapping(value = "/sunriseset", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> mockedSunRiseSet() {
        return internal.getResponse();
    }

    @GetMapping(value = "/airly", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> mockedAirLy() {
        return airLy.getResponse();
    }

    @GetMapping(value = "/accuweather", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> mockedAccuWeather() {
        return accuWeather.getResponse();
    }

    @GetMapping(value = "/airlyinst", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> mockedAirLyInstallation() {
        return airLyInst.getResponse();
    }
}
