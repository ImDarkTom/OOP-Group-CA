package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.GameObject;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private GameWindow() {};

    public static void createWindow() {
        int width = 800;
        int height = 600;

        JFrame frame = new JFrame();
        frame.setTitle("Game Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameState.generateWorld();

        frame.add(new GameCanvas());

        frame.setSize(width, height);
        frame.setVisible(true);
    }

    static class GameCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            // Set background color
            g2.setColor(new Color(0, 127, 12));
            g2.fillRect(0, 0, getWidth(), getHeight());

            for (GameObject object : GameState.gameObjects) {
                object.render(g2);
            }
        }
    }
}
