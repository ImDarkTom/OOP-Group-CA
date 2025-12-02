package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.OOPGroupCAGUI;
import com.ncirl.oop.groupca.thomas.GameWindowUI.ActionButtons;
import com.ncirl.oop.groupca.thomas.shared.ScoreManager;

import javax.swing.*;

public class GameLoop {
    private static Timer frameTimer;
    private static Timer logicTimer;

    public static void startGameLoop(GameCanvas canvasInstance) {
        // Logic: 10 ticks/s
        logicTimer = new Timer(100, _ -> {
            GameValues.bumpDay();
            
            ActionButtons.updateFarmBtn();

            ActionButtons.updatePlayerMaterialsText();
            ActionButtons.updateDayText();
            ActionButtons.updateScoreText();

            GameObjectManager.tickLogic();

            if (GameValues.getDay() >= GameValues.DAYS_TOTAL) {
                handleEndGame();
            }
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

    public static void stopLoops() {
        frameTimer.stop();
        logicTimer.stop();
    }

    public static void handleEndGame() {
        stopLoops();

        int score = GameValues.getScore();
        ScoreManager.setThomasScore(score);

        if (score >= 85) {
            ScoreManager.setThomasMsg("You done so well you got more than one settlement!");
        } else if (score >= 35) {
            ScoreManager.setThomasMsg("You managed to get one settlement to the highest level!");
        } else if (score >= 10) {
            ScoreManager.setThomasMsg("You managed to upgrade a settlement!");
        } else {
            ScoreManager.setThomasMsg("Better luck next time.");
        }

        JOptionPane.showMessageDialog(TomGameWindow.gameWindow, "Game Over! Your score was " + score + ". Press OK or X to go back to main menu.");

        TomGameWindow.gameWindow.dispose();

        OOPGroupCAGUI mainMenu = new OOPGroupCAGUI();
        mainMenu.setVisible(true);
    }
}
