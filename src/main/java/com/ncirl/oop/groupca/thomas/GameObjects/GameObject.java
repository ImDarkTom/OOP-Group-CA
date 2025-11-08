package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.util.Vector2D;

import java.awt.*;

public class GameObject {
    Vector2D pos;
    int size;

    public GameObject(double startX, double startY, int size) {
        pos = new Vector2D(startX, startY);
        this.size = size;
    }

    public void render(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect((int)pos.x, (int)pos.y, size, size);
    }
}
