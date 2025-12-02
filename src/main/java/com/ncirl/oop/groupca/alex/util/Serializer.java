/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex.util;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import com.ncirl.oop.groupca.alex.util.*;

/*
 *
 * @author DELL
 */
public class Serializer { // Saves object
    static public void serialize(Object obj, String file) {
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(file+".esr"));
            objOut.writeObject(obj);
            objOut.close(); // Close streams
        } catch (IOException i) { // Catch errors
            i.printStackTrace();
        }
    }
    
    public Scores getScore() { // Returns score object
        Scores score = new Scores();
        try {
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("Scores.esr"));
            score = (Scores) objIn.readObject();
            objIn.close(); // Close streams
        } catch (IOException i) { // Catch errors
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return score;
    }
    public Customisation getCustom() { // Returns score object
        Customisation custom = new Customisation();
        try {
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("Customisation.esr"));
            custom = (Customisation) objIn.readObject();
            objIn.close(); // Close streams
        } catch (IOException i) { // Catch errors
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return custom;
    }
}