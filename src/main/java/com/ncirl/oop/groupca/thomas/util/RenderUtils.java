package com.ncirl.oop.groupca.thomas.util;

import java.awt.*;

public class RenderUtils {
    public static void drawImage(Graphics2D g2, Image asset, Point pos) {

        g2.drawImage(asset, pos.x, pos.y, null);
    }

    public static void drawImage(Graphics2D g2, Image asset, Point pos, float opacity) {
        // Source: https://stackoverflow.com/a/11552156/17727765
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

        g2.drawImage(asset, pos.x, pos.y, null);
    }
}
