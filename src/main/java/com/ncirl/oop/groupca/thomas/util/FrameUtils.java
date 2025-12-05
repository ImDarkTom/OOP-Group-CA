package com.ncirl.oop.groupca.thomas.util;

import com.ncirl.oop.groupca.OOPGroupCAGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Thomas
 */
public class FrameUtils {
    public static void setBackToMenuOnClose(JFrame currentFrame, Runnable onClose) {
        currentFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        currentFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(currentFrame, "Are you sure you want to go back to menu", "Confirm", JOptionPane.YES_NO_OPTION) == 1) {
                    // If no, return
                    return;
                }
                OOPGroupCAGUI myGUI = new OOPGroupCAGUI();
                myGUI.setVisible(true);

                onClose.run();

                currentFrame.dispose();

                super.windowClosed(e);
            }
        });
    }
}
