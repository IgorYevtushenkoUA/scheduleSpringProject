package com.example.faculty.util.scheduler;

import com.example.faculty.util.cache.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableAsync
@Component
@EnableScheduling
public class Scheduler {
    private final CacheUtil util;

    public Scheduler(CacheUtil util) {
        this.util = util;
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTaskAsync() {
        System.out.println(
                "5 Seconds - " + System.currentTimeMillis() / 1000);
        util.clearSubjectsCache();
    }

    @Scheduled(cron = "1 * * * * *")
    public void scheduleTaskUsingCronExpression() {
        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "New minute - " + now);
        util.clearEventsCache();
    }
}

