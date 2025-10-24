package com.modularmedia.renderers;

/**
 * STRATEGY PATTERN - Concrete Strategy for Hardware Rendering
 * 
 * This renderer uses GPU-based hardware acceleration for media playback.
 * It provides better performance and lower CPU usage when available.
 * 
 * Design Rationale:
 * - Implements hardware-accelerated rendering strategy
 * - Provides optimal performance when GPU is available
 * - Encapsulates hardware rendering logic
 * - Maintains consistent interface with other renderers
 */
public class HardwareRenderer implements Renderer { 
    private boolean initialized = false;
    private boolean gpuAvailable = true; // Simulate GPU availability check
    
    @Override 
    public void render(String content) { 
        if (!initialized) {
            initialize();
        }
        System.out.println("[Hardware Rendering] " + content);
        System.out.println("  → Using GPU-accelerated rendering pipeline");
        System.out.println("  → Hardware decoder active");
        System.out.println("  → GPU memory optimized");
    }
    
    @Override
    public String getRendererInfo() {
        return "Hardware Renderer (GPU-accelerated)";
    }
    
    @Override
    public boolean isAvailable() {
        return gpuAvailable; // Check if GPU is available
    }
    
    @Override
    public void initialize() {
        System.out.println("  → Initializing hardware renderer...");
        System.out.println("  → Detecting GPU capabilities...");
        System.out.println("  → Setting up hardware rendering pipeline...");
        System.out.println("  → Hardware renderer ready");
        initialized = true;
    }
}
