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
public class Air extends Vehicle {

    public Air(int x, int y, int w, int h, double xV, double yV, int c) {
        super(x, y, 60, 40, xV, yV, c);
    }

    @Override
    public void paintVehicle(Graphics g) {
        g.setColor(Color.GREEN);
        int centerX = (int) posX;
        int centerY = (int) posY;

        int[] xFill = {centerX, centerX + (width/2), centerX - (width/2)};
        int[] yFill = {centerY + (height/2), centerY - (height/2), centerY - (height/2)};
        g.fillPolygon(xFill, yFill, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(xFill, yFill, 3);
        g.setColor(Color.ORANGE);
        g.drawRect((int)posX-(width/2), (int)posY-(height/2), width, height);
    }
}