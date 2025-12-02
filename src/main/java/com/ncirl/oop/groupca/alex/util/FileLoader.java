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

/*
 *
 * @author Alex + Thomas
 */
public class FileLoader { // Saves object
    static public void saveToFile(Object obj, String fileName) {
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(fileName));
            objOut.writeObject(obj);
            objOut.close(); // Close streams
        } catch (IOException i) { // Catch errors
            i.printStackTrace();
        }
    }
    
    public static <T> T loadFromFile(String fileName, Class<T> type) { // Returns score object
        try {
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(fileName));
            Object readObject = objIn.readObject();
            objIn.close(); // Close streams

            if (type.isInstance(readObject)) {
                return (T) readObject;
            } else {
                throw new Error("Could not assert that returned object was of type.");
            }
        } catch (Exception e) {
            try {
                // Get the constructor for the class type we pass
                return type.getDeclaredConstructor().newInstance();
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public static Customisation getCustom() { // Returns score object
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