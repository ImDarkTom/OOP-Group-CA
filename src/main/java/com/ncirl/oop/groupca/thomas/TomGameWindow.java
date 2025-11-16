package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.OOPGroupCAGUI;
import com.ncirl.oop.groupca.thomas.GameObjects.Farm;
import com.ncirl.oop.groupca.thomas.GameObjects.GameObject;
import com.ncirl.oop.groupca.thomas.util.Vector2D;

import javax.swing.*;
import java.awt.*;

public class TomGameWindow {
    private static final JButton backToMenuBtn = new JButton("< Menu");
    private static final JButton placeFarmBtn = new JButton("Place Farm [110]");
    private static final JLabel buildingMaterialLbl = new JLabel("Materials: 0");

    public TomGameWindow() {}

    public void createWindow() {
        int width = 800;
        int height = 600;

        // Setup JFrame
        JFrame frame = new JFrame();
        frame.setTitle("Game Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the canvas we'll be rendering the game to
        GameCanvas canvas = new GameCanvas();
        canvas.setBackground(new Color(0, 127, 12));

        // UI Layout
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.add(placeFarmBtn);
        controlPanel.add(buildingMaterialLbl);

        // event listeners
        placeFarmBtn.addActionListener((event) -> GameState.placeFarm());
        backToMenuBtn.addActionListener((e) -> {
            OOPGroupCAGUI myGUI = new OOPGroupCAGUI();
            myGUI.setVisible(true);
            frame.dispose();
        });

        frame.setLayout(new BorderLayout());

        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(canvas, BorderLayout.CENTER);

        frame.pack();

        // init state
        GameState.generateWorld();
        canvas.startGameLoop();

        // init window
        frame.setSize(width, height);

        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

    static class GameCanvas extends JPanel {
        private void renderFrame(Graphics2D g2) {
            for (GameObject object : GameState.gameObjects) {
                object.render(g2);
            }

            // Mouse-state related rendering
            if (GameState.isPlacingFarm) {
                Point mousePos = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(mousePos, this);
                Farm.drawGhost(g2, new Vector2D(mousePos.x, mousePos.y));
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            renderFrame((Graphics2D) g);
        }

        public void startGameLoop() {
            // 10 ticks/s
            Timer logicTimer = new Timer(100, _ -> {
                buildingMaterialLbl.setText("Materials: " + GameState.getPlayerMaterials());
                placeFarmBtn.setEnabled(GameState.getPlayerMaterials() >= 110);

                GameState.tickLogic();
            });

            // 30 fps
            Timer frameTimer = new Timer(33, _ -> repaint()); // this re-runs `paintComponent`

            frameTimer.start();
            logicTimer.start();
        }
    }
}
