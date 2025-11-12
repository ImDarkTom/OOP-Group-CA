package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.GameObject;
import com.ncirl.oop.groupca.thomas.GameObjects.Settlement;

import java.util.ArrayList;

public class GameState {
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();

    private static int materials = 100;

    public static int getMaterials() {
        return materials;
    }

    public static void setMaterials(int materials) {
        GameState.materials = materials;
    }

    private GameState() {};

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
}
