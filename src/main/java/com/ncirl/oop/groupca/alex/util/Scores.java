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
    private int alexInt = 0;
    private int thomasInt = 0;
    private int antonioInt = 0;
    private String alexMsg = "";
    private String thomasMsg = "";
    private String antonioMsg = "";
    
    public Scores() {}
    
    // Setters
    public void setAlexInt(int alexInt) {
        this.alexInt = alexInt;
    }

    public void setThomasInt(int thomasInt) {
        this.thomasInt = thomasInt;
    }

    public void setAntonioInt(int antonioInt) {
        this.antonioInt = antonioInt;
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
    
    // Getters
    public int getAlexInt() {
        return alexInt;
    }

    public int getThomasInt() {
        return thomasInt;
    }

    public int getAntonioInt() {
        return antonioInt;
    }

    public String getAlexMsg() {
        return alexMsg;
    }

    public String getThomasMsg() {
        return thomasMsg;
    }

    public String getAntonioMsg() {
        return antonioMsg;
    }
    
}
