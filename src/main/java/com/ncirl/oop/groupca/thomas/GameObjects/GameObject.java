package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.util.Renderable;
import com.ncirl.oop.groupca.thomas.util.Vector2D;

public abstract class GameObject implements Renderable {
    Vector2D pos;
    int size;

    public GameObject(double startX, double startY, int size) {
        pos = new Vector2D(startX, startY);
        this.size = size;
    }
}
