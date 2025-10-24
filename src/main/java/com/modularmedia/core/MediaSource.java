package com.modularmedia.core;

/**
 * ADAPTER PATTERN - Target Interface
 * 
 * This interface defines the common contract that all media sources must implement.
 * It allows different types of media sources (files, streams, APIs) to be treated uniformly.
 * 
 * Design Rationale:
 * - Provides a unified interface for heterogeneous media sources
 * - Enables runtime switching between different source types
 * - Supports the Open/Closed Principle - new source types can be added without modifying existing code
 */
public interface MediaSource extends Media { 
    /**
     * Load the media source and prepare it for playback
     */
    void load(); 
    
    /**
     * Get metadata about the media source
     */
    String getSourceInfo();
    
    /**
     * Check if the source is ready for playback
     */
    boolean isReady();
}
