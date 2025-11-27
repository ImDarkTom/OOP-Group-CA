/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex;
import com.ncirl.oop.groupca.alex.AlexWindow.GameWindow;
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
    public void startTicks() { // Starts Timer
        ticker.start();
    }
    public void endTicks() {
        ticker.stop();
    }
    
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
    
}
