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
//        System.out.println("SAVING SCORES TO FILE: "+ fileName + " contents " + obj);
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
//                System.out.println("LOADED SCORES FROM FILE: " + readObject);
                return (T) readObject;
            } else {
                throw new Error("Could not assert that returned object was of type.");
            }
        } catch (Exception _e) {
            System.out.println(_e.getMessage());
            try {
                // Create a new instance of the class we passed with blank params
                T instance = type.getDeclaredConstructor().newInstance();

                saveToFile(instance, fileName);

                return instance;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}