package com.pawbla.project.home.application.monitoring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Memory {
    @JsonProperty("total")
    private long total;
    @JsonProperty("used")
    private long used;
    @JsonProperty("free")
    private long free;
    @JsonProperty("shared")
    private long shared;
    @JsonProperty("buffers")
    private long buffers;
    @JsonProperty("cached")
    private long cached;

    public void setTotal(long total) {
        this.total = total;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public void setFree(long free) {
        this.free = free;
    }

    public void setShared(long shared) {
        this.shared = shared;
    }

    public void setBuffers(long buffers) {
        this.buffers = buffers;
    }

    public void setCached(long cached) {
        this.cached = cached;
    }
}
