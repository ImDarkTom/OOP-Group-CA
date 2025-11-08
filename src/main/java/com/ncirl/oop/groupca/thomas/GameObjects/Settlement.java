package com.ncirl.oop.groupca.thomas.GameObjects;

import java.awt.*;

public class Settlement extends GameObject {
    float hunger = 0;
    public Settlement(double startX, double startY) {
        super(startX, startY, 50);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect((int)pos.x, (int)pos.y, size, size);
    }
}
