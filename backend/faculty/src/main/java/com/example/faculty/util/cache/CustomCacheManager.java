package com.example.faculty.util.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;

@EnableCaching
@Component
public class CustomCacheManager implements CacheManager {
    private final HashMap<String, Cache> map;

    public CustomCacheManager() {
        this.map = new HashMap<>();
        map.put("events", new ConcurrentMapCache("events"));
        map.put("subjects", new ConcurrentMapCache("subjects"));
    }

    @Override
    public Cache getCache(String s) {
        return map.get(s);
    }

    @Override
    public Collection<String> getCacheNames() {
        return map.keySet();
    }
}
