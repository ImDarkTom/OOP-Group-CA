/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex;
import com.ncirl.oop.groupca.alex.AlexWindow.GameWindow;
import com.ncirl.oop.groupca.alex.Objects.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author DELL
 */
public class GameLoop {
    private int ticks = 0;//Tracks ticks since starto of game.
    private boolean running = false;//Tracks if the game has started/ended
    private GameWindow panel; // Have to pass panel into here to trigger redraw
    private int points; // Track players point
    private Player player = new Player(); // Player
    private ArrayList<Plant> plants = new ArrayList<Plant>(); // Stores plants
    private ArrayList<Plant> heldPlants = new ArrayList<Plant>();
    private int plantID = 0;
    private Scythe scythe = new Scythe(ran(800,950),ran(300,550));
    private Shovel shovel = new Shovel(ran(800,950),ran(300,550));
    
    public GameLoop() {
        for(int i=0;i<5;i++) {
            plants.add(new Wheat(ran(0,800),ran(0,550), plantID, false));
            plantID++;
        }
        for(int i=0;i<5;i++) {
            plants.add(new Onion(ran(0,800),ran(0,550), plantID, false));
            plantID++;
        }
        
        startTicks();
    }
   
    
    public Timer ticker = new Timer(35, (e) -> { //Runs every 35ms, which comes out to running 28.5 times a second
        ticks++;
        if(ticks>=20) {
            running=true;
            collisionHandling();
        }
        panel.repaint();
        
    });
    public void resetGame() {
        // Reset
        points = 0;
        plants.clear();
        heldPlants.clear();
    }
    // Tick Methods
    public void startTicks() { // Starts Timer
        ticker.start();
    }
    public void endTicks() {
        ticker.stop();
    }
    
    // Render graphical elements to screen
    public void render(Graphics g) {
        // Sell Area, first so everything renders on top
        g.setColor(Color.yellow);
        g.fillRect(800, 0, 200, 300);
        
        for(Plant plant : heldPlants) {
            plant.paintPlant(g);
        }
        player.paintPlayer(g);
        for(Plant plant : plants) {
            plant.paintPlant(g);
        }
        scythe.paintTool(g);
        shovel.paintTool(g);
        
        
    }
    
    // Pass panel to GameLoop
    public void setPanel(GameWindow p) { // Accepts panel from AlexWindow
        panel = p;
    }
    
    // Player Movement
    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        int speed = 10;
        // Seperate if statements so the player can move multiple directions at once
        // This function also updates positions of objects held by player, and the bounding box for the sell area
        if(up&&player.getY()>0) {player.changeY(-speed);} else {
            player.changeY(5);
        }
        if(down&&player.getY()<600) {player.changeY(speed);} else {
            player.changeY(-5);
        }
        if(left&&player.getX()>0) {player.changeX(-speed);} else {
            player.changeX(5);
        }
        if(right&&player.getX()<1000) {player.changeX(speed);} else {
            player.changeX(-5);
        }
        // Held plants and tool positions must be updated so they are consistent
        int playerX = player.getX(); int playerY = player.getY();
        for(Plant plant : heldPlants) {
            plant.setX(playerX-8);
            plant.setY(playerY-5);
            playerY=playerY-20;
        }
        playerY=player.getY();
        if(player.getTool()=="scythe") {
            scythe.setX(playerX+40);
            scythe.setY(playerY);
        } else if(player.getTool()=="shovel") {
            shovel.setX(playerX+40);
            shovel.setY(playerY);
        }
        // Detects whether the player is in the sell zone.
        if((playerX>790&&playerY<310)&&(!heldPlants.isEmpty())){
            sellPlants();
        }
        
    }
    public void toolInteraction() {
        if(player.getTool()=="none") {
            if(checkCollision(scythe.getX(),scythe.getY(),player, 100)) {
                player.setTool("scythe");
            } else if(checkCollision(shovel.getX(),shovel.getY(),player, 100)) {
                player.setTool("shovel");
            }
        } else {
            player.setTool("none");
        }
    }
    
    // Collision
    public void collisionHandling() {
        int num = -1;
        for(Plant plant : plants) {
            if(checkCollision(plant.getX(),plant.getY(),player, 50)&&heldPlants.size()<5) {
                if(plant.getType()=="wheat"&&player.getTool()=="scythe") {
                    num = plant.getArrayID();
                    heldPlants.add(new Wheat(plant.getX(),plant.getY(),plant.getArrayID(), true));
                } else if (plant.getType()=="onion"&&player.getTool()=="shovel") {
                    num = plant.getArrayID();
                    heldPlants.add(new Onion(plant.getX(),plant.getY(),plant.getArrayID(), true));
                }
            }
        }
        if(num!=-1) {
            final int numFin = num;
            plants.removeIf(plant -> plant.getArrayID()==numFin);
            System.out.println("removed Called");
            System.out.println("Num "+num);
        }
    }
    
    public boolean checkCollision(int X1, int Y1, Player player, int range) {
        double distance;
        distance = Math.hypot(X1-player.getX(),Y1-player.getY());
        if(distance<= range) {
            return true;
        } else {
            return false;
        }
    }
    
    public void sellPlants() {
        points=points+(heldPlants.size()*2);
        heldPlants.clear();
    }
    
    // Lots of random values are needed, seperate function for readability
    public int ran(int min, int max) {
        int random; return random = (int)(Math.random()*(max-min)+min);
    }
    // Get Statements
    public int getTicks() { // Return ticks for display
        return ticks;
    }
    public boolean getStatus() { // Return boolean whether game is running(for display)
        return running;
    }
    public int getPoints() {
        return points;
    }
}

