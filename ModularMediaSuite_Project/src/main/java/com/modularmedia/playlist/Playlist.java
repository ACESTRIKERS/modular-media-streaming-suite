package com.modularmedia.playlist;

import java.util.ArrayList;
import java.util.List;

import com.modularmedia.core.Media;

/**
 * COMPOSITE PATTERN - Composite Component
 * 
 * This class represents a playlist that can contain both individual media items
 * and other playlists (nested playlists). It implements the same Media interface
 * as individual items, allowing uniform treatment of single items and collections.
 * 
 * Design Rationale:
 * - Treats individual items and collections uniformly through the Media interface
 * - Enables recursive composition (playlists within playlists)
 * - Simplifies client code by providing a consistent interface
 * - Supports tree-like structures for organizing media content
 */
public class Playlist implements Media {
    private String name;
    private List<Media> items = new ArrayList<>();
    private int currentIndex = 0;

    public Playlist(String name) { 
        this.name = name; 
    }
    
    public void add(Media media) { 
        items.add(media); 
        System.out.println("  → Added to playlist '" + name + "': " + getItemDescription(media));
    }
    
    public void remove(Media media) {
        if (items.remove(media)) {
            System.out.println("  → Removed from playlist '" + name + "': " + getItemDescription(media));
        }
    }
    
    public String getName() { 
        return name; 
    }
    
    public int getItemCount() {
        return items.size();
    }
    
    public List<Media> getItems() {
        return new ArrayList<>(items);
    }

    @Override
    public void play() {
        System.out.println("Playing playlist: " + name + " (" + items.size() + " items)");
        System.out.println("==========================================");
        
        for (int i = 0; i < items.size(); i++) {
            Media item = items.get(i);
            System.out.println("[" + (i + 1) + "/" + items.size() + "] " + getItemDescription(item));
            item.play();
            System.out.println("---");
        }
        
        System.out.println("Playlist '" + name + "' completed.");
    }
    
    private String getItemDescription(Media media) {
        if (media instanceof Playlist) {
            Playlist playlist = (Playlist) media;
            return "Playlist: " + playlist.getName() + " (" + playlist.getItemCount() + " items)";
        } else if (media instanceof MediaItem) {
            MediaItem item = (MediaItem) media;
            return "Media: " + item.getTitle();
        } else {
            return "Media: " + media.getClass().getSimpleName();
        }
    }
}
