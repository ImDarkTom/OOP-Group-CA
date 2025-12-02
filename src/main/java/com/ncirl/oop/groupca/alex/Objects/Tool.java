/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex.Objects;

import java.awt.Graphics;

/**
 * Tool.java
 * @author Alex
 */
abstract public class Tool {
    protected int posX;protected int posY;
    protected boolean equipped;
    
    public Tool() {} 
    //  Setters
    public void setX(int x) {
        posX = x;
    }
    public void setY(int y) {
        posY = y;
    }
    // Getters
    public int getX() {
        return posX;
    }
    public int getY() {
        return posY;
    }
    public void paintTool(Graphics g) { // Draw tool to screen
    }
}


