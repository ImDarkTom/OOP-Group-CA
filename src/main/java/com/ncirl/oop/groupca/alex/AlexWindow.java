/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;

import com.ncirl.oop.groupca.thomas.shared.FontManager;
import com.ncirl.oop.groupca.thomas.util.FrameUtils;

/**
 * AlexWindow.java
 * @author Alex
 */
public class AlexWindow { // Must create variables at the start so they can be used outside of creatWindow()
    private static Color bgColour = new Color(70, 145, 0);
    private static GameLoop game;
    private static JFrame f;
    private static Label points;
    private static Label timer;
    
    // Booleans that track when keys are held
    private static boolean up = false;
    private static boolean down = false;
    private static boolean left = false;
    private static boolean right = false;
    
    public AlexWindow() { // Create window
        GameWindow gamePanel = new GameWindow();
        
    }
    
    static public class GameWindow extends JPanel implements KeyListener{ 
        // Main panel
        public GameWindow() { // Create and populate game screen
            game = new GameLoop(this); // Create GameLoop and pass this into it
            setBorder(BorderFactory.createLineBorder(Color.black)); // Makes black border
            f = new JFrame("Frame"); // Create frame and JPanel for buttons
            JPanel topDisplay = new JPanel(new FlowLayout(FlowLayout.LEFT));
            f.setFocusable(true);
            f.setFocusTraversalKeysEnabled(false);
            f.addKeyListener(this);
            //Points, timer and pass panel obj to gameLoop
            points = new Label("Points"); // Create label for points
            points.setFont(FontManager.getFont(20));
            timer = new Label("Points"); // Create label for timer
            timer.setFont(FontManager.getFont(20));
            
            // Layout and add labels to topdisplay
            f.add(topDisplay, BorderLayout.NORTH);
            points.setPreferredSize(new Dimension(130,40));
            topDisplay.add(points);
            topDisplay.add(timer);

            f.setResizable(false);
            
            f.add(this); // Add panel
            FrameUtils.setBackToMenuOnClose(f, AlexWindow::exitButton);
            this.setBackground(bgColour);
            f.pack();
            f.setVisible(true);
            f.setLocationRelativeTo(null);
        }
        @Override // Set window size
        public Dimension getPreferredSize() {
            return new Dimension(1000,600);
        }
        
        @Override // Paint/Display function, draws onto the screen
        public void paintComponent(Graphics g) {
            super.paintComponent(g); 
            game.render(g);
            updateFunc(); // Updates values
        }
        
        @Override // Key Listeners
        public void keyPressed(KeyEvent e) { // Detects when keys are pressed down
            int code = e.getKeyCode();
            switch(code) {
                case 87:
                    up=true;
                    break;
                case 83:
                    down=true;
                    break;
                case 65:
                    left=true;
                    break;
                case 68:
                    right=true;
                    break;
                case 69:
                    game.toolInteraction();
                    break;
            }
        }
        @Override
        public void keyReleased(KeyEvent e) { // Detects when keys are released
            int code = e.getKeyCode();
            switch(code) {
                case 87:
                    up=false;
                    break;
                case 83:
                    down=false;
                    break;
                case 65:
                    left=false;
                    break;
                case 68:
                    right=false;
                    break;
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {
            // Cannot be left blank, must be overridden
        }
        // Must dispose of the window from the gameLoop
        static void disposeWindow() {
            f.dispose();
        }
    }
    static public void updateFunc() { // Updates text every tick
        game.movePlayer(up, down, left, right);
        points.setText("Points: "+game.getPoints());
        timer.setText("Time left: "+(120-game.getSeconds()));
    }
    static public void exitButton() {
        game.resetGame(); // Resets game so new instance is loaded next time
    }
}


