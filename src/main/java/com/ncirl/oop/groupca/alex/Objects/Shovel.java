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
public class Shovel extends Tool{
    public Shovel(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        equipped=false;
    }
    @Override
    public void paintTool(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(posX+7, posY+40, 6, 35);
        g.setColor(Color.black);
        g.fillRect(posX, posY+15, 20, 25);
    }
}
