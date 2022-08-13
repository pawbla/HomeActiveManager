package com.pawbla.project.home.application.monitoring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OperatingSystem {
    @JsonProperty("name")
    private String name;
    @JsonProperty("version")
    private String version;
    @JsonProperty("architecture")
    private String architecture;
    @JsonProperty("firmwareBuild")
    private String firmwareBuild;
    @JsonProperty("firmwareDate")
    private String firmwareDate;

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public void setFirmwareBuild(String firmwareBuild) {
        this.firmwareBuild = firmwareBuild;
    }

    public void setFirmwareDate(String firmwareDate) {
        this.firmwareDate = firmwareDate;
    }
}
