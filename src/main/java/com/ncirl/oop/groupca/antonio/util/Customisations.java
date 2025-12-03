/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.antonio.util;

import com.ncirl.oop.groupca.alex.util.FileLoader;

import java.awt.*;
import java.io.Serializable;

/**
 *
 * @author anton
 */
public class Customisations implements Serializable {
    Color alexBody = Color.blue;
    Color alexHead = Color.yellow;
    Color alexWheat;
    Color alexOnion;
    int hat = 1;

    public Customisations() { // Set default values
        FileLoader.saveToFile(this, "Customisations.esr");
    }


    // Setters

    public void setAlexBody(Color alexBody) {
        this.alexBody = alexBody;
    }

    public void setAlexHead(Color alexHead) {
        this.alexHead = alexHead;
    }

    public void setAlexWheat(Color alexWheat) {
        this.alexWheat = alexWheat;
    }

    public void setAlexOnion(Color alexOnion) {
        this.alexOnion = alexOnion;
    }

    public void setHat(int hat) {
        this.hat = hat;
    }


    // Getters


    public Color getAlexBody() {
        return alexBody;
    }

    public Color getAlexHead() {
        return alexHead;
    }

    public Color getAlexWheat() {
        return alexWheat;
    }

    public Color getAlexOnion() {
        return alexOnion;
    }

    public int getHat() {
        return hat;
    }

}
