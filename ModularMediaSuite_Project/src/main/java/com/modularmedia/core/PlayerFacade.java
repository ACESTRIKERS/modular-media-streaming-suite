package com.modularmedia.core;

import com.modularmedia.playlist.Playlist;
import com.modularmedia.renderers.Renderer;

/**
 * FACADE PATTERN - Facade Class
 * 
 * This class provides a simplified interface to the complex subsystem of
 * media playback, rendering, and playlist management. It hides the complexity
 * of the underlying system and provides a unified interface for clients.
 * 
 * Design Rationale:
 * - Simplifies the interface to a complex subsystem
 * - Reduces dependencies between clients and subsystem classes
 * - Provides a single point of access to the media playback system
 * - Encapsulates the complexity of managing multiple components
 */
public class PlayerFacade implements Media {
    private Renderer renderer;
    private Playlist playlist;
    private boolean isPlaying = false;

    public PlayerFacade(Renderer renderer) { 
        this.renderer = renderer; 
        System.out.println("Player initialized with: " + renderer.getRendererInfo());
    }

    public void setRenderer(Renderer renderer) {
        if (renderer.isAvailable()) {
            this.renderer = renderer;
            System.out.println("Renderer switched to: " + renderer.getRendererInfo());
        } else {
            System.out.println("Renderer not available: " + renderer.getRendererInfo());
        }
    }

    public void load(Playlist playlist) {
        this.playlist = playlist;
        System.out.println("Playlist loaded: " + playlist.getName() + " (" + playlist.getItemCount() + " items)");
    }

    @Override
    public void play() {
        if (playlist != null) {
            System.out.println("Starting playback with " + renderer.getRendererInfo() + "...");
            isPlaying = true;
            renderer.render("Playback started");
            playlist.play();
            isPlaying = false;
        } else {
            System.out.println("No playlist loaded!");
        }
    }
    
    public void stop() {
        if (isPlaying) {
            System.out.println("Stopping playback...");
            isPlaying = false;
        }
    }
    
    public void pause() {
        if (isPlaying) {
            System.out.println("Playback paused");
        }
    }
    
    public boolean isPlaying() {
        return isPlaying;
    }
    
    public String getStatus() {
        return "Player Status: " + (isPlaying ? "Playing" : "Stopped") + 
               " | Renderer: " + renderer.getRendererInfo() +
               " | Playlist: " + (playlist != null ? playlist.getName() : "None");
    }
}
