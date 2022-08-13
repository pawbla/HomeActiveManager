package com.pawbla.project.home.application.monitoring.handler;

import com.pawbla.project.home.application.monitoring.models.Application;
import com.pawbla.project.home.application.monitoring.models.Hardware;
import com.pawbla.project.home.application.monitoring.models.JavaEnvironment;
import com.pawbla.project.home.application.monitoring.models.Memory;
import com.pawbla.project.home.application.monitoring.models.Monitoring;
import com.pawbla.project.home.application.monitoring.models.Network;
import com.pawbla.project.home.application.monitoring.models.OperatingSystem;
import com.pawbla.project.home.application.monitoring.services.ApplicationMonitoringService;
import com.pawbla.project.home.application.monitoring.services.HardwareService;
import com.pawbla.project.home.application.monitoring.services.JavaEnvironmentService;
import com.pawbla.project.home.application.monitoring.services.MemoryService;
import com.pawbla.project.home.application.monitoring.services.NetworkService;
import com.pawbla.project.home.application.monitoring.services.OperatingSystemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitoringHandlerImpl implements MonitoringHandler {
    private final Logger logger = LogManager.getLogger(MonitoringHandlerImpl.class);

    private final List<Application> applicationList;
    private final Hardware hardware;
    private final OperatingSystem operatingSystem;
    private final Network network;
    private final Memory memory;
    private final JavaEnvironment javaEnvironment;
    private final HardwareService hardwareService;
    private final OperatingSystemService operatingSystemService;
    private final NetworkService networkService;
    private final MemoryService memoryService;
    private final JavaEnvironmentService javaEnvironmentService;
    private final ApplicationMonitoringService applicationMonitoringService;

    @Autowired
    public MonitoringHandlerImpl(@Value("${registerd.ports}") final List<String> registeredPorts, final HardwareService hardwareService,
                                 final OperatingSystemService operatingSystemService, final NetworkService networkService,
                                 final MemoryService memoryService, final JavaEnvironmentService javaEnvironmentService,
                                 final ApplicationMonitoringService applicationMonitoringService) {
        this.hardware = new Hardware();
        this.operatingSystem = new OperatingSystem();
        this.network = new Network();
        this.memory = new Memory();
        this.javaEnvironment = new JavaEnvironment();

        this.applicationList = registeredPorts
                .stream()
                .map(Application::new)
                .collect(Collectors.toList());
        this.hardwareService = hardwareService;
        this.operatingSystemService = operatingSystemService;
        this.networkService = networkService;
        this.memoryService = memoryService;
        this.javaEnvironmentService = javaEnvironmentService;
        this.applicationMonitoringService = applicationMonitoringService;
    }

    public Monitoring getMonitoring() {
        readMonitoredData();
        return new Monitoring(hardware, operatingSystem, network, memory, javaEnvironment, applicationList);
    }

    @PostConstruct
    public void init() {
        logger.info("Read monitored data on Post Construct.");
        hardwareService.gatherHardwareInfo(hardware);
        operatingSystemService.gatherOperathingSystemInfo(operatingSystem);
        networkService.gatherNetworkInfo(network);
        javaEnvironmentService.gatherJavaEnvironmentInfo(javaEnvironment);
    }

    @Scheduled(cron="0 */10 * ? * *", zone="Europe/Warsaw")
    public void scheduled() { //TODO configure scheduler
        logger.info("Read monitored data on scheduled.");
        applicationMonitoringService.readApplicationsInfo(applicationList);
        applicationMonitoringService.healthCheck(applicationList);
    }

    private void readMonitoredData() {
        logger.info("Read monitored data on request.");
        hardwareService.updateHardwareInfo(hardware);
        networkService.gatherNetworkInfo(network);
        memoryService.gatherMemoryInfo(memory);
    }
}
