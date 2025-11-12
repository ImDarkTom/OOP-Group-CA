/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex;
import com.ncirl.oop.groupca.alex.Objects.Player;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author DELL
 */
public class AlexWindow {
    
    JFrame f;
    JButton btn1;
    
    public AlexWindow() {
        createWindow();
    }
    
    public void createWindow() {
        Label L1 = new Label("Label 1");
        f = new JFrame("Frame");
        btn1 = new JButton("");
        
        btn1.setBounds(150, 200, 150, 50);
    
        f.add(L1);
        f.add(btn1);
        f.add(new panel());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
    
    public class panel extends JPanel {
        public panel() {
            setBorder(BorderFactory.createLineBorder(Color.black));
        }
        Player player = new Player();
        
        public Dimension getPreferredSize() {
            return new Dimension(500,500);
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);  
            player.paintPlayer(g);

            // Draw Text
            g.drawString("This is my custom Panel!",10,20);
        } 
    }

}


