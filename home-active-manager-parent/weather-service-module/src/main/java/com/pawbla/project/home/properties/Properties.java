package com.pawbla.project.home.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class Properties {
    private String ipInternalSensor;
    private String ipExternalSensor;
    private String intSensorPassword;
    private String ipAirLy;
    private String ipAirLyInstallation;
    private String apiKeyAirLy;
    private String ipSunSetRise;
    private String urlAccuWeather;

    public String getIpInternalSensor() {
        return ipInternalSensor;
    }
    public void setIpInternalSensor(String ipInternalSensor) {
        this.ipInternalSensor = ipInternalSensor;
    }
    public String getIpExternalSensor() {
        return ipExternalSensor;
    }
    public void setIpExternalSensor(String ipExternalSensor) {
        this.ipExternalSensor = ipExternalSensor;
    }
    public String getIntSensorPassword() {
        return intSensorPassword;
    }
    public void setIntSensorPassword(String intSensorPassword) {
        this.intSensorPassword = intSensorPassword;
    }
    public String getIpAirLy() {
        return ipAirLy;
    }
    public void setIpAirLy(String ipAirLy) {
        this.ipAirLy = ipAirLy;
    }
    public String getApiKeyAirLy() {
        return apiKeyAirLy;
    }
    public void setApiKeyAirLy(String apiKeyAirLy) {
        this.apiKeyAirLy = apiKeyAirLy;
    }
    public String getIpAirLyInstallation() {
        return ipAirLyInstallation;
    }
    public void setIpAirLyInstallation(String ipAirLyInstallation) {
        this.ipAirLyInstallation = ipAirLyInstallation;
    }
    public String getIpSunSetRise() {
        return ipSunSetRise;
    }
    public void setIpSunSetRise(String ipSunSetRise) {
        this.ipSunSetRise = ipSunSetRise;
    }
    public String getUrlAccuWeather() {
        return urlAccuWeather;
    }
    public void setUrlAccuWeather(String urlAccuWeather) {
        this.urlAccuWeather = urlAccuWeather;
    }
}