/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex.Objects;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Scythe.java
 * @author Alex
 */
public class Scythe extends Tool{
    public Scythe(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        equipped=false;
    }
    @Override
    public void paintTool(Graphics g) { // Draw Scythe to screen
        g.setColor(Color.gray);
        g.fillRect(posX+15, posY, 8, 45);
        g.setColor(Color.black);
        g.fillArc(posX-20, posY, 70, 10, 90, 90);
    }
}
 