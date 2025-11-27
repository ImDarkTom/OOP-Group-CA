package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.GameObject;
import com.ncirl.oop.groupca.thomas.util.Log;

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
                Log.info("Detected click at " + e.getX() + ", " + e.getY());
                lastClickPos = e.getPoint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderFrame((Graphics2D) g);
    }

    private void renderFrame(Graphics2D g2) {
        // Handle click events inside frame render to feel more instant.
        checkForClickEvents();

        // Get mouse pos relative to the game panel and pass to object's renderer.
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mousePos, this);

        for (GameObject object : GameState.gameObjects) {
            object.render(g2, mousePos);
        }
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

    // Utils
    /**
     * If lastClickPos isn't null, go through every gameObject and
     * check if the click is within the bounds of the object by using
     * size to determine where on screen the object is, if it is within
     * the bounds of the object, set the click action to that object's
     * `onClicked`, since the list is in order of when things were added,
     * newer objects will overwrite the action.
     * <p>
     * This is useful as it means that if we have a farm ghost while the
     * user is hovering over another object, the farm ghost will take
     * priority and have its click action performed.
     */
    private void checkForClickEvents() {
        if (lastClickPos != null) {
            Runnable clickAction = null;

            for (GameObject object : GameState.gameObjects) {
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
    }
}