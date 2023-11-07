package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.JavaEnvironment;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev", "test"})
public class JavaEnvironmentServiceDev implements JavaEnvironmentService {
    @Override
    public void gatherJavaEnvironmentInfo(JavaEnvironment javaEnvironment) {
        javaEnvironment.setVendor("Vendor");
        javaEnvironment.setVendorUrl("vendor.url");
        javaEnvironment.setVersion("17");
        javaEnvironment.setVirtualMachine("vm");
        javaEnvironment.setRuntime("jre-17");
    }
}
