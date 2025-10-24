package com.modularmedia.plugins;

/**
 * DECORATOR PATTERN - Concrete Decorator for Watermarking
 * 
 * This decorator adds watermarking functionality to any Media object.
 * It wraps the original media and adds watermark rendering during playback.
 * 
 * Design Rationale:
 * - Adds watermarking functionality without modifying existing Media classes
 * - Can be applied to any Media object (files, streams, playlists)
 * - Enables runtime addition/removal of watermarking
 * - Maintains single responsibility principle
 */
public class WatermarkDecorator extends MediaDecorator {
    private String watermark;
    private String position;

    public WatermarkDecorator(com.modularmedia.core.Media media, String watermark) {
        super(media);
        this.watermark = watermark;
        this.position = "bottom-right"; // Default position
    }
    
    public WatermarkDecorator(com.modularmedia.core.Media media, String watermark, String position) {
        super(media);
        this.watermark = watermark;
        this.position = position;
    }

    @Override
    public void play() {
        super.play();
        System.out.println("  → Applying watermark: '" + watermark + "' at " + position);
        System.out.println("  → Rendering watermark overlay...");
    }
    
    @Override
    public String getDecoratorInfo() {
        return "Watermark: '" + watermark + "' at " + position;
    }
}
