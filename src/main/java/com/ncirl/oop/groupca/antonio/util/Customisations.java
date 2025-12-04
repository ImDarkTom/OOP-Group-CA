/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.util;

import com.ncirl.oop.groupca.alex.util.FileLoader;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author anton
 */
public class Customisations implements Serializable {
    int bodyNum = 0;
    Color alexOnion;
    int hat = 0; 

    public Customisations() { // Set default values
        FileLoader.saveToFile(this, "Customisations.esr");
    }


    // Setters

    public void setBody(int bodyCol) {
        bodyNum = bodyCol;
    }
    public void setAlexOnion(Color alexOnion) {
        this.alexOnion = alexOnion;
    }
    public void setHat(int hat) {
        this.hat = hat;
    }


    // Getters
    public Color getBodyCol() { // Returns number according to int
        switch(bodyNum) {
            case 0:
                return Color.BLUE;
            case 1:
                return Color.CYAN;
            case 2:
                return Color.RED;
            case 3:
                return  Color.MAGENTA;
            case 4:
                return Color.PINK;
            case 5:
                return Color.GRAY;
            case 6:
                return Color.BLACK;
        }
        return Color.BLUE;
    }
    public int getBodyNum() { // Returns number
        return bodyNum;
    }
    public int getHat() {
        return hat;
    }

}
