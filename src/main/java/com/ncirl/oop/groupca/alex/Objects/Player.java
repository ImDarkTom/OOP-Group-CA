/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex.Objects;
import java.awt.Graphics;
import java.awt.Color;
/**
 *
 * @author DELL
 */
public class Player {
    private int posX;private int posY;
    private int width;private int height;
    public Player() {
        posX = 0;
        posY = 0;
        width = 50;
        height = 50;
    }
    
    public void setPosX(int X) {
      posX = X;  
    }
    public void setPosY(int Y) {
      posY = Y;  
    }
    
    public int getX() {
        return posX;
    }
    public int getY() {
        return posY;
    }
    
    public void paintPlayer(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(posX, posY, width, height);
        g.setColor(Color.black);
        g.drawRect(posX, posY, width, height);
        
    }
}
