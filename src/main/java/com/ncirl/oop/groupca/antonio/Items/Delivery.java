/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.Items;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author anton
 */
public class Delivery extends Items {

    public Delivery(int radius, int[] posX, int[] posY, int time, int score) {
        super(radius, posX, posY, time, score);
    }

    @Override
    public void paintItem(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(posX[0], posY[0], radius, radius);
        g.setColor(Color.white);
        g.drawOval(posX[0], posY[0], radius, radius);
    }

}
