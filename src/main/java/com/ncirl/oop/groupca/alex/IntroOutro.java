/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class IntroOutro {
    public static void playIntro() { // Plays intro dialog boxes
        JOptionPane.showMessageDialog(null, "Welcome to The Farming Game!\nYou must collect and sell as many plants as you can in 3 minutes!", "Intro", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "You can move with the keys W A S D.\nPress E to pick up and drop tools.", "Intro", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "To pick up onions you need to carry the shovel.\nTo pick up wheat, carry the scythe.", "Intro", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "You can only hold 5 plants at a time, so you must sell them.\nGo to the top right of the screen to sell held plants.", "Intro", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void playOutro(int points) { // Plays outro dialog boxes
        String endMsg = "";
        if(points<40){endMsg="You grew little food...";}
        else if(points<80){endMsg="You grew a decent amount of food.";}
        else if(points>80){endMsg="You grew lots of food! Good job!";}
        JOptionPane.showMessageDialog(null, "You have completed The Farming Game congratulations!\nYou earned "+points+" points!\n"+endMsg, "Intro", JOptionPane.INFORMATION_MESSAGE);
    }
}
