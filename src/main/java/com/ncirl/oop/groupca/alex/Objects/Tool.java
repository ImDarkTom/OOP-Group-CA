/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex.Objects;

import java.awt.Graphics;

/**
 *
 * @author DELL
 */
abstract public class Tool {
    protected int posX;protected int posY;
    protected boolean equipped;
    
    public Tool() {
        posX=0;
        posY=0;
        equipped=false;
    } 
    
    public void setX(int x) {
        posX = x;
    }
    public void setY(int y) {
        posY = y;
    }
    
    public int getX() {
        return posX;
    }
    public int getY() {
        return posY;
    }
    
    public boolean toggleEquip() {
        if(equipped==true) {
            equipped=false;
        } else {
            equipped=true;
        }
        return equipped;
    }
    
    public void paintTool(Graphics g) {
    }
}


