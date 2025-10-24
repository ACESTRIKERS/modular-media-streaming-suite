# Modular Media Streaming Suite

## How to Compile & Run

```bash
# Compile
javac -d target/classes -cp . -source 11 -target 11 src/main/java/com/modularmedia/**/*.java

# Run
java -cp target/classes com.modularmedia.app.Main
```

## Description
A modular Java media streaming system demonstrating structural design patterns:
- **Adapter** – Integrates Local, Remote, and HLS sources.
- **Composite** – Handles playlists and sub-playlists.
- **Decorator** – Adds subtitles, watermarks, equalizers dynamically.
- **Proxy** – Implements caching for remote streams.
- **Strategy** – Switches between software and hardware renderers.
- **Facade** – Simplifies media control through PlayerFacade.

## Eclipse IDE Setup
1. Import as Existing Maven Project
2. Run `Main.java` as Java Application

## Author
Brambram Bangahon – 2025
