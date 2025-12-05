/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.util;

import com.ncirl.oop.groupca.thomas.enums.TruckStyle;

import java.awt.Color;
import java.io.Serializable;

/**
 * <p>
 *     `bodyNum/bodyColor` is used by Antonio.
 *     `hat` is used by Alex.
 *     `truckStyle` is used by Thomas.
 * </p>
 *
 * @author Anton + Thomas + Alex
 */
public class Customisations implements Serializable {
    int bodyNum = 0;
    int hat = 0;
    int truckStyleIndex = 0;

    public Customisations() {}

    // Setters
    public void setBody(int bodyCol) {
        bodyNum = bodyCol;
    }

    public void setHat(int hat) {
        this.hat = hat;
    }

    public void setTruckStyleIndex(int truckStyleIndex) {
        this.truckStyleIndex = truckStyleIndex;
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

    public int getTruckStyleIndex() {
        return truckStyleIndex;
    }

    public TruckStyle getTruckStyle() {
        return TruckStyle.values()[truckStyleIndex];
    }
}
