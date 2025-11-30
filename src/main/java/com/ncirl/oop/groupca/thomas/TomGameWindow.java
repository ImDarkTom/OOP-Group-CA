package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameWindowUI.ActionButtons;
import com.ncirl.oop.groupca.thomas.util.FrameUtils;

import javax.swing.*;
import java.awt.*;

public class TomGameWindow extends JFrame {
    private GameCanvas canvas;
    private JPanel controlPanel;

    public TomGameWindow() {
        initComponents();
    }

    // Even though this wasn't made through the form editor, still have an initComponents method for the sake of uniformity
    private void initComponents() {
        final int WINDOW_WIDTH = 1000;
        final int WINDOW_HEIGHT = 600;

        canvas = new GameCanvas();
        controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        canvas.setBackground(new Color(0, 127, 12));

        ActionButtons.placeActionButtons(controlPanel);

        add(controlPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        pack();

        // frame ui
        setTitle("Tom - Food Distribution Game");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        FrameUtils.setBackToMenuOnClose(this, GameObjectManager::resetState);

        // game state
        GameObjectManager.generateWorld();
        GameLoop.startGameLoop(canvas);
    }
}
