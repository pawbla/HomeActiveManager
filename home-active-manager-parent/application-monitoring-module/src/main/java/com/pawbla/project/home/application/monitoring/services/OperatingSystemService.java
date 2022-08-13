package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.OperatingSystem;

public interface OperatingSystemService {
    void gatherOperathingSystemInfo(OperatingSystem operatingSystem);
}
