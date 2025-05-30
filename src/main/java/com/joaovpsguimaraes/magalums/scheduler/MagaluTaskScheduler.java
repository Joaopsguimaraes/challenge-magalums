package com.joaovpsguimaraes.magalums.scheduler;

import com.joaovpsguimaraes.magalums.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class MagaluTaskScheduler {
    private static final Logger logger = LoggerFactory.getLogger(MagaluTaskScheduler.class);
    private final NotificationService notificationService;

    public MagaluTaskScheduler(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void checkTasks(){
        var dateTime = LocalDateTime.now();
        logger.info("Running at {}",dateTime);

        this.notificationService.checkAndSend(dateTime);
    }
}
