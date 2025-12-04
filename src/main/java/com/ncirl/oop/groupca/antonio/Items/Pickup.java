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
public class Pickup extends Items {

    private final int[] amount;

    public Pickup(int radius, int x, int y, int amount) {
        super(radius, x, y);
        this.amount = new int[]{amount};
    }

    @Override
    public void paintItem(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(posX[0], posY[0], itemRadius, itemRadius);
        g.setColor(Color.black);
        g.drawOval(posX[0], posY[0], itemRadius, itemRadius);
    }

    public int[] getAmount() {
        return amount;
    }

}
