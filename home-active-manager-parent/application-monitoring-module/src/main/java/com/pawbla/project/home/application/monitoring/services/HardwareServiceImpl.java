package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.Hardware;
import com.pawbla.project.home.application.monitoring.utils.DateTimeUtils;
import com.pi4j.platform.PlatformManager;
import com.pi4j.system.SystemInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Profile("prod")
public class HardwareServiceImpl implements HardwareService {
    private final Logger logger = LogManager.getLogger(HardwareServiceImpl.class);

    private final DateTimeUtils dateTimeUtils;

    @Autowired
    public HardwareServiceImpl(final DateTimeUtils dateTimeUtils) {
        this.dateTimeUtils = dateTimeUtils;
    }

    @Override
    public void gatherHardwareInfo(Hardware hardware) {
        logger.info("Read hardware info");
        hardware.setCpuArchitecture(readCpuArchitecture());
        hardware.setBoardTypeName(readBoardTypeName());
        hardware.setPlatformLabel(readPlatformLabel());
        hardware.setPlatformId(readPlatformId());
        hardware.setCpuRevision(readCpuRevision());
        hardware.setModelName(readModelName());
        hardware.setProcessor(readProcessor());
        hardware.setCpuTemperature(readCpuTemperature());
        hardware.setCpuVoltage(readCpuVoltage());
    }

    @Override
    public void updateHardwareInfo(Hardware hardware) {
        logger.info("Update hardware info");
        hardware.setCpuTemperature(readCpuTemperature());
        hardware.setCpuVoltage(readCpuVoltage());
        hardware.setDateTime(dateTimeUtils.getNow());
    }

    private String readPlatformLabel() {
        return PlatformManager.getPlatform().getLabel();
    }

    private String readPlatformId() {
        return PlatformManager.getPlatform().getId();
    }

    private String readCpuRevision() {
        String cpuRevision = "";
        try {
            cpuRevision = SystemInfo.getCpuRevision();
        } catch (IOException | InterruptedException | UnsupportedOperationException e) {
            logger.warn("Cannot read CPU Revision. " + e.getMessage());
        }
        return cpuRevision;
    }

    private String readCpuArchitecture() {
        String cpuArchitecture = "";
        try {
            cpuArchitecture = SystemInfo.getCpuArchitecture();
        } catch (IOException | InterruptedException | UnsupportedOperationException e) {
            logger.warn("Cannot read CPU Architecture. " + e.getMessage());
        }
        return cpuArchitecture;
    }

    private String readModelName() {
        String modelName = "";
        try {
            modelName = SystemInfo.getModelName();
        } catch (IOException | InterruptedException | UnsupportedOperationException e) {
            logger.warn("Cannot read Model name. " + e.getMessage());
        }
        return modelName;
    }

    private String readProcessor() {
        String processor = "";
        try {
            processor = SystemInfo.getProcessor();
        } catch (IOException | InterruptedException | UnsupportedOperationException e) {
            logger.warn("Cannot read Processor. " + e.getMessage());
        }
        return processor;
    }

    private String readBoardTypeName() {
        String boardTypeName = "";
        try {
            boardTypeName = SystemInfo.getBoardType().name();
        } catch (IOException | InterruptedException | UnsupportedOperationException e) {
            logger.warn("Cannot read Board Type Name. " + e.getMessage());
        }
        return boardTypeName;
    }

    private float readCpuTemperature() {
        float cpuTemperature = 0;
        try {
            cpuTemperature = SystemInfo.getCpuTemperature();
        } catch (IOException | InterruptedException | UnsupportedOperationException e) {
            logger.warn("Cannot read CPU temperature. " + e.getMessage());
        }
        return cpuTemperature;
    }

    private float readCpuVoltage() {
        float cpuVoltage = 0;
        try {
            cpuVoltage = SystemInfo.getCpuVoltage();
        } catch (IOException | InterruptedException | UnsupportedOperationException e) {
            logger.warn("Cannot read CPU voltage. " + e.getMessage());
        }
        return cpuVoltage;
    }
}
