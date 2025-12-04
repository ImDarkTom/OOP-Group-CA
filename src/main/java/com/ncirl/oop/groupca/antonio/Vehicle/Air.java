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
public class Air extends Vehicle {

    public Air(int x, int y, int w, int h, double xV, double yV, int c, Image hat, Color color) {
        super(x, y, 60, 40, xV, yV, c, hat, color);
    }

    @Override
    public void paintVehicle(Graphics g) {
        int centerX = (int) posX;
        int centerY = (int) posY;

        g.setColor(vehicleColor);
        int[] xFill = {centerX, centerX + (width/2), centerX - (width/2)};
        int[] yFill = {centerY + (height/2), centerY - (height/2), centerY - (height/2)};
        g.fillPolygon(xFill, yFill, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(xFill, yFill, 3);
        g.setColor(Color.ORANGE);
        g.drawRect((int)posX-(width/2), (int)posY-(height/2), width, height);
        g.setColor(Color.black);
        if (CustomisationManager.getHat() != 0) {
            Point hatPos = new Point((int) posX - 20, (int) posY - 50);
            RenderUtils.drawImage((Graphics2D) g, ASSET, hatPos);
        }
    }
}