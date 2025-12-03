package com.ncirl.oop.groupca.thomas;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles loading assets from the <code>resources</code> folder.
 */
public class ImageLoader {
    private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
    private static final Map<String, Image> cachedAssets = new HashMap<>();

    public static Image load(String path) {
        Image objectInMap = cachedAssets.get(path);

        // If the asset was already loaded, return it from the map
        if (objectInMap != null) {
            return objectInMap;
        }

        Image loadedAsset = TOOLKIT.getImage(ImageLoader.class.getResource(path));
        cachedAssets.put(path, loadedAsset);

        return loadedAsset;
    }

    public static Image loadWithSize(String path, int width, int height) {
        Image originalImage = load(path);

        return originalImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }

    public static Image loadWithSize(String path, int width, int height, int scaleHint) {
        Image originalImage = load(path);

        return originalImage.getScaledInstance(width, height, scaleHint);
    }

    public static ImageIcon loadAsIcon(String path) {
        return new ImageIcon(ImageLoader.load(path));
    }
}
