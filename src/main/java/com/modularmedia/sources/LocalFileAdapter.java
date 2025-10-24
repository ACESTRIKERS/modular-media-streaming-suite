package com.modularmedia.sources;

import com.modularmedia.core.MediaSource;

/**
 * ADAPTER PATTERN - Concrete Adapter for Local Files
 * 
 * This adapter wraps local file operations to conform to the MediaSource interface.
 * It handles the complexity of file system operations and provides a uniform interface.
 * 
 * Design Rationale:
 * - Encapsulates file-specific logic (path validation, format detection, etc.)
 * - Provides consistent interface regardless of file format
 * - Isolates file system dependencies from the rest of the system
 */
public class LocalFileAdapter implements MediaSource {
    private String filePath;
    private boolean loaded = false;
    private String fileFormat;

    public LocalFileAdapter(String filePath) { 
        this.filePath = filePath;
        this.fileFormat = detectFileFormat(filePath);
    }

    @Override 
    public void load() { 
        System.out.println("Loading local file: " + filePath);
        System.out.println("  → Detected format: " + fileFormat);
        System.out.println("  → Validating file path...");
        System.out.println("  → Initializing " + fileFormat + " decoder...");
        System.out.println("  → Setting up file buffers...");
        this.loaded = true;
    }
    
    @Override 
    public void play() { 
        if (!loaded) {
            load();
        }
        System.out.println("Playing local file: " + filePath);
        System.out.println("  → Using " + fileFormat + " playback engine");
    }
    
    @Override
    public String getSourceInfo() {
        return "Local File: " + filePath + " (" + fileFormat + ")";
    }
    
    @Override
    public boolean isReady() {
        return loaded;
    }
    
    private String detectFileFormat(String path) {
        if (path.endsWith(".mp4")) return "MP4";
        if (path.endsWith(".avi")) return "AVI";
        if (path.endsWith(".mkv")) return "MKV";
        if (path.endsWith(".mov")) return "MOV";
        return "Unknown";
    }
}
