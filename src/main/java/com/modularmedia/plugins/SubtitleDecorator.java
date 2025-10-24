package com.modularmedia.plugins;

/**
 * DECORATOR PATTERN - Concrete Decorator for Subtitles
 * 
 * This decorator adds subtitle rendering functionality to any Media object.
 * It wraps the original media and adds subtitle overlay during playback.
 * 
 * Design Rationale:
 * - Adds subtitle functionality without modifying existing Media classes
 * - Can be applied to any Media object (files, streams, playlists)
 * - Enables runtime addition/removal of subtitles
 * - Maintains single responsibility principle
 */
public class SubtitleDecorator extends MediaDecorator {
    private String subtitleFile;
    private String language;

    public SubtitleDecorator(com.modularmedia.core.Media media, String subtitleFile) {
        super(media);
        this.subtitleFile = subtitleFile;
        this.language = "en"; // Default language
    }
    
    public SubtitleDecorator(com.modularmedia.core.Media media, String subtitleFile, String language) {
        super(media);
        this.subtitleFile = subtitleFile;
        this.language = language;
    }

    @Override
    public void play() {
        super.play();
        System.out.println("  → Loading subtitles from: " + subtitleFile);
        System.out.println("  → Rendering subtitles in " + language + " language");
        System.out.println("  → Synchronizing subtitle timing...");
    }
    
    @Override
    public String getDecoratorInfo() {
        return "Subtitles: " + subtitleFile + " (" + language + ")";
    }
}
