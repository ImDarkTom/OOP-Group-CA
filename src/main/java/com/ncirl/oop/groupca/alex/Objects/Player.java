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
public class Player {
    private int posX;private int posY;
    final private int width;final private int height;
    private String toolHeld;
    
    public Player() {
        posX = 100;
        posY = 100;
        width = 50;
        height = 50;
        toolHeld="none";
    }
    
    public void setPosX(int X) {
      posX = X;  
    }
    public void setPosY(int Y) {
      posY = Y;  
    }
    public void setTool(String tool) {
        toolHeld = tool;
    }
    
    // Reset tool
    public void clearTool() {
            toolHeld = "none";
    }
    
    // Methods to change relative to present value are here to reduce calling positions back and forth
    public void changeX(int X) {
        posX = posX+X;
    }
    public void changeY(int Y) {
        posY = posY+Y;
    }
    
    public int getX() {
        return posX;
    }
    public int getY() {
        return posY;
    }
    public String getTool() {
        return toolHeld;
    }
     
    public void paintPlayer(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(posX, posY+(height/4), width, height/2);
        g.setColor(Color.yellow);
        g.fillRect(posX+(width/4), posY-(height/4), width/2, height/2);
        
        
        g.setColor(Color.black);
    }
}
