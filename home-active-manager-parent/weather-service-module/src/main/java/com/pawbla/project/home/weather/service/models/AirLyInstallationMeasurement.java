package com.pawbla.project.home.weather.service.models;

import static com.pawbla.project.home.weather.service.utils.Constants.EMPTY;

public class AirLyInstallationMeasurement extends Measurement {
    private String country = EMPTY;
    private String city = EMPTY;
    private String street = EMPTY;

    public void setMeasurements(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }


}
