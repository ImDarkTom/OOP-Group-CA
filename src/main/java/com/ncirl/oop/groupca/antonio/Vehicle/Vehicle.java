/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.Vehicle;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author anton
 */
public class Vehicle {

    protected int posX;
    protected int posY;
    protected int width;
    protected int height;
    protected float xVel;
    protected float yVel;
    protected int Capacity;
//    protected String Vehicle;

    public Vehicle() {
        posX = 0;
        posY = 0;
        width = 0;
        height = 0;
        xVel = 0;
        yVel = 0;
        Capacity = 0;
//        Vehicle = "/TBD";
    }

    public Vehicle(int x, int y, int w, int h, float xV, float yV, int c) {
        posX = x;
        posY = y;
        width = w;
        height = h;
        xVel = xV;
        yVel = yV;
        Capacity = c;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getxVel() {
        return xVel;
    }

    public void setxVel(float xVel) {
        this.xVel = xVel;
    }

    public float getyVel() {
        return yVel;
    }

    public void setyVel(float yVel) {
        this.yVel = yVel;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public void paintVehicle(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(posX, posY, width, height);
        g.setColor(Color.black);
        g.drawRect(posX, posY, width, height);
    }

}
