/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex;
import com.ncirl.oop.groupca.alex.AlexWindow.GameWindow;
import com.ncirl.oop.groupca.alex.Objects.*;
import java.awt.Graphics;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author DELL
 */
public class GameLoop {
    public GameLoop() {
        for(int i=0;i<10;i++) {
            plants.add(new Wheat(ran(600),ran(600), plantID, false));
            plantID++;
        }
        startTicks();
    }
    private int ticks = 0;//Tracks ticks since starto of game.
    private boolean running = false;//Tracks if the game has started/ended
    private GameWindow panel; // Have to pass panel into here to trigger redraw
    private int points; // Track players point
    private Player player = new Player(); // Player
    private ArrayList<Plant> plants = new ArrayList<Plant>(); // Stores plants
    private ArrayList<Plant> heldPlants = new ArrayList<Plant>();
    private int plantID = 0;
    
    public Timer ticker = new Timer(35, (e) -> { //Runs every 35ms, which comes out to running 28.5 times a second
        ticks++;
        if(ticks>=20) {
            running=true;
            collisionUpdate();
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
        for(Plant plant : heldPlants) {
            plant.paintPlant(g);
        }
        player.paintPlayer(g);
        for(Plant plant : plants) {
            plant.paintPlant(g);
        }
    }
    
    // Pass panel to GameLoop
    public void setPanel(GameWindow p) { // Accepts panel from AlexWindow
        panel = p;
    }
    
    // Player Movement
    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        int speed = 10;
        // Seperate if statements so the player can move multiple directions at once
        // If statements check whether player is in bounds and if the button is held down,
        // If both are true the player moves, otherwise their position is corrected.
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
        // Held plants and tool positions must be updated too
        int playerX = player.getX(); int playerY = player.getY();
        for(Plant plant : heldPlants) {
            plant.setX(playerX-8);
            plant.setY(playerY-5);
            playerY=playerY-20;
        }
        
    }
    
    // Collision
    public void collisionUpdate() {
        int num = -1;
        for(Plant plant : plants) {
            int tempNum = checkCollision(plant, player);
            if(tempNum!=-1&&heldPlants.size()<=5) {
                num = tempNum;
                heldPlants.add(new Wheat(plant.getX(),plant.getY(),plant.getArrayID(), true));
            }
        }
        if(num!=-1) {
            final int numFin = num;
            plants.removeIf(plant -> plant.getArrayID()==numFin);
            System.out.println("removed Called");
            System.out.println("Num "+num);
        }
    }
    
    public int checkCollision(Plant plant, Player player) {
        double distance;
        distance = Math.hypot(plant.getX()-player.getX(),plant.getY()-player.getY());
        if(distance<= 50) {
            return plant.getArrayID();
        } else {
            return -1;
        }
    }
    
    // Lots of random values are needed, seperate function for readability
    public int ran(int max) {
        int random; return random = (int)(Math.floor(Math.random()*max)+1);
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

