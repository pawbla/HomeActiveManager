package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.Memory;

public interface MemoryService {
    void gatherMemoryInfo(Memory memory);
}
