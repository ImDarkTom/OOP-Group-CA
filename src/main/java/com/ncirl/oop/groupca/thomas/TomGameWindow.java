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
 * - File I/O (FileLoader, CustomisationManager, ScoreManager) ✔️
 * .
 * @author Thomas
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

        // tutorial popup
        JOptionPane.showMessageDialog(
                this,
                """
                        Hello and welcome to the Food Distribution Game! In this game, your task is to feed the settlements \s
                        on the map by providing them with food. To do this, you will need to construct farms near water sources \s
                        that will then start distributing food to nearby settlements. Once a settlement is fed enough, it will begin \s
                        upgrading, and transform into a bigger and more prosperous town. This means more building materials, \s
                        but also higher food requirements. Once you upgrade a town enough, it will turn into a city, and if \s
                        you get a city's upgrade meter to 100%, it will spawn another settlement to take care of. \s
                        
                        You gain score by upgrading the settlements, each settlement upgrade level increases score by \s
                        a set amount, therefore, more settlements = higher score potential. You may purchase upgrades to \s
                        improve various aspects of your farms, such as the delivery speed of your distribution trucks, \s
                        the range of your roads, the delay between trucks, and more. \s
                        
                        The game lasts 10 in-game years, or approximately 6 real minutes. After this, you will get \s
                        a final score, and the game will end. \s
                        
                        Some tips: \s
                        - Farms can't be built too close to each other or directly on top of the river.
                        - Settlements increase materials/sec by 5, towns by 10, and cities by 20.
                        - Clicking a farm will prompt to to delete it (in case you build too far from a settlement).
                        - Roads may cross rivers.
                        
                        Have fun! \s
                        """,
                "Food Distribution Game Tutorial",
                JOptionPane.INFORMATION_MESSAGE
        );

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
