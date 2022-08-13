package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.JavaEnvironment;
import com.pi4j.system.SystemInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class JavaEnvironmentServiceImpl implements JavaEnvironmentService {

    @Override
    public void gatherJavaEnvironmentInfo(JavaEnvironment javaEnvironment) {
        javaEnvironment.setVendor(readVendor());
        javaEnvironment.setVendorUrl(readVendorUrl());
        javaEnvironment.setVersion(readVersion());
        javaEnvironment.setVirtualMachine(readVM());
        javaEnvironment.setRuntime(readRuntime());
    }

    private String readVendor() {
        return SystemInfo.getJavaVendor();
    }

    private String readVendorUrl() {
        return SystemInfo.getJavaVendorUrl();
    }

    private String readVersion() {
        return SystemInfo.getJavaVersion();
    }

    private String readVM() {
        return SystemInfo.getJavaVirtualMachine();
    }

    private String readRuntime() {
        return SystemInfo.getJavaRuntime();
    }
}
