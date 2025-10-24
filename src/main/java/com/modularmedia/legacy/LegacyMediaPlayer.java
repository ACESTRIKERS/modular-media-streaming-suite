package com.modularmedia.legacy;

import java.util.List;

/**
 * LEGACY CODE - Intentionally messy monolithic media player
 * This represents the "before" state that needs refactoring
 * 
 * Problems:
 * - Monolithic class handling everything
 * - Duplicated code for different media types
 * - No plugin mechanism
 * - Hard-coded rendering logic
 * - No caching mechanism
 * - Tight coupling between components
 */
public class LegacyMediaPlayer {
    
    private String currentFile;
    private String currentStream;
    private String currentAPI;
    private boolean isHardwareRendering = false;
    private boolean hasWatermark = false;
    private boolean hasSubtitles = false;
    private boolean hasEqualizer = false;
    private String watermarkText = "";
    private String subtitleFile = "";
    private String equalizerPreset = "";
    
    // Monolithic play method - handles everything
    public void playFile(String filePath) {
        System.out.println("=== LEGACY PLAYER: Playing File ===");
        
        // Duplicated file handling logic
        if (filePath.endsWith(".mp4")) {
            System.out.println("Loading MP4 file: " + filePath);
            System.out.println("Initializing MP4 decoder...");
            System.out.println("Setting up MP4-specific buffers...");
        } else if (filePath.endsWith(".avi")) {
            System.out.println("Loading AVI file: " + filePath);
            System.out.println("Initializing AVI decoder...");
            System.out.println("Setting up AVI-specific buffers...");
        } else if (filePath.endsWith(".mkv")) {
            System.out.println("Loading MKV file: " + filePath);
            System.out.println("Initializing MKV decoder...");
            System.out.println("Setting up MKV-specific buffers...");
        }
        
        // Hard-coded rendering logic
        if (isHardwareRendering) {
            System.out.println("Using hardware rendering (hardcoded)...");
        } else {
            System.out.println("Using software rendering (hardcoded)...");
        }
        
        // Hard-coded feature application
        if (hasWatermark) {
            System.out.println("Applying watermark: " + watermarkText + " (hardcoded)");
        }
        if (hasSubtitles) {
            System.out.println("Loading subtitles: " + subtitleFile + " (hardcoded)");
        }
        if (hasEqualizer) {
            System.out.println("Applying equalizer: " + equalizerPreset + " (hardcoded)");
        }
        
        System.out.println("Playing: " + filePath);
        this.currentFile = filePath;
    }
    
    // Duplicated stream handling logic
    public void playStream(String streamUrl) {
        System.out.println("=== LEGACY PLAYER: Playing Stream ===");
        
        if (streamUrl.contains("m3u8")) {
            System.out.println("Loading HLS stream: " + streamUrl);
            System.out.println("Parsing HLS playlist...");
            System.out.println("Setting up HLS-specific buffers...");
        } else if (streamUrl.contains("rtmp")) {
            System.out.println("Loading RTMP stream: " + streamUrl);
            System.out.println("Connecting to RTMP server...");
            System.out.println("Setting up RTMP-specific buffers...");
        } else {
            System.out.println("Loading generic stream: " + streamUrl);
            System.out.println("Setting up generic stream buffers...");
        }
        
        // Same hard-coded rendering and feature logic
        if (isHardwareRendering) {
            System.out.println("Using hardware rendering (hardcoded)...");
        } else {
            System.out.println("Using software rendering (hardcoded)...");
        }
        
        if (hasWatermark) {
            System.out.println("Applying watermark: " + watermarkText + " (hardcoded)");
        }
        if (hasSubtitles) {
            System.out.println("Loading subtitles: " + subtitleFile + " (hardcoded)");
        }
        if (hasEqualizer) {
            System.out.println("Applying equalizer: " + equalizerPreset + " (hardcoded)");
        }
        
        System.out.println("Streaming: " + streamUrl);
        this.currentStream = streamUrl;
    }
    
    // Duplicated API handling logic
    public void playFromAPI(String apiUrl) {
        System.out.println("=== LEGACY PLAYER: Playing from API ===");
        
        System.out.println("Connecting to API: " + apiUrl);
        System.out.println("Authenticating with API...");
        System.out.println("Fetching media metadata...");
        System.out.println("Setting up API-specific buffers...");
        
        // Same hard-coded rendering and feature logic (duplicated again!)
        if (isHardwareRendering) {
            System.out.println("Using hardware rendering (hardcoded)...");
        } else {
            System.out.println("Using software rendering (hardcoded)...");
        }
        
        if (hasWatermark) {
            System.out.println("Applying watermark: " + watermarkText + " (hardcoded)");
        }
        if (hasSubtitles) {
            System.out.println("Loading subtitles: " + subtitleFile + " (hardcoded)");
        }
        if (hasEqualizer) {
            System.out.println("Applying equalizer: " + equalizerPreset + " (hardcoded)");
        }
        
        System.out.println("Streaming from API: " + apiUrl);
        this.currentAPI = apiUrl;
    }
    
    // Monolithic playlist handling
    public void playPlaylist(List<String> files) {
        System.out.println("=== LEGACY PLAYER: Playing Playlist ===");
        for (String file : files) {
            playFile(file); // Reusing the monolithic method
        }
    }
    
    // Hard-coded feature toggles
    public void enableWatermark(String text) {
        this.hasWatermark = true;
        this.watermarkText = text;
        System.out.println("Watermark enabled: " + text);
    }
    
    public void enableSubtitles(String file) {
        this.hasSubtitles = true;
        this.subtitleFile = file;
        System.out.println("Subtitles enabled: " + file);
    }
    
    public void enableEqualizer(String preset) {
        this.hasEqualizer = true;
        this.equalizerPreset = preset;
        System.out.println("Equalizer enabled: " + preset);
    }
    
    public void switchToHardwareRendering() {
        this.isHardwareRendering = true;
        System.out.println("Switched to hardware rendering");
    }
    
    public void switchToSoftwareRendering() {
        this.isHardwareRendering = false;
        System.out.println("Switched to software rendering");
    }
    
    // No caching mechanism - streams are fetched every time
    public void playCachedStream(String streamUrl) {
        System.out.println("=== LEGACY PLAYER: No Caching - Fetching Again ===");
        playStream(streamUrl); // Just calls the same method again
    }
}
