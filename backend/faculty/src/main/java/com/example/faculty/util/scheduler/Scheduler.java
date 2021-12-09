
package com.example.faculty.util.scheduler;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;


@EnableAsync
@Component
@EnableScheduling
public class Scheduler {
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Scheduled(cron = "1 * * * * *")
    @CacheEvict(value = "events", allEntries = true)
    public void clearEventsCache() {
        logger.info(" ---- Clear cache for events ---- ");
    }

    @Scheduled(cron = "1 * * * * *")
    @CacheEvict(value = "subjects", allEntries = true)
    public void clearUserCache() { logger.info(" ---- Clear cache for subjects ---- "); }

    @Scheduled(cron = "2 * * * * *")
    @CacheEvict(value = "requests", allEntries = true)
    public void clearRequestCache() { logger.info(" ---- Clear cache for request ---- "); }

    @Scheduled(cron = "3 * * * * *")
    @CacheEvict(value = "users", allEntries = true)
    public void clearUsersCache() { logger.info(" ---- Clear cache for users ---- "); }
}
