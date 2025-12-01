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
import com.ncirl.oop.groupca.alex.util.Scores;

/**
 *
 * @author DELL
 */
public class SerializeScore {
    static public void Serialize(Scores scoreFile) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Scores.esr");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(scoreFile);
            objOut.close();
            fileOut.close();
            System.out.println("Searaised!!!!!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    static public Scores Deserialize() {
        Scores score = new Scores();
        try {
            FileInputStream fileIn = new FileInputStream("Scores.esr");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            score = (Scores) objIn.readObject();
            objIn.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return score;
        
    }
}
