package com.modularmedia.app;

import com.modularmedia.core.Media;
import com.modularmedia.core.MediaSource;
import com.modularmedia.core.PlayerFacade;
import com.modularmedia.legacy.LegacyMediaPlayer;
import com.modularmedia.playlist.MediaItem;
import com.modularmedia.playlist.Playlist;
import com.modularmedia.plugins.EqualizerDecorator;
import com.modularmedia.plugins.SubtitleDecorator;
import com.modularmedia.plugins.WatermarkDecorator;
import com.modularmedia.proxy.RemoteProxy;
import com.modularmedia.renderers.HardwareRenderer;
import com.modularmedia.renderers.Renderer;
import com.modularmedia.renderers.SoftwareRenderer;
import com.modularmedia.sources.HLSStreamAdapter;
import com.modularmedia.sources.LocalFileAdapter;
import com.modularmedia.sources.RemoteAPIAdapter;

/**
 * COMPREHENSIVE DEMO: Evolution from Legacy to Structural Patterns
 * 
 * This demo shows the transformation from a monolithic legacy media player
 * to a modular, extensible system using structural design patterns.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println("=== MODULAR MEDIA STREAMING SUITE - EVOLUTION DEMO ===");
        System.out.println("================================================================");
        
        // =================================================================
        // PART 1: LEGACY SYSTEM (The Problem)
        // =================================================================
        demonstrateLegacySystem();
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("=== EVOLUTION TO STRUCTURAL PATTERNS ===");
        System.out.println("=".repeat(80));
        
        // =================================================================
        // PART 2: STRUCTURAL PATTERNS SOLUTION
        // =================================================================
        demonstrateStructuralPatterns();
        
        System.out.println("\n================================================================");
        System.out.println("=== END OF EVOLUTION DEMO ===");
        System.out.println("================================================================");
    }
    
    private static void demonstrateLegacySystem() {
        System.out.println("\n[LEGACY] PART 1: LEGACY SYSTEM (The Problems)");
        System.out.println("=".repeat(50));
        
        LegacyMediaPlayer legacyPlayer = new LegacyMediaPlayer();
        
        System.out.println("\n[FILE] Legacy File Playback:");
        legacyPlayer.playFile("videos/movie.mp4");
        
        System.out.println("\n[STREAM] Legacy Stream Playback:");
        legacyPlayer.playStream("https://example.com/stream.m3u8");
        
        System.out.println("\n[API] Legacy API Playback:");
        legacyPlayer.playFromAPI("https://api.example.com/media?id=123");
        
        System.out.println("\n[FEATURES] Legacy Feature Management:");
        legacyPlayer.enableWatermark("© Legacy Co.");
        legacyPlayer.enableSubtitles("subs.srt");
        legacyPlayer.enableEqualizer("Bass Boost");
        legacyPlayer.switchToHardwareRendering();
        
        System.out.println("\n[PROBLEMS] LEGACY PROBLEMS DEMONSTRATED:");
        System.out.println("  • Monolithic class handling everything");
        System.out.println("  • Duplicated code for different media types");
        System.out.println("  • Hard-coded feature application");
        System.out.println("  • No plugin mechanism");
        System.out.println("  • No caching system");
        System.out.println("  • Tight coupling between components");
    }
    
    private static void demonstrateStructuralPatterns() {
        System.out.println("\n[PATTERNS] PART 2: STRUCTURAL PATTERNS SOLUTION");
        System.out.println("=".repeat(50));
        
        // =================================================================
        // ADAPTER PATTERN: Unified Media Sources
        // =================================================================
        System.out.println("\n[ADAPTER] ADAPTER PATTERN - Unified Media Sources");
        System.out.println("-".repeat(50));
        
        MediaSource localSource = new LocalFileAdapter("videos/movie.mp4");
        MediaSource remoteSource = new RemoteAPIAdapter("https://api.example.com/media?id=123");
        MediaSource hlsSource = new HLSStreamAdapter("https://cdn.example.com/playlist.m3u8");
        
        System.out.println("[OK] All sources now implement the same MediaSource interface");
        System.out.println("[OK] No more duplicated code for different media types");
        
        // =================================================================
        // PROXY PATTERN: Caching System
        // =================================================================
        System.out.println("\n[PROXY] PROXY PATTERN - Caching System");
        System.out.println("-".repeat(50));
        
        MediaSource cachedRemote = new RemoteProxy(remoteSource);
        System.out.println("[OK] Transparent caching without modifying original source");
        System.out.println("[OK] Improved performance and reduced network traffic");
        
        // =================================================================
        // COMPOSITE PATTERN: Nested Playlists
        // =================================================================
        System.out.println("\n[COMPOSITE] COMPOSITE PATTERN - Nested Playlists");
        System.out.println("-".repeat(50));
        
        // Create sub-playlists
        Playlist actionMovies = new Playlist("Action Movies");
        actionMovies.add(new MediaItem("Action Movie 1", localSource));
        actionMovies.add(new MediaItem("Action Movie 2", hlsSource));
        
        Playlist mainPlaylist = new Playlist("My Collection");
        mainPlaylist.add(new MediaItem("Local Movie", localSource));
        mainPlaylist.add(new MediaItem("HLS Stream", hlsSource));
        mainPlaylist.add(new MediaItem("Cached Remote Stream", cachedRemote));
        mainPlaylist.add(actionMovies); // Nested playlist!
        
        System.out.println("[OK] Playlists can contain other playlists (recursive composition)");
        System.out.println("[OK] Uniform treatment of individual items and collections");
        
        // =================================================================
        // STRATEGY PATTERN: Runtime Renderer Switching
        // =================================================================
        System.out.println("\n[STRATEGY] STRATEGY PATTERN - Runtime Renderer Switching");
        System.out.println("-".repeat(50));
        
        Renderer softwareRenderer = new SoftwareRenderer();
        Renderer hardwareRenderer = new HardwareRenderer();
        
        System.out.println("[OK] Multiple rendering strategies available");
        System.out.println("[OK] Runtime switching between hardware and software rendering");
        
        // =================================================================
        // FACADE PATTERN: Simplified Player Interface
        // =================================================================
        System.out.println("\n[FACADE] FACADE PATTERN - Simplified Player Interface");
        System.out.println("-".repeat(50));
        
        PlayerFacade player = new PlayerFacade(softwareRenderer);
        player.load(mainPlaylist);
        
        System.out.println("[OK] Simplified interface to complex subsystem");
        System.out.println("[OK] Single point of access for media playback");
        
        // =================================================================
        // DECORATOR PATTERN: Plugin System
        // =================================================================
        System.out.println("\n[DECORATOR] DECORATOR PATTERN - Plugin System");
        System.out.println("-".repeat(50));
        
        Media decoratedMedia = new WatermarkDecorator(player, "© Modular Media Co. 2025", "top-left");
        decoratedMedia = new SubtitleDecorator(decoratedMedia, "subs/movie_subtitles.srt", "en");
        decoratedMedia = new EqualizerDecorator(decoratedMedia, "Bass Boost");
        
        System.out.println("[OK] Features can be added/removed at runtime");
        System.out.println("[OK] No modification of existing classes required");
        System.out.println("[OK] Decorators can be chained together");
        
        // =================================================================
        // DEMONSTRATION: Full System in Action
        // =================================================================
        System.out.println("\n[DEMO] FULL SYSTEM DEMONSTRATION");
        System.out.println("=".repeat(50));
        
        System.out.println("\n[STATUS] System Status:");
        System.out.println("  " + player.getStatus());
        
        System.out.println("\n[PLAY] Playing with Software Renderer:");
        System.out.println("-".repeat(30));
        decoratedMedia.play();
        
        System.out.println("\n[SWITCH] Switching to Hardware Renderer:");
        System.out.println("-".repeat(30));
        player.setRenderer(hardwareRenderer);
        
        System.out.println("\n[REPLAY] Replaying with Hardware Renderer:");
        System.out.println("-".repeat(30));
        decoratedMedia.play();
        
        // =================================================================
        // BENEFITS SUMMARY
        // =================================================================
        System.out.println("\n[BENEFITS] STRUCTURAL PATTERNS BENEFITS:");
        System.out.println("  [ADAPTER] Adapter: Unified interface for heterogeneous sources");
        System.out.println("  [PROXY] Proxy: Transparent caching and performance optimization");
        System.out.println("  [COMPOSITE] Composite: Recursive composition and uniform treatment");
        System.out.println("  [STRATEGY] Strategy: Runtime algorithm switching");
        System.out.println("  [FACADE] Facade: Simplified complex subsystem interface");
        System.out.println("  [DECORATOR] Decorator: Runtime feature addition without modification");
        
        System.out.println("\n[PRINCIPLES] DESIGN PRINCIPLES ACHIEVED:");
        System.out.println("  • Single Responsibility Principle");
        System.out.println("  • Open/Closed Principle");
        System.out.println("  • Dependency Inversion Principle");
        System.out.println("  • Interface Segregation Principle");
    }
}
