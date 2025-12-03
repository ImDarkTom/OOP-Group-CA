/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex.Objects;
import java.awt.*;
import com.ncirl.oop.groupca.alex.util.*;
/**
 * Player.java
 * @author Alex
 */
public class Player {
    private int posX;private int posY;
    final private int width;final private int height;
    private String toolHeld;
    Customisation custom = FileLoader.loadFromFile("Customisation.esr", Customisation.class);

    public Player() {
        posX = 900;
        posY = 100;
        width = 50;
        height = 50;
        toolHeld="none";
    }
    
    // Setters
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
    public void changePos(int X, int Y) {
        posX = posX+X;
        posY = posY+Y;
    }
    
    // Getters
    public int getX() {
        return posX;
    }
    public int getY() {
        return posY;
    }
    public String getTool() {
        return toolHeld;
    }
     
    public void paintPlayer(Graphics g) { // Render / draw function
        g.setColor(custom.getAlexBody());
        g.fillRect(posX, posY+(height/4), width, height/2);
        g.setColor(custom.getAlexHead());
        g.fillRect(posX+(width/4), posY-(height/4), width/2, height/2);
        g.setColor(Color.black);
    }
}
