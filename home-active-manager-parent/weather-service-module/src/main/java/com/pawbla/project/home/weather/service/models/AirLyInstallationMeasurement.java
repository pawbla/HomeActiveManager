package com.pawbla.project.home.weather.service.models;

public class AirLyInstallationMeasurement extends Measurement {
    private String country;
    private String city;
    private String street;

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
