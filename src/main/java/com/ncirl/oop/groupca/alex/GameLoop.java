/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex;
import com.ncirl.oop.groupca.alex.AlexWindow.GameWindow;
import com.ncirl.oop.groupca.alex.Objects.*;
import java.awt.Graphics;
import javax.swing.Timer;

/**
 *
 * @author DELL
 */
public class GameLoop {
    public GameLoop() {}
    private int ticks = 0;//Tracks ticks since starto of game.
    private boolean running = false;//Tracks if the game has started/ended
    private GameWindow panel; // Have to pass panel into here to trigger redraw
    private int points; // Track players point
    private Player player = new Player(); // Player
    
    public Timer ticker = new Timer(35, (e) -> { //Runs very 35ms, which comes out to running 28.5 times a second
        ticks++;
        if(ticks==286) {
            running=true;
        }
        panel.repaint();
        
    });
    public void resetGame() {
        // Reset
        points = 0;
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
        player.paintPlayer(g);
    }
    
    // Pass panel to GameLoop
    public void setPanel(GameWindow p) { // Accepts panel from AlexWindow
        panel = p;
    }
    
    public int getTicks() { // Return ticks for display
        return ticks;
    }
    
    public boolean getStatus() { // Return boolean whether game is running(for display)
        return running;
    }
    public int getPoints() {
        return points;
    }
    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        int speed = 10;
         // Seperate if statements so the player can move multiple directions at once
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
        System.out.println("up"+up);
        System.out.println("down"+down);
        System.out.println("left"+left);
        System.out.println("right"+right);
        
    }
}

