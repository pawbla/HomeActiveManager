package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.Hardware;

public interface HardwareService {
    void gatherHardwareInfo(Hardware hardware);
    void updateHardwareInfo(Hardware hardware);
}
