package com.example.faculty.util.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class CacheUtil {
    @CacheEvict(value = "events", allEntries = true)
    public void clearEventsCache() {
        System.err.println("Clear cache events");
    }

    @CacheEvict(value = "subjects", allEntries = true)
    public void clearSubjectsCache() {
        System.err.println("Clear cache subjects");
    }
}
