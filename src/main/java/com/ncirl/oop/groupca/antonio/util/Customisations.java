/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.util;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Anton + Thomas + Alex
 */
public class Customisations implements Serializable {
    int bodyNum = 0;
    Color alexOnion;
    int hat = 0; 

    public Customisations() {}

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
        return switch (bodyNum) {
            case 1 -> Color.CYAN;
            case 2 -> Color.RED;
            case 3 -> Color.MAGENTA;
            case 4 -> Color.PINK;
            case 5 -> Color.GRAY;
            case 6 -> Color.BLACK;
            default -> Color.BLUE;
        };
    }
    public int getBodyNum() { // Returns number
        return bodyNum;
    }
    public int getHat() {
        return hat;
    }

}
