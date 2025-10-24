package com.modularmedia.sources;

import com.modularmedia.core.MediaSource;

/**
 * ADAPTER PATTERN - Concrete Adapter for Remote APIs
 * 
 * This adapter wraps remote API operations to conform to the MediaSource interface.
 * It handles the complexity of API authentication, data fetching, and protocol management.
 * 
 * Design Rationale:
 * - Encapsulates API-specific logic (authentication, data parsing, etc.)
 * - Provides uniform interface for remote media sources
 * - Isolates network API dependencies from the rest of the system
 */
public class RemoteAPIAdapter implements MediaSource {
    private String apiUrl;
    private boolean loaded = false;
    private String mediaId;

    public RemoteAPIAdapter(String apiUrl) { 
        this.apiUrl = apiUrl;
        this.mediaId = extractMediaId(apiUrl);
    }

    @Override 
    public void load() { 
        System.out.println("Connecting to remote API: " + apiUrl);
        System.out.println("  → Authenticating with API server...");
        System.out.println("  → Fetching media metadata for ID: " + mediaId);
        System.out.println("  → Validating media availability...");
        System.out.println("  → Setting up API stream connection...");
        this.loaded = true;
    }
    
    @Override 
    public void play() { 
        if (!loaded) {
            load();
        }
        System.out.println("Streaming from API: " + apiUrl);
        System.out.println("  → Using authenticated API session");
        System.out.println("  → Streaming media ID: " + mediaId);
    }
    
    @Override
    public String getSourceInfo() {
        return "Remote API: " + apiUrl + " (ID: " + mediaId + ")";
    }
    
    @Override
    public boolean isReady() {
        return loaded;
    }
    
    private String extractMediaId(String url) {
        // Simple extraction - in real implementation, this would be more sophisticated
        if (url.contains("id=")) {
            return url.substring(url.indexOf("id=") + 3);
        }
        return "unknown";
    }
}
