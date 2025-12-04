/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.Vehicle;

import java.awt.*;
import com.ncirl.oop.groupca.thomas.shared.CustomisationManager;
import com.ncirl.oop.groupca.thomas.ImageLoader;
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

    public Vehicle() {
        posX = 0;
        posY = 0;
        width = 0;
        height = 0;
        xVel = 0;
        yVel = 0;
        Capacity = 0;
        if (CustomisationManager.getHat() != 0){
            ASSET = ImageLoader.loadWithSize("/hats/" + CustomisationManager.getHat() + ".png", 40, 40);
    }
        vehicleColor = CustomisationManager.getBodyCol();

    }

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

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public Image getASSET() {
        return ASSET;
    }

    public void setASSET(Image ASSET) {
        this.ASSET = ASSET;
    }

    public Color getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(Color vehicleColor) {
        this.vehicleColor = vehicleColor;
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
