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
    private int alexScore = 0;
    private int thomasScore = 0;
    private int antonioScore = 0;
    private String alexMsg = "You have not yet played.";
    private String thomasMsg = "You have not yet played.";
    private String antonioMsg = "You have not yet played.";

    // Setters
    public void setAlexScore(int alexInt) {
        this.alexScore = alexInt;
    }

    public void setThomasScore(int thomasInt) {
        this.thomasScore = thomasInt;
    }

    public void setAntonioScore(int antonioInt) {
        this.antonioScore = antonioInt;
    }

    public void setAlexMsg(String alexMsg) {
        this.alexMsg = alexMsg;
    }

    public void setThomasMsg(String thomasMsg) {
        this.thomasMsg = thomasMsg;
    }

    public void setAntonioMsg(String antonioMsg) {
        this.antonioMsg = antonioMsg;
    }
    public int getTotalScore() {
        return alexScore+antonioScore+thomasScore;
    }

    // Getters return all saved info out at once
    public String getAlexScoreText() {
        return "Growing Food: " + this.alexScore + " points. " + this.alexMsg;
    }

    public String getAntonioScoreText() {
        return "Delivering Food: " + this.antonioScore + " points." + this.antonioMsg;
    }

    public String getThomasScoreText() {
        return "Supplying Food: " + this.thomasScore + " points. " + this.thomasMsg;
    }

    public String getFullScoreText() {
        return "Total Score: " + (this.alexScore + this.antonioScore + this.thomasScore) + " points";
    }
}
