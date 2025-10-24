package com.modularmedia.plugins;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.modularmedia.core.Media;

/**
 * Register decorator factories (functions that wrap a Media and return a decorated Media).
 */
public class PluginManager {
    private final List<Function<Media, Media>> decorators = new ArrayList<>();

    public void registerDecorator(Function<Media, Media> decoratorFactory) {
        decorators.add(decoratorFactory);
    }

    public void unregisterDecorator(Function<Media, Media> decoratorFactory) {
        decorators.remove(decoratorFactory);
    }

    /**
     * Wrap the base media with the registered decorators in order.
     */
    public Media applyDecorators(Media base) {
        Media current = base;
        for (Function<Media, Media> f : decorators) {
            current = f.apply(current);
        }
        return current;
    }

    public void clear() {
        decorators.clear();
    }
}
