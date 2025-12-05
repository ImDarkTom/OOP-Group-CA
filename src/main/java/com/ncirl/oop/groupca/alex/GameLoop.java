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

/**
 * GameLoop.java
 * @author Alex
 */
public class GameLoop {
    private int ticks = 0; private int seconds;//Tracks ticks since start of game.
    private GameWindow panel; // Have to pass panel into here to trigger redraw
    private int points; // Track players point
    private Player player;
    private ArrayList<Plant> plants;// Stores plants
    private ArrayList<Plant> heldPlants;// Stores held plants
    private int plantID = 0; // Serial plant identifier
    private Scythe scythe;// Init tool objects
    private Shovel shovel;
    
    public GameLoop(GameWindow p) {
        initGame();
        panel=p;
    }
    
    public Timer ticker = new Timer(40, (e) -> { //Runs every 40ms, which comes out to running 25 times a second
        if(ticks%25==0) {
            seconds++;
        }
        if(seconds<=120) { // Calls functions that run the game, stops when timer runs out
            if(plants.size()<=12) { // Every 5 seconds add 2 new plants
                if((ran(1,6)%2)==0) {
                    plants.add(new Onion(ran(0,800),ran(0,550), plantID, false));
                } else {
                    plants.add(new Wheat(ran(0,800),ran(0,550), plantID, false));
                }
                plantID++;
            }
            ticks++;
            collisionHandling();
            panel.repaint();
        } else { // Timer has ended, play outro
            endTicks();
            GameWindow.disposeWindow();
            IntroOutro.playOutro(points);
        }
    });
    private void initGame() { // Start game by 
        resetGame();
        IntroOutro.playIntro();
        startTicks();
    }
    public void resetGame() {// Creates new values for game components
        endTicks();// Timer is ended here so it can never stop without resetting game
        points=0;
        seconds=0;
        ticks=0;
        player = new Player(); // Player
        plants = new ArrayList<Plant>(); // Stores plants
        heldPlants = new ArrayList<Plant>(); // Stores held plants
        plantID = 0; // Serial plant identifier
        scythe = new Scythe(ran(800,950),ran(300,550)); // Init tool objects
        shovel = new Shovel(ran(800,950),ran(300,550));
        for(int i=0;i<5;i++) {// Create plants
            plants.add(new Wheat(ran(0,800),ran(0,550), plantID, false));
            plantID++;
        }
        for(int i=0;i<5;i++) {// Create plants
            plants.add(new Onion(ran(0,800),ran(0,550), plantID, false));
            plantID++;
        }
    }
    // Tick Methods
    public void startTicks() { // Starts timer
        ticker.start();
    }
    public void endTicks() {
        ticker.stop();
    }

    // Render graphical elements to screen
    public void render(Graphics g) {
        // Sell Area, first so everything renders on top
        g.setColor(Color.orange);
        g.fillRect(800, 0, 200, 300);
        
        for(Plant plant : heldPlants) { // Paints held plants
            plant.paintPlant(g);
        }
        player.paintPlayer(g); // Paints player
        for(Plant plant : plants) { // Paints plants
            plant.paintPlant(g);
        }
        scythe.paintTool(g); // Tool painting
        shovel.paintTool(g);
    }
    // Player Movement and also updates positions of objects held by player, and the bounding box for the sell area
    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        int speed = 8;
        // Seperate if statements so the player can move multiple directions at once
        if(up&&player.getY()>0) {player.changePos(0,-speed);} else {
            player.changePos(0,5);
        }
        if(down&&player.getY()<570) {player.changePos(0,speed);} else {
            player.changePos(0,-5);
        }
        if(left&&player.getX()>0) {player.changePos(-speed,0);} else {
            player.changePos(5,0);
        }
        if(right&&player.getX()<950) {player.changePos(speed,0);} else {
            player.changePos(-5,0);
        }
        // Held plants and tool positions must be updated so they are consistent
        int playerX = player.getX(); int playerY = (player.getY()+40)-(heldPlants.size())*20;
        for(Plant plant : heldPlants) {
            plant.setX(playerX-8);
            plant.setY(playerY-15);
            playerY=playerY+20;
        }
        playerY=player.getY(); // Equipped tool must be by the player
        if(player.getTool().equals("scythe")) {
            scythe.setX(playerX+40);
            scythe.setY(playerY);
        } else if(player.getTool().equals("shovel")) {
            shovel.setX(playerX+40);
            shovel.setY(playerY);
        }
        // Detects whether the player is in the sell zone.
        if((playerX>790&&playerY<310)&&(!heldPlants.isEmpty())){
            sellPlants();
        }
    }
    public void toolInteraction() {
        if(player.getTool().equals("none")) {
            double scytheDist = checkCollision(scythe.getX(),scythe.getY(),player);
            double shovelDist = checkCollision(shovel.getX(),shovel.getY(),player);
            if(scytheDist<=100&&scytheDist<shovelDist) {
                player.setTool("scythe");
            } else if(shovelDist<=100&&shovelDist<scytheDist) {
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
            if((checkCollision(plant.getX(),plant.getY(),player)<=50)&&heldPlants.size()<6) { // If player holds less than 5 plants and is close enough
                if(plant.getType().equals("wheat")&&player.getTool().equals("scythe")) { // Tool check
                    num = plant.getArrayID();
                    heldPlants.add(new Wheat(plant.getX(),plant.getY(),plant.getArrayID(), true));
                } else if (plant.getType().equals("onion")&&player.getTool().equals("shovel")) { // Tool check
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
    public double checkCollision(int X1, int Y1, Player player) {
        double distance;
        distance = Math.hypot(X1-player.getX(),Y1-player.getY()); // distance between points
        return distance;
    }
    // Rewards player with points for held plants and clears arraylist
    public void sellPlants() {         
        points=points+(heldPlants.size()*3);
        heldPlants.clear();
    }
    // Lots of random values are needed, seperate function for readability
    public int ran(int min, int max) {
        int random; return random = (int)(Math.random()*(max-min)+min);
    }
    // Get Statements
    public int getSeconds() { // Return ticks for display
        return seconds;
    }
    public int getPoints() { // Points...
        return points;
    }
}

