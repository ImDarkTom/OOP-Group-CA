/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ncirl.oop.groupca.alex.util;
import java.io.Serializable;
/**
 *
 * @author DELL
 */
public class Scores implements Serializable {
    private static int alexScore = 0;
    private static int thomasScore = 0;
    private static int antonioScore = 0;
    private static String alexMsg = "";
    private static String thomasMsg = "";
    private static String antonioMsg = "";

    public Scores() {
        FileLoader.saveToFile(this, "Scores.esr");
    }
    
    // Setters
    public static void setAlexScore(int alexInt) {
        alexScore = alexInt;
    }

    public static void setThomasScore(int thomasInt) {
        thomasScore = thomasInt;
    }

    public static void setAntonioScore(int antonioInt) {
        antonioScore = antonioInt;
    }

    public static void setAlexMsg(String alexMsg) {
        Scores.alexMsg = alexMsg;
    }

    public static void setThomasMsg(String thomasMsg) {
        Scores.thomasMsg = thomasMsg;
    }

    public static void setAntonioMsg(String antonioMsg) {
        Scores.antonioMsg = antonioMsg;
    }

    //
    public static String getAlexScoreText() {
        return "Growing Food: " + alexScore + " points. You done " + alexMsg;
    }

    public static String getAntonioScoreText() {
        return "Delivering Food: " + antonioScore + " points. You done " + antonioMsg;
    }

    public static String getThomasScoreText() {
        return "Supplying Food: " + thomasScore + " points. You done " + thomasMsg;
    }

    public static String getFullScoreText() {
        return "Total Score: " + (alexScore + antonioScore + thomasScore) + " points";
    }
}
