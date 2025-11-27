package com.ncirl.oop.groupca.thomas.util;

import java.awt.*;

public interface Renderable {
    void setup();
    void render(Graphics2D g2, Point mousePos);
}
