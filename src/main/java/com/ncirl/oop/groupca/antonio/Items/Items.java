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
public class Items {

    protected int radius;
    protected int[] posX;
    protected int[] posY;
    protected int time;
    protected int score;
//    protected String style;

    public Items() {
        radius = 0;
        int[] posX = {0};
        int[] posY = {0};
        time = 0;
        score = 0;
//                        style = "/TBD";
    }

    public Items(int radius, int[] posX, int[] posY, int time, int score) {
        this.radius = radius;
        this.posX = posX;
        this.posY = posY;
        this.time = time;
        this.score = score;
    

    ////        this.style = style;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int[] getPosX() {
        return posX;
    }

    public void setPosX(int[] posX) {
        this.posX = posX;
    }

    public int[] getPosY() {
        return posY;
    }

    public void setPosY(int[] posY) {
        this.posY = posY;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

//    public String getStyle() {
//        return style;
//    }
//
//    public void setStyle(String style) {
//        this.style = style;
//    }
    public void paintItem(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(posX[0], posY[0], radius, radius);
        g.setColor(Color.black);
        g.drawOval(posX[0], posY[0], radius, radius);
    }

}
