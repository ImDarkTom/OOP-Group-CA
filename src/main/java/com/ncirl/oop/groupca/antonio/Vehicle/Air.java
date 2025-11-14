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

    public Air(int x, int y, int w, int h, float xV, float yV, int c) {
        super(x, y, 60, 40, xV, yV, c);
    }

    @Override
    public void paintVehicle(Graphics g) {
        g.setColor(Color.GREEN);
        int[] xFill = {200, 225, 250};
        int[] yFill = {350, 300, 350};
        g.fillPolygon(xFill, yFill, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(xFill, yFill, 3);
    }
}
