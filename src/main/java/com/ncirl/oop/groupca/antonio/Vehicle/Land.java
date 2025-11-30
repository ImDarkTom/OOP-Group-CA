/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.Vehicle;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author anton
 */
public class Land extends Vehicle {

    private boolean full;
    public Land(int x, int y, int w, int h, double xV, double yV, int c) {
        super(x, y, 55, 35, xV, yV, c);
    }

    @Override
    public void paintVehicle(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect((int)posX, (int)posY, width, height);
        g.setColor(Color.BLACK);
        g.drawRect((int)posX, (int)posY, width, height);
    }
}
