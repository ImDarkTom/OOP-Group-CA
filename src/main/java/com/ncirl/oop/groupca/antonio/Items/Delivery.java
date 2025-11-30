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
public class Delivery extends Items {

    private int[] requested;
    public Delivery(int radius, int x, int y, int requested) {
        super(radius, x, y);
        this.requested = new int[]{requested};
    }

    @Override
    public void paintItem(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(posX[0], posY[0], radius, radius);
        g.setColor(Color.white);
        g.drawOval(posX[0], posY[0], radius, radius);
    }

    public int[] getRequested() {
        return requested;
    }

    public void setRequested(int[] requested) {
        this.requested = requested;
    }
}
