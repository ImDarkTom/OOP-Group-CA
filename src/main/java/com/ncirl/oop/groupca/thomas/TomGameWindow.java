package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.OOPGroupCAGUI;

import javax.swing.*;
import java.awt.*;

public class TomGameWindow extends JFrame {
    private GameCanvas canvas;
    private JPanel controlPanel;
    private JButton backToMenuBtn;
    private JButton placeFarmBtn;
    private JLabel buildingMaterialLbl;

    public TomGameWindow() { initComponents(); }

    // Even though this wasn't made through the form editor, still have an initComponents method for the sake of uniformity
    private void initComponents() {
        final int WINDOW_WIDTH = 800;
        final int WINDOW_HEIGHT = 600;

        canvas = new GameCanvas();
        controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backToMenuBtn = new JButton("Menu");
        placeFarmBtn = new JButton("Place Farm [110]");
        buildingMaterialLbl = new JLabel("Materials: 0");

        // Frame meta
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game Window");

        // Canvas
        canvas.setBackground(new Color(0, 127, 12));

        // back to menu btn
        controlPanel.add(backToMenuBtn);
        backToMenuBtn.addActionListener(_ -> {
            OOPGroupCAGUI myGUI = new OOPGroupCAGUI();
            myGUI.setVisible(true);
            dispose();
        });

        // place farm btn
        controlPanel.add(placeFarmBtn);
        placeFarmBtn.addActionListener(_ -> GameState.placeFarm());

        // building material lbl
        controlPanel.add(buildingMaterialLbl);

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
        canvas.startGameLoop(this);
    }

    public void setBuildingMaterialAmount(int amount) {
        buildingMaterialLbl.setText("Materials: " + amount);
    }

    public void setFarmBtnEnabled(boolean val) {
        placeFarmBtn.setEnabled(val);
    }
}
