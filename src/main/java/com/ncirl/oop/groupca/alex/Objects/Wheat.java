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
public class Wheat extends Plant{
    public Wheat(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        width = 35;
        height = 35;
        tool = "scythe";
    }
    
    @Override
    public void paintPlant(Graphics g) {
        g.setColor(new Color(250, 227, 22));
        g.fillRect(posX-(width/4),posY+(height/3),(width/2),height);
        g.setColor(new Color(214, 172, 19));
        g.fillRect(posX-(width/3 ),posY, 2*(width/3),height/3);
    }
}
