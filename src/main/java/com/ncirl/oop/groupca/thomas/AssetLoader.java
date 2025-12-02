package com.ncirl.oop.groupca.thomas;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles loading assets from the <code>resources</code> folder.
 */
public class AssetLoader {
    private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
    private static final Map<String, Image> cachedAssets = new HashMap<>();

    public static Image loadAsset(String path) {
        Image objectInMap = cachedAssets.get(path);

        // If the asset was already loaded, return it from the map
        if (objectInMap != null) {
            return objectInMap;
        }

        Image loadedAsset = TOOLKIT.getImage(AssetLoader.class.getResource(path));
        cachedAssets.put(path, loadedAsset);

        return loadedAsset;
    }
}
