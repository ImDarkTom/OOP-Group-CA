/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex.Objects;
import java.awt.*;
/**
 *
 * @author DELL
 */
abstract public class Plant {
    protected int posX;protected int posY;
    protected int width;protected int height;
    protected String tool;protected String type;
    public int arrayID;
    
    public Plant() {
        posX = 100;
        posY = 100;
        width = 50;
        height = 50;
        arrayID = 0;
    }
    
    public void setX(int X) {
      posX = X;  
    }
    public void setY(int Y) {
      posY = Y;
    }
    
    public int getX() {
        return posX;
    }
    public int getY() {
        return posY;
    }
    public int getArrayID() {
        return arrayID;
    }
    public String getType() {
        return type;
    }
    // Paint function
    public void paintPlant(Graphics g) {
    }
}
