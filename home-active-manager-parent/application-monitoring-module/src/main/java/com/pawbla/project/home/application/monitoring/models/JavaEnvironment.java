package com.pawbla.project.home.application.monitoring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JavaEnvironment {
    @JsonProperty("vendor")
    private String vendor;
    @JsonProperty("vendorUrl")
    private String vendorUrl;
    @JsonProperty("version")
    private String version;
    @JsonProperty("virtualMachinve")
    private String virtualMachine;
    @JsonProperty("runtime")
    private String runtime;

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setVendorUrl(String vendorUrl) {
        this.vendorUrl = vendorUrl;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setVirtualMachine(String virtualMachine) {
        this.virtualMachine = virtualMachine;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
}
