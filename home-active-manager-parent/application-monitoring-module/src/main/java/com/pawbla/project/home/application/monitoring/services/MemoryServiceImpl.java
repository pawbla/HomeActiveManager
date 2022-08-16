package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.Memory;
import com.pi4j.system.SystemInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Profile("prod")
public class MemoryServiceImpl implements MemoryService {
    private final Logger logger = LogManager.getLogger(MemoryServiceImpl.class);

    @Override
    public void gatherMemoryInfo(Memory memory) {
        memory.setTotal(readTotal());
        memory.setUsed(readUsed());
        memory.setFree(readFree());
        memory.setShared(readShared());
        memory.setBuffers(readBuffers());
        memory.setCached(readCached());
    }

    private long readTotal() {
        long total = 0;
        try {
            total = SystemInfo.getMemoryTotal();
        } catch (IOException | InterruptedException e) {
            logger.warn("Cannot read memory total. " + e.getMessage());
        }
        return total;
    }

    private long readUsed() {
        long used = 0;
        try {
            used = SystemInfo.getMemoryUsed();
        } catch (IOException | InterruptedException e) {
            logger.warn("Cannot read memory used. " + e.getMessage());
        }
        return used;
    }

    private long readFree() {
        long free = 0;
        try {
            free = SystemInfo.getMemoryFree();
        } catch (IOException | InterruptedException e) {
            logger.warn("Cannot read memory free. " + e.getMessage());
        }
        return free;
    }

    private long readShared() {
        long shared = 0;
        try {
            shared = SystemInfo.getMemoryShared();
        } catch (IOException | InterruptedException e) {
            logger.warn("Cannot read memory shared. " + e.getMessage());
        }
        return shared;
    }

    private long readBuffers() {
        long buffers = 0;
        try {
            buffers = SystemInfo.getMemoryBuffers();
        } catch (IOException | InterruptedException e) {
            logger.warn("Cannot read memory buffers. " + e.getMessage());
        }
        return buffers;
    }

    private long readCached() {
        long cached = 0;
        try {
            cached = SystemInfo.getMemoryCached();
        } catch (IOException | InterruptedException e) {
            logger.warn("Cannot read memory cached. " + e.getMessage());
        }
        return cached;
    }
}
