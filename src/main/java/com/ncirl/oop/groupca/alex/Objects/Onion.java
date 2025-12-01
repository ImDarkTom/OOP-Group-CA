/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex.Objects;

import java.awt.Graphics;
import java.awt.Color;
/**
 *
 * @author DELL
 */
public class Onion extends Plant{
    public Onion(int posX, int posY, int arrayNum, boolean isHeld) {
        type="onion";
        this.posX = posX;
        this.posY = posY;
        width = 25;
        height = 25;
        tool = "shovel";
        arrayID = arrayNum;
    }
    
    @Override
    public void paintPlant(Graphics g) { // Draw onion to screen
        int[] xpoints = {posX,posX-14,posX+14};
        int[] ypoints = {posY+20,posY-30,posY-30};
        g.setColor(Color.green);
        g.fillPolygon(xpoints,ypoints,3);
        g.setColor(new Color(71, 28, 92));
        g.fillOval(posX-(width/2), posY, width, height);
    }
}
