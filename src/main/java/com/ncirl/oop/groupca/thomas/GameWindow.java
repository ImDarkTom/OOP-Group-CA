package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.GameObject;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private GameWindow() {}

    public static void createWindow() {
        System.setProperty("sun.java2d.uiScale", "2.0");

        int width = 800;
        int height = 600;

        JFrame frame = new JFrame();
        frame.setTitle("Game Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // UI
        // // 2D canvas
        GameCanvas canvas = new GameCanvas();

        JLabel buildingMaterialLbl = new JLabel("Materials: 0");
        JButton placeFarmBtn = new JButton("Place Farm");

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.add(placeFarmBtn);
        controlPanel.add(buildingMaterialLbl);

        frame.setLayout(new BorderLayout());

        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(canvas, BorderLayout.CENTER);

        frame.pack();

//        GroupLayout layout = new GroupLayout(frame.getContentPane());
//        frame.getContentPane().setLayout(layout);
//
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addContainerGap()
//                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                                        .addComponent(canvas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                        .addGroup(layout.createSequentialGroup()
//                                                .addComponent(placeFarmBtn, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
//                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                                                .addComponent(buildingMaterialLbl, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
//                                                .addGap(0, 0, Short.MAX_VALUE)))
//                                .addContainerGap())
//        );
//
//        layout.setVerticalGroup(
//                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addContainerGap()
//                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//                                        .addComponent(placeFarmBtn)
//                                        .addComponent(buildingMaterialLbl))
//                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                                .addComponent(canvas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addContainerGap())
//        );
//
//        frame.pack();

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
            // Setup any objects we may have
            for (GameObject object : GameState.gameObjects) {
                object.setup();
            }

            Timer timer = new Timer(33, _ -> {
                frameSinceStart++;
                repaint();
            });

            timer.start();
        }
    }
}
