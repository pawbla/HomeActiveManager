package com.pawbla.project.home.history.module.config;

import com.pawbla.project.home.history.module.service.HistoryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
@Profile("!test")
public class SchedulerConfiguration {
    private final static int POOL_SIZE = 2;

    private final HistoryHandler historyHandler;

    public SchedulerConfiguration(HistoryHandler historyHandler) {
        this.historyHandler = historyHandler;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        return threadPoolTaskScheduler;
    }

    @Scheduled(cron="0 0 * * * *", zone="Europe/Warsaw")
    public void handleMeasurementData() {
        historyHandler.handle();
    }


}
