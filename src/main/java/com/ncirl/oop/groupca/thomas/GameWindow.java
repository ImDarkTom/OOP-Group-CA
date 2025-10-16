package com.ncirl.oop.groupca.thomas;

import javax.swing.*;

public class GameWindow {
    public static void createWindow(String title, int width, int height) {
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Canvas canvas = new Canvas();
        canvas.setBounds(0, 0, width, height);

        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
