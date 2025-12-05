/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.ncirl.oop.groupca;

import com.ncirl.oop.groupca.alex.AlexWindow;
import com.ncirl.oop.groupca.antonio.AntonioGUI;
import com.ncirl.oop.groupca.thomas.TomGameWindow;

/**
 *
 * @author Thomas
 */
public class GameLauncher {
    public GameLauncher() {}
    
    public static void launchAntonioGame() {
        // copied from original launch method in commit
        AntonioGUI myGUI = new AntonioGUI();
        myGUI.setVisible(true);
    }
    
    public static void launchTomGame() {
        TomGameWindow tomGameWindow = new TomGameWindow();
        tomGameWindow.setVisible(true);
    }
    
    public static void launchAlexGame() {
        AlexWindow alexWindow = new AlexWindow();
    }
}

