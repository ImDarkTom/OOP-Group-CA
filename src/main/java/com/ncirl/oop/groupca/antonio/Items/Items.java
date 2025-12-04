/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.Items;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author anton
 */
public class Items {

    protected int itemRadius;
    protected int[] posX;
    protected int[] posY;

    public Items(int radius, int x, int y) {
        itemRadius = radius;
        posX = new int[] {x};
        posY = new int[] {y};
    }

    public int getRadius() {
        return itemRadius;
    }

    public int[] getPosX() {
        return posX;
    }

    public void setPosX(int[] posX) {
        this.posX = posX;
    }

    public int[] getPosY() {
        return posY;
    }

    public void setPosY(int[] posY) {
        this.posY = posY;
    }

    public void paintItem(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(posX[0], posY[0], itemRadius, itemRadius);
        g.setColor(Color.black);
        g.drawOval(posX[0], posY[0], itemRadius, itemRadius);
    }

}
