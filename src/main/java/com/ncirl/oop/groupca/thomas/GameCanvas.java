package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.Farm;
import com.ncirl.oop.groupca.thomas.GameObjects.GameObject;
import com.ncirl.oop.groupca.thomas.util.Vector2D;

import javax.swing.*;
import java.awt.*;

class GameCanvas extends JPanel {
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

    public void startGameLoop(TomGameWindow windowInstance) {
        // 10 ticks/s
            Timer logicTimer = new Timer(100, _ -> {
                windowInstance.setBuildingMaterialAmount(GameState.getPlayerMaterials());

                windowInstance.setFarmBtnEnabled(GameState.getPlayerMaterials() >= 110);

                GameState.tickLogic();
            });

        // 30 fps
        Timer frameTimer = new Timer(33, _ -> repaint()); // this re-runs `paintComponent`

        frameTimer.start();
        logicTimer.start();
    }
}