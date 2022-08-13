package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.OperatingSystem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev", "test"})
public class OperatingSystemDev implements OperatingSystemService {

    @Override
    public void gatherOperathingSystemInfo(OperatingSystem operatingSystem) {
        operatingSystem.setName("OS Name");
        operatingSystem.setVersion("OS Version");
        operatingSystem.setArchitecture("OS Architecture");
        operatingSystem.setFirmwareBuild("OS Firmware build");
        operatingSystem.setFirmwareDate("OS Firmware date");
    }
}
