package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.Hardware;
import com.pawbla.project.home.application.monitoring.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev", "test"})
public class HardwareServiceDev implements HardwareService {

    private final DateTimeUtils dateTimeUtils;

    @Autowired
    public HardwareServiceDev(final DateTimeUtils dateTimeUtils) {
        this.dateTimeUtils = dateTimeUtils;
    }

    @Override
    public void gatherHardwareInfo(Hardware hardware) {
        hardware.setCpuArchitecture("cpu_architecture");
        hardware.setBoardTypeName("board_type_name");
        hardware.setPlatformLabel("platform_label");
        hardware.setPlatformId("platform_id");
        hardware.setCpuRevision("cpu_revision");
        hardware.setModelName("model_name");
        hardware.setProcessor("processor");
        hardware.setCpuTemperature(12.0f);
        hardware.setCpuVoltage(3.5f);
    }

    @Override
    public void updateHardwareInfo(Hardware hardware) {
        hardware.setCpuTemperature(12.0f);
        hardware.setCpuVoltage(3.5f);
        hardware.setDateTime(dateTimeUtils.getTimeOf("2022-08-13T13:05:23"));
    }
}
