/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex.util;
import java.io.Serializable;
import java.awt.Color;

/**
 *
 * @author DELL
 */
public class Customisation implements Serializable {
    Color alexBody; Color alexHead; Color alexWheat; Color alexOnion;
    boolean hat1; boolean hat2; boolean hat3;
    public Customisation() { // Set default values
        alexBody = Color.blue;
        alexHead = Color.yellow;
        hat1=false;hat2=false;hat3=false;
    }
    // Setters
    
    
    // Getters
    
    public Color getAlexBody() {
        return alexBody;
    }
    
    public Color getAlexHead() {
        return alexHead;
    }
}
