package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.OOPGroupCAGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TomGameWindow extends JFrame {
    private GameCanvas canvas;
    private JPanel controlPanel;
    private JButton backToMenuBtn;
    private JButton placeFarmBtn;
    private JLabel buildingMaterialLbl;
    private JLabel scoreLbl;

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
        scoreLbl = new JLabel("Score: 0");

        // Frame meta
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game Window");

        // Canvas
        canvas.setBackground(new Color(0, 127, 12));

        // back to menu btn
        controlPanel.add(backToMenuBtn);
        backToMenuBtn.addActionListener(this::backToMenuBtnAction);
        backToMenuBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small_icons/back.png")));

        // place farm btn
        controlPanel.add(placeFarmBtn);
        placeFarmBtn.addActionListener(_ -> GameState.placeFarm());
        placeFarmBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tom_game/contruct.png")));

        // building material lbl
        controlPanel.add(buildingMaterialLbl);
        buildingMaterialLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small_icons/wrench.png")));

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
        canvas.startGameLoop(this);
    }

    // button actions
    private void backToMenuBtnAction(ActionEvent _e) {
        if (JOptionPane.showConfirmDialog(this, "Are you sure you want to go back to menu", "Confirm", JOptionPane.YES_NO_OPTION) == 1) {
            // If no, return
            return;
        }
        OOPGroupCAGUI myGUI = new OOPGroupCAGUI();
        myGUI.setVisible(true);

        GameState.resetState();

        dispose();
    }

    // get/set
    public void setBuildingMaterialAmount(int amount) {
        buildingMaterialLbl.setText("Materials: " + amount);
    }

    public void setFarmBtnEnabled(boolean val) {
        placeFarmBtn.setEnabled(val);
    }
}
