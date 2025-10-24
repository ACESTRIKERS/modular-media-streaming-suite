# Mermaid UML Diagrams for Structural Design Patterns

## 1. Adapter Pattern

```mermaid
classDiagram
    class MediaSource {
        <<interface>>
        +play()
        +load()
        +getSourceInfo()
        +isReady()
    }
    
    class Media {
        <<interface>>
        +play()
    }
    
    class LocalFileAdapter {
        -filePath: String
        -loaded: boolean
        -fileFormat: String
        +LocalFileAdapter(filePath)
        +load()
        +play()
        +getSourceInfo()
        +isReady()
        -detectFileFormat(path)
    }
    
    class HLSStreamAdapter {
        -playlistUrl: String
        -loaded: boolean
        -segmentCount: int
        +HLSStreamAdapter(playlistUrl)
        +load()
        +play()
        +getSourceInfo()
        +isReady()
    }
    
    class RemoteAPIAdapter {
        -apiUrl: String
        -loaded: boolean
        -mediaId: String
        +RemoteAPIAdapter(apiUrl)
        +load()
        +play()
        +getSourceInfo()
        +isReady()
        -extractMediaId(url)
    }
    
    Media <|-- MediaSource
    MediaSource <|.. LocalFileAdapter
    MediaSource <|.. HLSStreamAdapter
    MediaSource <|.. RemoteAPIAdapter
    
    note for MediaSource "Target Interface<br/>Unified contract for all media sources"
    note for LocalFileAdapter "Adapts local file operations<br/>to MediaSource interface"
    note for HLSStreamAdapter "Adapts HLS streaming<br/>to MediaSource interface"
    note for RemoteAPIAdapter "Adapts remote API calls<br/>to MediaSource interface"
```

## 2. Proxy Pattern

```mermaid
classDiagram
    class MediaSource {
        <<interface>>
        +play()
        +load()
        +getSourceInfo()
        +isReady()
    }
    
    class RemoteAPIAdapter {
        -apiUrl: String
        -loaded: boolean
        -mediaId: String
        +load()
        +play()
        +getSourceInfo()
        +isReady()
    }
    
    class RemoteProxy {
        -remoteSource: MediaSource
        -cached: boolean
        -cacheKey: String
        -cacheTimestamp: long
        +RemoteProxy(remoteSource)
        +load()
        +play()
        +getSourceInfo()
        +isReady()
        +clearCache()
        +isCached()
        -generateCacheKey(source)
        -isCacheExpired()
        -getCacheAge()
    }
    
    MediaSource <|.. RemoteAPIAdapter
    MediaSource <|.. RemoteProxy
    RemoteProxy --> RemoteAPIAdapter : wraps
    
    note for RemoteProxy "Proxy adds caching behavior<br/>without modifying original source"
    note for RemoteAPIAdapter "Real Subject<br/>Actual remote API implementation"
```

## 3. Composite Pattern

```mermaid
classDiagram
    class Media {
        <<interface>>
        +play()
    }
    
    class MediaItem {
        -title: String
        -source: MediaSource
        -description: String
        +MediaItem(title, source)
        +play()
        +getTitle()
        +getSource()
        +getDescription()
    }
    
    class Playlist {
        -name: String
        -items: List~Media~
        -currentIndex: int
        +Playlist(name)
        +add(media)
        +remove(media)
        +getName()
        +getItemCount()
        +getItems()
        +play()
        -getItemDescription(media)
    }
    
    class MediaSource {
        <<interface>>
        +play()
        +load()
        +getSourceInfo()
        +isReady()
    }
    
    Media <|.. MediaItem
    Media <|.. Playlist
    MediaItem --> MediaSource : uses
    Playlist --> Media : contains
    
    note for Media "Component Interface<br/>Common contract for all media"
    note for MediaItem "Leaf Component<br/>Individual media items"
    note for Playlist "Composite Component<br/>Can contain other playlists"
```

## 4. Decorator Pattern

```mermaid
classDiagram
    class Media {
        <<interface>>
        +play()
    }
    
    class MediaDecorator {
        <<abstract>>
        #decoratedMedia: Media
        +MediaDecorator(decoratedMedia)
        +play()
        +getDecoratorInfo()*
    }
    
    class PlayerFacade {
        -renderer: Renderer
        -playlist: Playlist
        -isPlaying: boolean
        +PlayerFacade(renderer)
        +setRenderer(renderer)
        +load(playlist)
        +play()
        +stop()
        +pause()
        +isPlaying()
        +getStatus()
    }
    
    class WatermarkDecorator {
        -watermark: String
        -position: String
        +WatermarkDecorator(media, watermark)
        +WatermarkDecorator(media, watermark, position)
        +play()
        +getDecoratorInfo()
    }
    
    class SubtitleDecorator {
        -subtitleFile: String
        -language: String
        +SubtitleDecorator(media, subtitleFile)
        +SubtitleDecorator(media, subtitleFile, language)
        +play()
        +getDecoratorInfo()
    }
    
    class EqualizerDecorator {
        -preset: String
        -frequencyBands: int[]
        +EqualizerDecorator(media, preset)
        +play()
        +getDecoratorInfo()
        -getPresetBands(preset)
    }
    
    Media <|.. PlayerFacade
    Media <|.. MediaDecorator
    MediaDecorator <|-- WatermarkDecorator
    MediaDecorator <|-- SubtitleDecorator
    MediaDecorator <|-- EqualizerDecorator
    MediaDecorator --> Media : wraps
    WatermarkDecorator --> Media : decorates
    SubtitleDecorator --> Media : decorates
    EqualizerDecorator --> Media : decorates
    
    note for MediaDecorator "Abstract Decorator<br/>Base class for all decorators"
    note for PlayerFacade "Concrete Component<br/>Object being decorated"
```

## 5. Facade Pattern

```mermaid
classDiagram
    class PlayerFacade {
        -renderer: Renderer
        -playlist: Playlist
        -isPlaying: boolean
        +PlayerFacade(renderer)
        +setRenderer(renderer)
        +load(playlist)
        +play()
        +stop()
        +pause()
        +isPlaying()
        +getStatus()
    }
    
    class Renderer {
        <<interface>>
        +render(content)
        +getRendererInfo()
        +isAvailable()
        +initialize()
    }
    
    class SoftwareRenderer {
        -initialized: boolean
        +render(content)
        +getRendererInfo()
        +isAvailable()
        +initialize()
    }
    
    class HardwareRenderer {
        -initialized: boolean
        -gpuAvailable: boolean
        +render(content)
        +getRendererInfo()
        +isAvailable()
        +initialize()
    }
    
    class Playlist {
        -name: String
        -items: List~Media~
        +add(media)
        +remove(media)
        +play()
        +getName()
        +getItemCount()
    }
    
    class Media {
        <<interface>>
        +play()
    }
    
    class Main {
        +main(args)
        -demonstrateLegacySystem()
        -demonstrateStructuralPatterns()
    }
    
    Main --> PlayerFacade : uses
    PlayerFacade --> Renderer : uses
    PlayerFacade --> Playlist : uses
    Renderer <|.. SoftwareRenderer
    Renderer <|.. HardwareRenderer
    Playlist --> Media : contains
    
    note for PlayerFacade "Facade<br/>Simplified interface to complex subsystem"
    note for Main "Client<br/>Uses simplified facade interface"
```

## 6. Strategy Pattern

```mermaid
classDiagram
    class Renderer {
        <<interface>>
        +render(content)
        +getRendererInfo()
        +isAvailable()
        +initialize()
    }
    
    class SoftwareRenderer {
        -initialized: boolean
        +render(content)
        +getRendererInfo()
        +isAvailable()
        +initialize()
    }
    
    class HardwareRenderer {
        -initialized: boolean
        -gpuAvailable: boolean
        +render(content)
        +getRendererInfo()
        +isAvailable()
        +initialize()
    }
    
    class PlayerFacade {
        -renderer: Renderer
        -playlist: Playlist
        -isPlaying: boolean
        +setRenderer(renderer)
        +play()
    }
    
    Renderer <|.. SoftwareRenderer
    Renderer <|.. HardwareRenderer
    PlayerFacade --> Renderer : uses
    
    note for Renderer "Strategy Interface<br/>Defines rendering algorithm contract"
    note for SoftwareRenderer "Concrete Strategy<br/>CPU-based rendering"
    note for HardwareRenderer "Concrete Strategy<br/>GPU-accelerated rendering"
    note for PlayerFacade "Context<br/>Uses strategy and can switch at runtime"
```

## 7. Complete System Architecture

```mermaid
classDiagram
    class Main {
        +main(args)
        -demonstrateLegacySystem()
        -demonstrateStructuralPatterns()
    }
    
    class PlayerFacade {
        -renderer: Renderer
        -playlist: Playlist
        +play()
        +setRenderer(renderer)
        +load(playlist)
    }
    
    class Media {
        <<interface>>
        +play()
    }
    
    class MediaSource {
        <<interface>>
        +load()
        +getSourceInfo()
        +isReady()
    }
    
    class Playlist {
        -items: List~Media~
        +add(media)
        +play()
    }
    
    class MediaItem {
        -source: MediaSource
        +play()
    }
    
    class LocalFileAdapter {
        +load()
        +play()
    }
    
    class HLSStreamAdapter {
        +load()
        +play()
    }
    
    class RemoteAPIAdapter {
        +load()
        +play()
    }
    
    class RemoteProxy {
        -remoteSource: MediaSource
        +load()
        +play()
    }
    
    class MediaDecorator {
        <<abstract>>
        #decoratedMedia: Media
        +play()
    }
    
    class WatermarkDecorator {
        +play()
    }
    
    class SubtitleDecorator {
        +play()
    }
    
    class EqualizerDecorator {
        +play()
    }
    
    class Renderer {
        <<interface>>
        +render(content)
    }
    
    class SoftwareRenderer {
        +render(content)
    }
    
    class HardwareRenderer {
        +render(content)
    }
    
    %% Relationships
    Main --> PlayerFacade
    PlayerFacade --> Renderer
    PlayerFacade --> Playlist
    Media <|-- MediaSource
    Media <|-- Playlist
    Media <|-- MediaItem
    Media <|-- MediaDecorator
    MediaSource <|-- LocalFileAdapter
    MediaSource <|-- HLSStreamAdapter
    MediaSource <|-- RemoteAPIAdapter
    MediaSource <|-- RemoteProxy
    MediaDecorator <|-- WatermarkDecorator
    MediaDecorator <|-- SubtitleDecorator
    MediaDecorator <|-- EqualizerDecorator
    Renderer <|-- SoftwareRenderer
    Renderer <|-- HardwareRenderer
    Playlist --> Media
    MediaItem --> MediaSource
    RemoteProxy --> MediaSource
    MediaDecorator --> Media
```

## Usage Instructions

1. Copy any diagram code above
2. Paste into Mermaid Live Editor: https://mermaid.live/
3. Or use in documentation tools that support Mermaid
4. Each diagram shows the structure and relationships of one specific pattern
5. The final diagram shows how all patterns work together in the system
