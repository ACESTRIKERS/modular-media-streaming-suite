package com.modularmedia.renderers;

/**
 * STRATEGY PATTERN - Strategy Interface
 * 
 * This interface defines the contract for different rendering strategies.
 * It allows the system to switch between different rendering approaches
 * (hardware vs software) at runtime without modifying client code.
 * 
 * Design Rationale:
 * - Encapsulates different rendering algorithms
 * - Enables runtime switching between rendering strategies
 * - Supports the Open/Closed Principle - new renderers can be added without modifying existing code
 * - Isolates rendering logic from the rest of the system
 */
public interface Renderer { 
    /**
     * Render the given content using this rendering strategy
     */
    void render(String content);
    
    /**
     * Get information about this renderer
     */
    String getRendererInfo();
    
    /**
     * Check if this renderer is available
     */
    boolean isAvailable();
    
    /**
     * Initialize the renderer
     */
    void initialize();
}
