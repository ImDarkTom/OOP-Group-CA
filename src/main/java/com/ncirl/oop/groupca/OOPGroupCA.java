/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.ncirl.oop.groupca;

import com.ncirl.oop.groupca.thomas.TomGameWindow;

/**
 *
 * @author tom
 */
public class OOPGroupCA {
    private static String DEV_SKIP_TO = "tom"; // e.g. set to "tom" to skip menu and launch the game
    public static void main(String[] args) {

        if (DEV_SKIP_TO != null) {
            if (DEV_SKIP_TO.equalsIgnoreCase("tom")) {
                GameLauncher.launchTomGame();
                return;
            } else if (DEV_SKIP_TO.equalsIgnoreCase("alex")) {
                GameLauncher.launchAlexGame();
                return;
            } else if (DEV_SKIP_TO.equalsIgnoreCase("antonio")) {
                GameLauncher.launchAntonioGame();
                return;
            }
        }

        OOPGroupCAGUI myGUI = new OOPGroupCAGUI();
        myGUI.setVisible(true);
    }
}
