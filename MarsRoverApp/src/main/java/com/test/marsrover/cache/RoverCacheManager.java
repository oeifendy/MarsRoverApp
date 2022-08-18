package com.test.marsrover.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.test.marsrover.domain.Rover;

public class RoverCacheManager {
	private static RoverCacheManager instance;
    private static Object monitor = new Object();
    private Map<String, Rover> cache = Collections.synchronizedMap(new HashMap<String, Rover>());

    private RoverCacheManager() {
    }

    public void put(String cacheKey, Rover value) {
        cache.put(cacheKey, value);
    }

    public Rover get(String cacheKey) {
        return cache.get(cacheKey);
    }
    
    public Map<String, Rover> getAll() {
    	return cache;
    }

    public void clear(String cacheKey) {
        cache.put(cacheKey, null);
    }

    public void clear() {
        cache.clear();
    }

    public synchronized static RoverCacheManager getInstance() {
        if (instance == null) {
            synchronized (monitor) {
                if (instance == null) {
                    instance = new RoverCacheManager();
                }
            }
        }
        return instance;
    }

}
