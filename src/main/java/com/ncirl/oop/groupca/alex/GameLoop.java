/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex;
import com.ncirl.oop.groupca.alex.AlexWindow.GameWindow;
import com.ncirl.oop.groupca.alex.Objects.*;
import com.ncirl.oop.groupca.alex.IntroOutro;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class GameLoop {
    private int ticks = 0; private int seconds;//Tracks ticks since starto of game.
    private boolean running = false;//Tracks if the game has started/ended
    private GameWindow panel; // Have to pass panel into here to trigger redraw
    private int points; // Track players point
    private Player player = new Player(); // Player
    private ArrayList<Plant> plants = new ArrayList<Plant>(); // Stores plants
    private ArrayList<Plant> heldPlants = new ArrayList<Plant>(); // Stores held plants
    private int plantID = 0; // Serial plant identifier
    private Scythe scythe = new Scythe(ran(800,950),ran(300,550)); // Init tool objects
    private Shovel shovel = new Shovel(ran(800,950),ran(300,550));
    
    public GameLoop() {
        for(int i=0;i<5;i++) {// Create plants
            plants.add(new Wheat(ran(0,800),ran(0,550), plantID, false));
            plantID++;
        }
        for(int i=0;i<5;i++) {// Create plants
            plants.add(new Onion(ran(0,800),ran(0,550), plantID, false));
            plantID++;
        }
        IntroOutro.playIntro();
        startTicks();
    }
   
    
    public Timer ticker = new Timer(40, (e) -> { //Runs every 40ms, which comes out to running 25 times a second
        if(ticks%25==0) {
            seconds++;
            if(seconds%5==0) { // Every 5 seconds add 2 new plants
                plants.add(new Onion(ran(0,800),ran(0,550), plantID, false));
                plantID++;
                plants.add(new Wheat(ran(0,800),ran(0,550), plantID, false));
                plantID++;
            }
        }
        if(seconds<=180) { // Calls functions that run the game, stops when timer runs out
            ticks++;
            running=true;
            collisionHandling();
            panel.repaint();
        } else { // Timer has ended, play outro
            endTicks();
            GameWindow.disposeWindow();
            IntroOutro.playOutro(points);
        }
    });
    // Tick Methods
    public void startTicks() { // Starts timer
        ticker.start();
    }
    public void endTicks() { // Ends timer
        ticker.stop();
    }
    
    // Render graphical elements to screen
    public void render(Graphics g) {
        // Sell Area, first so everything renders on top
        g.setColor(Color.orange);
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
    // Player Movement and also updates positions of objects held by player, and the bounding box for the sell area
    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        int speed = 8;
        // Seperate if statements so the player can move multiple directions at once
        if(up&&player.getY()>0) {player.changeY(-speed);} else {
            player.changeY(5);
        }
        if(down&&player.getY()<570) {player.changeY(speed);} else {
            player.changeY(-5);
        }
        if(left&&player.getX()>0) {player.changeX(-speed);} else {
            player.changeX(5);
        }
        if(right&&player.getX()<950) {player.changeX(speed);} else {
            player.changeX(-5);
        }
        // Held plants and tool positions must be updated so they are consistent
        int playerX = player.getX(); int playerY = (player.getY()+40)-(heldPlants.size())*20;
        for(Plant plant : heldPlants) {
            plant.setX(playerX-8);
            plant.setY(playerY-15);
            playerY=playerY+20;
        }
        playerY=player.getY(); // Equipped tool must be by the player
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
    // Checks player position against plants, and if close enough and holding matching tool, plant is picked up
    public void collisionHandling() {
        int num = -1;
        for(Plant plant : plants) {
            if(checkCollision(plant.getX(),plant.getY(),player, 50)&&heldPlants.size()<5) { // If player holds less than 5 plants and is close enough
                if(plant.getType()=="wheat"&&player.getTool()=="scythe") { // Tool check
                    num = plant.getArrayID();
                    heldPlants.add(new Wheat(plant.getX(),plant.getY(),plant.getArrayID(), true));
                } else if (plant.getType()=="onion"&&player.getTool()=="shovel") { // Tool check
                    num = plant.getArrayID();
                    heldPlants.add(new Onion(plant.getX(),plant.getY(),plant.getArrayID(), true));
                }
            }
        }
        if(num!=-1) { // Deletes plant from plant arraylist after it has been cloned to heldPlant arraylist
            final int numFin = num;
            plants.removeIf(plant -> plant.getArrayID()==numFin);
            System.out.println("removed Called");
            System.out.println("Num "+num);
        }
    }
    // Returns true or false if player is near co-ordinates provided
    public boolean checkCollision(int X1, int Y1, Player player, int range) {
        double distance;
        distance = Math.hypot(X1-player.getX(),Y1-player.getY()); // distance between points
        if(distance<= range) {
            return true;
        } else {
            return false;
        }
    }
    // Rewards player with points for held plants and clears arraylist
    public void sellPlants() {         
        points=points+(heldPlants.size()*2);
        heldPlants.clear();
    }
    
    // Pass panel from window to GameLoop
    public void setPanel(GameWindow p) {
        panel = p;
    }
    
    // Lots of random values are needed, seperate function for readability
    public int ran(int min, int max) {
        int random; return random = (int)(Math.random()*(max-min)+min);
    }
    // Get Statements
    public int getSeconds() { // Return ticks for display
        return seconds;
    }
    public boolean getStatus() { // Return boolean
        return running;
    }
    public int getPoints() { // Points...
        return points;
    }
}

