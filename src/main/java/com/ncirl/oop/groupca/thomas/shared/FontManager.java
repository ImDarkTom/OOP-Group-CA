package com.ncirl.oop.groupca.thomas.shared;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Thomas
 */
public class FontManager {
    private static final Map<Integer, Font> cachedFonts = new HashMap<>();

    public static Font getFont(int size) {
        return getFont(size, Font.PLAIN);
    }

    public static Font getFont(int size, int style) {
        Font objectInMap = cachedFonts.get(size);

        // If the asset was already loaded, return it from the map
        if (objectInMap != null) {
            return objectInMap;
        }

        Font newFont = new Font("Arial", style, 14);

        cachedFonts.put(size, newFont);

        return newFont;
    }
}
