package com.modularmedia.plugins;

import com.modularmedia.core.Media;

/**
 * DECORATOR PATTERN - Abstract Decorator
 * 
 * This abstract class provides the base structure for all media decorators.
 * It implements the Media interface and maintains a reference to the wrapped Media object.
 * 
 * Design Rationale:
 * - Provides a common base for all decorators
 * - Enables chaining of multiple decorators
 * - Maintains the same interface as the wrapped object
 * - Supports the Open/Closed Principle - new decorators can be added without modifying existing code
 */
public abstract class MediaDecorator implements Media {
    protected Media decoratedMedia;

    public MediaDecorator(Media decoratedMedia) { 
        this.decoratedMedia = decoratedMedia; 
    }

    @Override 
    public void play() { 
        // Delegate to the wrapped media object
        decoratedMedia.play(); 
    }
    
    /**
     * Get information about this decorator
     */
    public abstract String getDecoratorInfo();
}
