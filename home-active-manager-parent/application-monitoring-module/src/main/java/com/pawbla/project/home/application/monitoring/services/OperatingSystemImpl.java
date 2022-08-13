package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.OperatingSystem;
import com.pi4j.system.SystemInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;

@Service
@Profile("prod")
public class OperatingSystemImpl implements OperatingSystemService {
    private final Logger logger = LogManager.getLogger(OperatingSystemImpl.class);

    @Override
    public void gatherOperathingSystemInfo(OperatingSystem operatingSystem) {
        operatingSystem.setName(readName());
        operatingSystem.setVersion(readVersion());
        operatingSystem.setArchitecture(readArchitecture());
        operatingSystem.setFirmwareBuild(readFirmwareBuild());
        operatingSystem.setFirmwareDate(readFirmwareDate());
    }

    private String readName() {
        return SystemInfo.getOsName();
    }

    private String readVersion() {
        return SystemInfo.getOsVersion();
    }

    private String readArchitecture() {
        return SystemInfo.getOsArch();
    }

    private String readFirmwareBuild() {
        String fwBuild = "";
        try {
            fwBuild = SystemInfo.getOsFirmwareBuild();
        } catch (IOException | InterruptedException e) {
            logger.warn("Cannot read OS firmware build. " + e.getMessage());
        }
        return fwBuild;
    }

    private String readFirmwareDate() {
        String fwDate = "";
        try {
            fwDate = SystemInfo.getOsFirmwareDate();
        } catch (IOException | InterruptedException | ParseException e) {
            logger.warn("Cannot read OS firmware date. " + e.getMessage());
        }
        return fwDate;
    }

}
