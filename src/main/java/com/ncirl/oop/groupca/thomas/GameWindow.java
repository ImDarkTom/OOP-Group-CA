package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.GameObject;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private GameWindow() {}

    public static void createWindow() {
        int width = 800;
        int height = 600;

        JFrame frame = new JFrame();
        frame.setTitle("Game Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2D canvas
        GameCanvas canvas = new GameCanvas();
        frame.add(canvas);

        // init state
        GameState.generateWorld();

        // init window
        frame.setSize(width, height);
        frame.setVisible(true);

        canvas.startGameLoop();
    }

    static class GameCanvas extends JPanel {
        private int frameSinceStart;

        private void renderFrame(Graphics2D g2, int frame) {
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("SansSerif", Font.BOLD, 16));
            g2.drawString("Frame: " + frame, 1, 16);

            for (GameObject object : GameState.gameObjects) {
                object.render(g2);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            // Set background color
            g2.setColor(new Color(0, 127, 12));
            g2.fillRect(0, 0, getWidth(), getHeight());

            renderFrame(g2, frameSinceStart);
        }

        public void startGameLoop() {
            Timer timer = new Timer(33, _ -> {
                frameSinceStart++;
                repaint();
            });

            timer.start();
        }
    }
}
