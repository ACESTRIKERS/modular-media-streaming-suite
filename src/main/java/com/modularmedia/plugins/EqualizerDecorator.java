package com.modularmedia.plugins;

/**
 * DECORATOR PATTERN - Concrete Decorator for Audio Equalizer
 * 
 * This decorator adds audio equalizer functionality to any Media object.
 * It wraps the original media and applies audio processing during playback.
 * 
 * Design Rationale:
 * - Adds audio processing without modifying existing Media classes
 * - Can be applied to any Media object (files, streams, playlists)
 * - Enables runtime addition/removal of audio effects
 * - Maintains single responsibility principle
 */
public class EqualizerDecorator extends MediaDecorator {
    private String preset;
    private int[] frequencyBands;

    public EqualizerDecorator(com.modularmedia.core.Media media, String preset) {
        super(media);
        this.preset = preset;
        this.frequencyBands = getPresetBands(preset);
    }

    @Override
    public void play() {
        super.play();
        System.out.println("  → Applying equalizer preset: " + preset);
        System.out.println("  → Processing audio with " + frequencyBands.length + " frequency bands");
        System.out.println("  → Real-time audio enhancement active");
    }
    
    @Override
    public String getDecoratorInfo() {
        return "Equalizer: " + preset + " (" + frequencyBands.length + " bands)";
    }
    
    private int[] getPresetBands(String preset) {
        switch (preset.toLowerCase()) {
            case "bass boost":
                return new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // 10 bands
            case "treble boost":
                return new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            case "vocal":
                return new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            default:
                return new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        }
    }
}
