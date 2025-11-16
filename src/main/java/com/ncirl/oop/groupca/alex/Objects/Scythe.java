/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex.Objects;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author DELL
 */
public class Scythe extends Tool{
    public Scythe(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        equipped=false;
    }
    @Override
    public void paintTool(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(posX, posY, 6, 45);
        g.setColor(Color.black);
        g.fillArc(posX-35, posY, 70, 10, 90, 90);
    }
}
 