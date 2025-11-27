package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.util.FrameUtils;

import javax.swing.*;
import java.awt.*;

public class TomGameWindow extends JFrame {
    private GameCanvas canvas;
    private JPanel controlPanel;

    private JToggleButton pauseBtn;
    private JButton placeFarmBtn;
    private JLabel buildingMaterialLbl;

    private JLabel daysLbl;
    private JLabel scoreLbl;

    public TomGameWindow() {
        initComponents();
    }

    // Even though this wasn't made through the form editor, still have an initComponents method for the sake of uniformity
    private void initComponents() {
        final int WINDOW_WIDTH = 1000;
        final int WINDOW_HEIGHT = 600;

        canvas = new GameCanvas();
        controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        pauseBtn = new JToggleButton("⏸");
        placeFarmBtn = new JButton("Place Farm [" + GameState.FARM_PRICE + "]");
        buildingMaterialLbl = new JLabel("Materials: 0");

        daysLbl = new JLabel("Day 0/0");
        scoreLbl = new JLabel("Score: 0");

        // Frame meta#
        FrameUtils.setBackToMenuOnClose(this, GameState::resetState);

        setTitle("Tom - Food Distribution Game");
        setResizable(false);

        canvas.setBackground(new Color(0, 127, 12));

        // pause btn
        controlPanel.add(pauseBtn);
        pauseBtn.addActionListener(e -> {
            if (pauseBtn.isSelected()) {
                GameState.pauseGame();
            } else {
                GameState.unpauseGame();
            }
        });

        // place farm btn
        controlPanel.add(placeFarmBtn);
        placeFarmBtn.addActionListener(_ -> GameState.placeFarm());
        placeFarmBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tom_game/contruct.png")));

        // building material lbl
        controlPanel.add(buildingMaterialLbl);
        buildingMaterialLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small_icons/wrench.png")));

        controlPanel.add(daysLbl);
        daysLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tom_game/calendar.png")));
        daysLbl.setBackground(new Color(181, 107, 74));

        controlPanel.add(scoreLbl);

        // frame ui
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);

        pack();

        // set size & set location has to be after pack
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);

        // game state
        GameState.generateWorld();
        GameState.startGameLoop(this, canvas);
    }

    // get/set
    public void setBuildingMaterialAmount(int amount) {
        buildingMaterialLbl.setText("Materials: " + amount);
    }

    public void setFarmBtnEnabled(boolean val) {
        placeFarmBtn.setEnabled(val);
    }

    public void setScoreText(int score) {
        this.scoreLbl.setText("Score: " + score);
    }

    public void setDayText() {
        this.daysLbl.setText("Day " + GameValues.day + "/" + GameValues.DAYS_TOTAL);
    }
}
