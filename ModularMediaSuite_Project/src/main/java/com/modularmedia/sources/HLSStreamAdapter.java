package com.modularmedia.sources;

import com.modularmedia.core.MediaSource;

/**
 * ADAPTER PATTERN - Concrete Adapter for HLS Streams
 * 
 * This adapter wraps HLS (HTTP Live Streaming) operations to conform to the MediaSource interface.
 * It handles the complexity of streaming protocols and playlist parsing.
 * 
 * Design Rationale:
 * - Encapsulates HLS-specific logic (playlist parsing, segment management, etc.)
 * - Provides uniform interface for streaming media
 * - Isolates network streaming dependencies from the rest of the system
 */
public class HLSStreamAdapter implements MediaSource {
    private String playlistUrl;
    private boolean loaded = false;
    private int segmentCount = 0;

    public HLSStreamAdapter(String playlistUrl) { 
        this.playlistUrl = playlistUrl; 
    }

    @Override 
    public void load() { 
        System.out.println("Loading HLS playlist: " + playlistUrl);
        System.out.println("  → Connecting to HLS server...");
        System.out.println("  → Parsing playlist manifest...");
        System.out.println("  → Detecting stream quality levels...");
        System.out.println("  → Initializing segment buffer...");
        this.segmentCount = (int)(Math.random() * 20) + 10; // Simulate segment count
        this.loaded = true;
    }
    
    @Override 
    public void play() { 
        if (!loaded) {
            load();
        }
        System.out.println("Playing HLS stream: " + playlistUrl);
        System.out.println("  → Streaming " + segmentCount + " segments");
        System.out.println("  → Using adaptive bitrate streaming");
    }
    
    @Override
    public String getSourceInfo() {
        return "HLS Stream: " + playlistUrl + " (" + segmentCount + " segments)";
    }
    
    @Override
    public boolean isReady() {
        return loaded;
    }
}
