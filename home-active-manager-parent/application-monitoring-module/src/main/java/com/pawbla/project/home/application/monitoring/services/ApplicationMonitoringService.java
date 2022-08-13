package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.Application;

import java.util.List;

public interface ApplicationMonitoringService {
    void readApplicationsInfo(List<Application> applicationList);
    void healthCheck(List<Application> applicationList);
}
