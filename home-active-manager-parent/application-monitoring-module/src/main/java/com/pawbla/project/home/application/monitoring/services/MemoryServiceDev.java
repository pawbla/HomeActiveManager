package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.Memory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev", "test"})
public class MemoryServiceDev implements MemoryService {

    @Override
    public void gatherMemoryInfo(Memory memory) {
        memory.setTotal(50);
        memory.setUsed(20);
        memory.setFree(5);
        memory.setShared(15);
        memory.setBuffers(5);
        memory.setCached(5);
    }
}
