/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.ncirl.oop.groupca;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Thomas + Antonio
 */
public class OOPGroupCA {
    private static String DEV_SKIP_TO; // e.g. set to "tom" to skip menu and launch the game
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

        // Set LookAndFeel to Nimbus, taken from Netbeans' generated UI code.
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Aptos", Font.BOLD, 12));
                    break;
                }
            }
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException ex) {
            System.out.println("Error setting LookAndFeel: " + ex);
        }

        OOPGroupCAGUI myGUI = new OOPGroupCAGUI();
        myGUI.setVisible(true);
    }
}
