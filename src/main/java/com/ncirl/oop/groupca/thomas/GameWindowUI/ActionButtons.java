package com.ncirl.oop.groupca.thomas.GameWindowUI;

import com.ncirl.oop.groupca.thomas.GameLoop;
import com.ncirl.oop.groupca.thomas.GameObjectManager;
import com.ncirl.oop.groupca.thomas.GameValues;

import javax.swing.*;
import java.awt.*;

public class ActionButtons {
    // UI Elements
    private static JToggleButton pauseBtn;
    private static JButton placeFarmBtn;
    private static JLabel buildingMaterialsAmountLbl;
    private static JLabel daysLbl;
    private static JLabel scoreLbl;


    public static void placeActionButtons(JPanel panel) {
        // Set values for the buttons.
        pauseBtn = new JToggleButton("⏸");
        placeFarmBtn = new JButton("Place Farm [" + GameValues.FARM_PRICE + "]");
        buildingMaterialsAmountLbl = new JLabel("Materials: 0");

        daysLbl = new JLabel("Day 0/0");
        scoreLbl = new JLabel("Score: 0");

        // Add them to the UI
        panel.add(pauseBtn);
        pauseBtn.addActionListener(e -> {
            if (pauseBtn.isSelected()) {
                GameLoop.pauseGame();
            } else {
                GameLoop.unpauseGame();
            }
        });

        // place farm btn
        panel.add(placeFarmBtn);
        placeFarmBtn.addActionListener(_ -> GameObjectManager.placeFarm());
        placeFarmBtn.setIcon(new javax.swing.ImageIcon(ActionButtons.class.getResource("/tom_game/contruct.png")));

        // building material lbl
        panel.add(buildingMaterialsAmountLbl);
        buildingMaterialsAmountLbl.setIcon(new javax.swing.ImageIcon(ActionButtons.class.getResource("/small_icons/wrench.png")));

        panel.add(daysLbl);
        daysLbl.setIcon(new javax.swing.ImageIcon(ActionButtons.class.getResource("/tom_game/calendar.png")));
        daysLbl.setBackground(new Color(181, 107, 74));

        panel.add(scoreLbl);
    }

    // Text setters
    public static void updatePlayerMaterialsText() {
        buildingMaterialsAmountLbl.setText("Materials: " + GameValues.getPlayerMaterials());
    }

    public static void updateFarmBtn() {
        placeFarmBtn.setEnabled(GameValues.getPlayerMaterials() >= GameValues.FARM_PRICE);
    }

    public static void updateScoreText() {
        scoreLbl.setText("Score: " + GameValues.score);
    }

    public static void updateDayText() {
        daysLbl.setText("Day " + GameValues.day + "/" + GameValues.DAYS_TOTAL);
    }
}
