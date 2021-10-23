package com.pawbla.project.home.weather.service.config;

import com.pawbla.project.home.weather.service.handlers.HandlerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class SchedulerConfiguration {

    private final static int POOL_SIZE = 10;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        return threadPoolTaskScheduler;
    }

    /**
     * AirLy Connector configuration
     **/
    @Autowired
    @Qualifier("AirLy")
    private HandlerInterface airLy;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron="0 0/15 * ? * *", zone="Europe/Warsaw") //cron at every 15 minutes
    public void fetchAirLyData() {
        airLy.setRecovery(1, 15); // when incorrect response received call api every 1 minute within 7 minutes
        airLy.execute();
    }

    /**
     * AccuWeather Connector configuration
     */
    @Autowired
    @Qualifier("accuWeather")
    private HandlerInterface accuWeather;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron="0 0/30 * ? * *", zone="Europe/Warsaw")  //cron at every 30 minutes
    public void fetchAccuWeatherData() {
        accuWeather.setRecovery(1, 30); // when incorrect response received call api every 1 minute within 30 minutes
        accuWeather.execute();
    }

    /**
     * Internal Connector configuration
     */
    @Autowired
    @Qualifier("internal")
    private HandlerInterface internal;

    private final int INT_TIMEOUT = 30000;
    private final int INT_DELAY_TIMEOUT = 20000;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(fixedRate = INT_TIMEOUT, initialDelay = INT_DELAY_TIMEOUT)
    public void fetchInternalData() {
        internal.execute();
    }

    /**
     * Sun rise set configuration
     */
    @Autowired
    @Qualifier("sunRiseSet")
    private HandlerInterface sunRiseSet;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron="10 0 0 * * ?", zone="Europe/Warsaw") //cron configured at 00:00:10am every day
    public void fetchSunRiseSetData() {
        sunRiseSet.setRecovery(10, 1400); // when incorrect response received call api every 10 minute within 1400 minutes
        sunRiseSet.execute();
    }
}
