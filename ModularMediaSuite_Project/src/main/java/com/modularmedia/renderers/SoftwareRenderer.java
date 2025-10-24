package com.modularmedia.renderers;

/**
 * STRATEGY PATTERN - Concrete Strategy for Software Rendering
 * 
 * This renderer uses CPU-based software rendering for media playback.
 * It provides a fallback option when hardware acceleration is not available.
 * 
 * Design Rationale:
 * - Implements software-based rendering strategy
 * - Provides reliable fallback when hardware rendering fails
 * - Encapsulates software rendering logic
 * - Maintains consistent interface with other renderers
 */
public class SoftwareRenderer implements Renderer { 
    private boolean initialized = false;
    
    @Override 
    public void render(String content) { 
        if (!initialized) {
            initialize();
        }
        System.out.println("[Software Rendering] " + content);
        System.out.println("  → Using CPU-based rendering pipeline");
        System.out.println("  → Software decoder active");
    }
    
    @Override
    public String getRendererInfo() {
        return "Software Renderer (CPU-based)";
    }
    
    @Override
    public boolean isAvailable() {
        return true; // Software rendering is always available
    }
    
    @Override
    public void initialize() {
        System.out.println("  → Initializing software renderer...");
        System.out.println("  → Setting up CPU rendering pipeline...");
        System.out.println("  → Software renderer ready");
        initialized = true;
    }
}
