package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.JavaEnvironment;

public interface JavaEnvironmentService {
    void gatherJavaEnvironmentInfo(JavaEnvironment javaEnvironment);
}
