package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameWindowUI.ActionButtons;
import com.ncirl.oop.groupca.thomas.util.FrameUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Group Requirements:
 * - Design consistency (shared custom Nimbus theme) ✔️
 * - Connected navigation (main menu) ✔️
 * - Inheritance & Polymorphism (`GameObject` extended in `Farm`, `Renderable` interface) ✔️
 * .
 * .
 * Individual requirements:
 * - More than 3 classes ✔️
 * - At least 3 features (placing farm, upgrade menu, river, delivery trucks, etc.) ✔️
 * - Data Input (clicking & selecting upgrade) & output (displaying placed farms on screen, showing upgrade success/fail) ✔️
 * - More than 1 user input form (control panel at top of game, and upgrade menu) ✔️
 * - ArrayLists use with objects (`gameObjects` & `foodDeliveries` arraylists in `GameObjectManager`) ✔️
 * - 1+ Instantiable class (Farm, Settlement, GameObject) ✔️
 * - Data structure functionality:
 *   - Add (placing farm)
 *   - Display (rendering farm on screen)
 *   - Search (looking for Settlement GameObjects when connecting them to farm - Farm.java#refreshInRangeSettlements)
 *   - Delete (clicking on a farm to delete it)
 * .
 * .
 * Final requirements:
 * - File I/O todo
 * -
 *
 * @author tom
 */
public class TomGameWindow extends JFrame {
    private static int canvasWidth;
    private static int canvasHeight;

    public static JFrame gameWindow;

    public TomGameWindow() {
        initComponents();
    }

    // Even though this wasn't made through the form editor, still have an initComponents method for the sake of uniformity
    private void initComponents() {
        final int WINDOW_WIDTH = 1000;
        final int WINDOW_HEIGHT = 600;

        setLayout(new BorderLayout());

        GameCanvas canvas = new GameCanvas();
        canvas.setBackground(new Color(0, 127, 12));

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ActionButtons.placeActionButtons(controlPanel);

        add(controlPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        pack();

        // frame ui
        setTitle("Tom - Food Distribution Game");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        FrameUtils.setBackToMenuOnClose(this, GameObjectManager::resetState);

        // Validating lets us calculate the size of the canvas before it's rendered
        validate();
        canvasWidth = canvas.getHeight();
        canvasHeight = canvas.getHeight();

        gameWindow = this;

        // game state
        GameObjectManager.generateWorld();
        GameLoop.startGameLoop(canvas);
    }

    public static int getCanvasHeight() {
        return canvasHeight;
    }

    public static int getCanvasWidth() {
        return canvasWidth;
    }
}
