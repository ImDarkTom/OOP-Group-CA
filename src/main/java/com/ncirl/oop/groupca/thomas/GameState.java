package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.GameObject;
import com.ncirl.oop.groupca.thomas.GameObjects.Settlement;

import javax.swing.*;
import java.util.ArrayList;

public class GameState {
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static int playerMaterials = 100;

    public static boolean isPlacingFarm = false;

    private GameState() {}

    public static void placeFarm() {
        if (playerMaterials < 110) {
            // TODO: maybe a popup?
            return;
        }

        playerMaterials -= 110;
        isPlacingFarm = true;
    }

    public static void generateWorld() {
        gameObjects.add(new Settlement(
                50,
                50
        ));
    }

    public static void resetState() {
        gameObjects = new ArrayList<>();
        playerMaterials = 100;
        isPlacingFarm = false;
    }

    public static void tickLogic() {
        for (GameObject object : gameObjects) {
            object.tickLogic();
        }
    }

    // Getters & Setters
    public static int getPlayerMaterials() {
        return playerMaterials;
    }

    public static void setPlayerMaterials(int playerMaterials) {
        GameState.playerMaterials = playerMaterials;
    }
}
