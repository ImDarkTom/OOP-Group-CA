/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex.Objects;
import java.awt.*;
import com.ncirl.oop.groupca.thomas.shared.CustomisationManager;
import com.ncirl.oop.groupca.thomas.ImageLoader;
import com.ncirl.oop.groupca.thomas.util.RenderUtils;

/**
 * Player.java
 * @author Alex
 */
public class Player {
    Image ASSET;

    private int posX;private int posY;
    final private int width;final private int height;
    private String toolHeld;
    

    public Player() {
        posX = 900;
        posY = 100;
        width = 50;
        height = 50;
        toolHeld="none";
        if(CustomisationManager.getHat()!=0) {
            ASSET = ImageLoader.loadWithSize("/hats/"+CustomisationManager.getHat()+".png",40,40);
        }
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
        g.setColor(CustomisationManager.getAlexBody());
        g.fillRect(posX, posY+(height/4), width, height/2);
        g.setColor(Color.yellow);
        g.fillRect(posX+(width/4), posY-(height/4), width/2, height/2);
        g.setColor(Color.black);
        Point hatPos = new Point(posX+5,posY-40);
        RenderUtils.drawImage((Graphics2D)g, ASSET, hatPos);
    }
}
