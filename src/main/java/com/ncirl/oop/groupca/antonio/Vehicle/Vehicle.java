/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.Vehicle;

import java.awt.*;
import com.ncirl.oop.groupca.thomas.shared.CustomisationManager;
import com.ncirl.oop.groupca.thomas.util.RenderUtils;

/**
 *
 * @author anton
 */
public class Vehicle {



    protected Image ASSET;
    protected Color vehicleColor;
    protected double posX;
    protected double posY;
    protected int width;
    protected int height;
    protected double xVel;
    protected double yVel;
    protected int Capacity;

    public Vehicle(double x, double y, int w, int h, double xV, double yV, int c, Image hat, Color color) {
        posX = x;
        posY = y;
        width = w;
        height = h;
        xVel = xV;
        yVel = yV;
        Capacity = c;
        if(CustomisationManager.getHat()!=0) {
            ASSET = hat;
        }
        vehicleColor = color;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getxVel() {
        return xVel;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void paintVehicle(Graphics g) {
        g.setColor(vehicleColor);
        g.fillRect((int)posX, (int)posY, width, height);
        g.setColor(Color.black);
        g.drawRect((int)posX, (int)posY, width, height);
        g.setColor(Color.black);
        if(CustomisationManager.getHat()!=0) {
           Point hatPos = new Point((int)posX+5,(int)posY-40);
            RenderUtils.drawImage((Graphics2D) g, ASSET, hatPos);
        }
    }

}
