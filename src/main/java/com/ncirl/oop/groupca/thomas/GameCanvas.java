package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class GameCanvas extends JPanel {
    private Point lastClickPos = null;

    public GameCanvas() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lastClickPos = e.getPoint();
                System.out.println("clicked");
            }
        });
    }

    /**
     * - Add item to list
     * - When we click, lastClickPos is set to non-null.
     * -
     * @param g2
     */
    private void renderFrame(Graphics2D g2) {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mousePos, this);

        if (lastClickPos != null) {
            Runnable clickAction = null;

            for (GameObject object : GameState.gameObjects) {
                System.out.println(object.getPos().x + ", lastClick: " + lastClickPos.toString());
                if (
                        (lastClickPos.x > object.getPos().x && lastClickPos.x < object.getPos().x + object.getSize()) &&
                        (lastClickPos.y > object.getPos().y && lastClickPos.y < object.getPos().y + object.getSize())
                ) {
                    clickAction = object::onClicked;
                }
            }

            if (clickAction != null) {
                clickAction.run();
            }
            lastClickPos = null;
        }

        for (GameObject object : GameState.gameObjects) {
            object.render(g2, mousePos);
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

                windowInstance.setFarmBtnEnabled(GameState.getPlayerMaterials() >= GameState.FARM_PRICE);

                GameState.tickLogic();
            });

        // 30 fps
        Timer frameTimer = new Timer(33, _ -> repaint()); // this re-runs `paintComponent`

        frameTimer.start();
        logicTimer.start();
    }
}