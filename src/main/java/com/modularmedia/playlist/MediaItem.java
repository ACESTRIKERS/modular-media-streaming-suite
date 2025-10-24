package com.modularmedia.playlist;

import com.modularmedia.core.MediaSource;

/**
 * COMPOSITE PATTERN - Leaf Component
 * 
 * This class represents an individual media item in a playlist.
 * It implements the Media interface and wraps a MediaSource.
 * 
 * Design Rationale:
 * - Represents individual media items in the composite structure
 * - Implements the same Media interface as Playlist for uniform treatment
 * - Acts as a bridge between the playlist system and media sources
 * - Maintains single responsibility for individual media playback
 */
public class MediaItem implements com.modularmedia.core.Media {
    private String title;
    private MediaSource source;
    private String description;

    public MediaItem(String title, MediaSource source) {
        this.title = title;
        this.source = source;
        this.description = source.getSourceInfo();
    }
    
    public MediaItem(String title, MediaSource source, String description) {
        this.title = title;
        this.source = source;
        this.description = description;
    }

    @Override
    public void play() {
        System.out.println("Now playing: " + title);
        System.out.println("  → Source: " + description);
        source.load();
        source.play();
    }
    
    public String getTitle() {
        return title;
    }
    
    public MediaSource getSource() {
        return source;
    }
    
    public String getDescription() {
        return description;
    }
}
