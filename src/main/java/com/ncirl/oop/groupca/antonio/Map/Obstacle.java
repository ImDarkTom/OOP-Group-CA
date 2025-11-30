package com.ncirl.oop.groupca.antonio.Map;

import java.awt.Color;
import java.awt.Graphics;

public class Obstacle {
    private int x;
    private int y;
    private int width;
    private int height;


    public Obstacle() {
        this.x = 100;
        this.y = 100;
        this.width = 50;
        this.height = 50;
    }

    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public void paintObstacle(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
