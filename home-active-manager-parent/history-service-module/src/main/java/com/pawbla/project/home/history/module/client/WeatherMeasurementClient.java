package com.pawbla.project.home.history.module.client;

import com.pawbla.project.home.history.module.model.WeatherMeasurement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "weatherMeasurement", url = "${weather.service.url}")
public interface WeatherMeasurementClient {

    @GetMapping(value = "/api/v1/weather/simplifiedMeasurement")
    WeatherMeasurement getSimplifiedMeasurement();
}
