package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.FarmGhost;
import com.ncirl.oop.groupca.thomas.GameObjects.GameObject;
import com.ncirl.oop.groupca.thomas.GameObjects.PathDrawer;
import com.ncirl.oop.groupca.thomas.GameObjects.Settlement;
import com.sun.jdi.ClassType;

import javax.swing.*;
import java.util.ArrayList;

public class GameState {
    public static final int FARM_PRICE = 100;

    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static int playerMaterials = 100;

    private GameState() {}

    public static void placeFarm() {
        if (playerMaterials < FARM_PRICE) {
            // Not enough materials
            return;
        }

        playerMaterials -= FARM_PRICE;
        gameObjects.add(new FarmGhost(0, 0));
    }

    public static void generateWorld() {
        gameObjects.add(new Settlement(
                50,
                50
        ));

        gameObjects.add(new PathDrawer(0, 0, 0));
    }

    public static void resetState() {
        gameObjects = new ArrayList<>();
        playerMaterials = 100;
    }

    public static void tickLogic() {
        for (GameObject object : gameObjects) {
            object.tickLogic();
        }
    }

    public static void addGameObject(GameObject object) {
        gameObjects.add(object);
    }

    public static void removeGameObject(GameObject object) {
        gameObjects.remove(object);
    }

    public static <T> ArrayList<T> objectsOfType(Class<T> type) {
        ArrayList<T> resultList = new ArrayList<>();

        for (GameObject object : gameObjects) {
            if (type.isInstance(object)) {
                resultList.add((T) object);
            }
        }

        return resultList;
    }

    // Getters & Setters
    public static int getPlayerMaterials() {
        return playerMaterials;
    }

    public static void setPlayerMaterials(int playerMaterials) {
        GameState.playerMaterials = playerMaterials;
    }
}
