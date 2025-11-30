package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameWindowUI.ActionButtons;

import javax.swing.*;

public class GameLoop {
    private static Timer frameTimer;
    private static Timer logicTimer;

    public static void startGameLoop(GameCanvas canvasInstance) {
        // Logic: 10 ticks/s
        logicTimer = new Timer(100, _ -> {
            GameValues.day++;
            
            ActionButtons.updateFarmBtn();

            ActionButtons.updatePlayerMaterialsText();
            ActionButtons.updateDayText();
            ActionButtons.updateScoreText();

            GameObjectManager.tickLogic();
        });

        // Frames: 30 fps
        frameTimer = new Timer(33, _ -> canvasInstance.repaint()); // this re-runs `paintComponent`

        frameTimer.start();
        logicTimer.start();
    }

    public static void pauseGame() {
        // Prevent errors if we pause while unset.
        if (frameTimer != null && logicTimer != null) {
            frameTimer.stop();
            logicTimer.stop();
        }
    }

    public static void unpauseGame() {
        if (frameTimer != null && logicTimer != null) {
            frameTimer.start();
            logicTimer.start();
        }
    }
}
