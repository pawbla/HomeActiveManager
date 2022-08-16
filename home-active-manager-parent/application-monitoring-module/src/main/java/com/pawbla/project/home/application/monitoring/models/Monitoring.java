package com.pawbla.project.home.application.monitoring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Monitoring {

    @JsonProperty("hardware")
    private final Hardware hardware;
    @JsonProperty("os")
    private final OperatingSystem operatingSystem;
    @JsonProperty("network")
    private final Network network;
    @JsonProperty("memory")
    private final Memory memory;
    @JsonProperty("java")
    private final JavaEnvironment javaEnvironment;
    @JsonProperty("applications")
    private final List<Application> applicationList;

    public Monitoring(Hardware hardware, OperatingSystem operatingSystem, Network network, Memory memory,
                      JavaEnvironment javaEnvironment, List<Application> applicationList) {
        this.hardware = hardware;
        this.operatingSystem = operatingSystem;
        this.network = network;
        this.memory = memory;
        this.javaEnvironment = javaEnvironment;
        this.applicationList = applicationList;
    }
}
