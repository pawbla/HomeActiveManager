package com.pawbla.project.home.application.monitoring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Network {
    @JsonProperty("hostName")
    private String hostName;
    @JsonProperty("ipAddress")
    private List<String> ipAddressList;
    @JsonProperty("fqdn")
    private List<String> fqdnList;
    @JsonProperty("nameserver")
    private List<String> nameserverList;

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setIpAddressList(List<String> ipAddressList) {
        this.ipAddressList = ipAddressList;
    }

    public void setFqdnList(List<String> fqdnList) {
        this.fqdnList = fqdnList;
    }

    public void setNameserverList(List<String> nameserverList) {
        this.nameserverList = nameserverList;
    }
}