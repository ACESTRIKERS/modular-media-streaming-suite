package com.modularmedia.proxy;

import com.modularmedia.core.MediaSource;

/**
 * PROXY PATTERN - Caching Proxy
 * 
 * This proxy wraps a remote MediaSource and provides caching functionality.
 * It intercepts calls to the remote source and caches the results locally
 * to improve performance and reduce network traffic.
 * 
 * Design Rationale:
 * - Provides transparent caching without modifying the original MediaSource
 * - Reduces network latency and bandwidth usage
 * - Maintains the same interface as the wrapped object
 * - Enables lazy loading and on-demand caching
 */
public class RemoteProxy implements MediaSource {
    private MediaSource remoteSource;
    private boolean cached = false;
    private String cacheKey;
    private long cacheTimestamp;

    public RemoteProxy(MediaSource remoteSource) { 
        this.remoteSource = remoteSource; 
        this.cacheKey = generateCacheKey(remoteSource);
        this.cacheTimestamp = 0;
    }

    @Override
    public void load() {
        if (!cached || isCacheExpired()) {
            System.out.println("  → Cache miss - fetching from remote source...");
            System.out.println("  → Cache key: " + cacheKey);
            remoteSource.load();
            cached = true;
            cacheTimestamp = System.currentTimeMillis();
            System.out.println("  → Remote content cached successfully");
        } else {
            System.out.println("  → Cache hit - using cached content");
            System.out.println("  → Cache age: " + getCacheAge() + "ms");
        }
    }

    @Override 
    public void play() { 
        load(); 
        remoteSource.play(); 
    }
    
    @Override
    public String getSourceInfo() {
        return "Cached: " + remoteSource.getSourceInfo();
    }
    
    @Override
    public boolean isReady() {
        return cached && remoteSource.isReady();
    }
    
    public void clearCache() {
        cached = false;
        cacheTimestamp = 0;
        System.out.println("  → Cache cleared for: " + cacheKey);
    }
    
    public boolean isCached() {
        return cached;
    }
    
    private String generateCacheKey(MediaSource source) {
        return "cache_" + source.hashCode() + "_" + System.currentTimeMillis();
    }
    
    private boolean isCacheExpired() {
        // Cache expires after 5 minutes
        return (System.currentTimeMillis() - cacheTimestamp) > 300000;
    }
    
    private long getCacheAge() {
        return System.currentTimeMillis() - cacheTimestamp;
    }
}
