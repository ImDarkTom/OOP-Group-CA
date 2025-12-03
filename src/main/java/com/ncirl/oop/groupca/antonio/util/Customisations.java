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
    boolean hat1 = false;;
    boolean hat2 = false;
    boolean hat3 = false;

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

    public void setHat1(boolean hat1) {
        this.hat1 = hat1;
    }

    public void setHat2(boolean hat2) {
        this.hat2 = hat2;
    }

    public void setHat3(boolean hat3) {
        this.hat3 = hat3;
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

    public boolean isHat1(boolean hat1) {
        return this.hat1;
    }

    public boolean isHat2(boolean hat2) {
        return this.hat2;
    }

    public boolean isHat3(boolean hat3) {
        return this.hat3;
    }

}
