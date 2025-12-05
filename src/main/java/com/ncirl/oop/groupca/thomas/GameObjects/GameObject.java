package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.util.ClickHandler;
import com.ncirl.oop.groupca.thomas.util.Renderable;
import com.ncirl.oop.groupca.thomas.util.Tickable;

import java.awt.*;

/**
 * @author Thomas
 */
public abstract class GameObject implements Renderable, Tickable, ClickHandler {
    Point pos;
    int size;

    public GameObject(int startX, int startY, int size) {
        pos = new Point(startX, startY);
        this.size = size;
    }

    public Point getPos() {
        return pos;
    }

    public int getSize() {
        return size;
    }
}
