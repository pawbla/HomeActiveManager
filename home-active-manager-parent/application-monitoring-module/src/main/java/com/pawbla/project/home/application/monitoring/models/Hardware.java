package com.pawbla.project.home.application.monitoring.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Hardware {
    @JsonProperty("platformLabel")
    private String platformLabel;
    @JsonProperty("platformId")
    private String platformId;
    @JsonProperty("cpuRevision")
    private String cpuRevision;
    @JsonProperty("cpuArchitecture")
    private String cpuArchitecture;
    @JsonProperty("modelName")
    private String modelName;
    @JsonProperty("processor")
    private String processor;
    @JsonProperty("boardTypeName")
    private String boardTypeName;
    @JsonProperty("cpuTemperature")
    private float cpuTemperature;
    @JsonProperty("cpuVoltage")
    private float cpuVoltage;
    @JsonProperty("dateTime")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime;

    public void setPlatformLabel(String platformLabel) {
        this.platformLabel = platformLabel;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public void setCpuRevision(String cpuRevision) {
        this.cpuRevision = cpuRevision;
    }

    public void setCpuArchitecture(String cpuArchitecture) {
        this.cpuArchitecture = cpuArchitecture;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setBoardTypeName(String boardTypeName) {
        this.boardTypeName = boardTypeName;
    }

    public void setCpuTemperature(float cpuTemperature) {
        this.cpuTemperature = cpuTemperature;
    }

    public void setCpuVoltage(float cpuVoltage) {
        this.cpuVoltage = cpuVoltage;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
