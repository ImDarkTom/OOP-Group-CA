/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex;
import com.ncirl.oop.groupca.alex.Objects.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.ncirl.oop.groupca.OOPGroupCAGUI;
import com.ncirl.oop.groupca.thomas.util.FrameUtils;

/**
 *
 * @author DELL
 */
public class AlexWindow { // Must create variables at the start so they can be used outside of creatWindow()
    private static GameLoop game = new GameLoop(); 
    private static JFrame f;
    private static JButton btn1;
    private static Label points;
    
    // Booleans that track when keys are held
    private static boolean up = false;
    private static boolean down = false;
    private static boolean left = false;
    private static boolean right = false;
    
    public AlexWindow() {
        createWindow();
    }
    
    public static void createWindow() {
        GameWindow gamePanel = new GameWindow();
    }
    
    static public class GameWindow extends JPanel implements KeyListener{ // Main panel
        public GameWindow() {
            setBorder(BorderFactory.createLineBorder(Color.black)); // Makes black border
            f = new JFrame("Frame"); // Create frame and JPanel for buttons
            JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
            f.addKeyListener(this);
            f.setFocusable(true);
            f.setFocusTraversalKeysEnabled(false);

            points = new Label("Points"); // Create label for points
            game.setPanel(this); // Pass panel to GameLoop so repaint() can be called every tick
            game.startTicks(); // Start tick timer as GameLoop now has panel

            // Add button and container JPanel
            f.add(buttonContainer, BorderLayout.NORTH);
            buttonContainer.add(points);

            f.setResizable(false);
            f.add(this); // Add panel to
            FrameUtils.setBackToMenuOnClose(f, AlexWindow::exitButton);
            //f.setLayout(new BorderLayout());
            f.pack();
            f.setVisible(true);
        }
        
        @Override
        public Dimension getPreferredSize() { // Set window size
            return new Dimension(1000,600);
        }
        
        @Override // Paint to gameWindow
        public void paintComponent(Graphics g) { // Function to draw items to screen, handles graphics
            super.paintComponent(g);
            
            game.render(g);
            
            if(game.getStatus()==false) { // Shows instructions
                g.drawString("Welcome", 100, 100);
                g.drawString("You must tend the farm.", 100, 130);
                g.drawString("Pick up and drop tools with E, move with WASD", 100, 160);
                g.drawString("The onions (purple circles), need the shovel. The wheat requires the scythe.", 100, 190);
            }
            updateFunc();
        }
        
        @Override // Key Listeners
        public void keyPressed(KeyEvent e) {
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
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
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

        @Override // Key Listeners
        public void keyTyped(KeyEvent e) {
            // Must be overridden
        }
        
    }
    static public void updateFunc() {
        game.movePlayer(up, down, left, right);
        points.setText("Points: "+game.getPoints());
    }
    
    static public void exitButton() {
        game.endTicks();
 
   }
}


