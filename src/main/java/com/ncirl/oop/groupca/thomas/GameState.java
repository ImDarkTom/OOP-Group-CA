package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.GameObject;
import com.ncirl.oop.groupca.thomas.GameObjects.Settlement;

import java.util.ArrayList;

public class GameState {
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static int playerMaterials = 100;

    private GameState() {}

    public static void generateWorld() {
        gameObjects.add(new Settlement(
                50,
                50
        ));
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
