/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.Vehicle;

import com.ncirl.oop.groupca.thomas.shared.CustomisationManager;
import com.ncirl.oop.groupca.thomas.util.RenderUtils;

import java.awt.*;

/**
 *
 * @author anton
 */
public class Land extends Vehicle {

    private boolean full;
    public Land(int x, int y, int w, int h, double xV, double yV, int c, Image hat, Color color) {
        super(x, y, 55, 35, xV, yV, 35, hat, color);
    }

    @Override
    public void paintVehicle(Graphics g) {
        g.setColor(vehicleColor);
        g.fillRect((int)posX, (int)posY, width, height);
        g.setColor(Color.BLACK);
        g.drawRect((int)posX, (int)posY, width, height);
        g.setColor(Color.black);
        if (CustomisationManager.getHat() != 0) {
            Point hatPos = new Point((int) posX + 10, (int) posY - 30);
            RenderUtils.drawImage((Graphics2D) g, ASSET, hatPos);
        }
    }

    public int getCapacity() {
        return 35;
    }
}
