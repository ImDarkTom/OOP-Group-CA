package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.*;
import com.sun.jdi.ClassType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

public class GameState {
    public static ArrayList<FoodDelivery> foodDeliveries = new ArrayList<>();

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
        // Shorthand for:
        // for (GameObject object : gameObjects) {
        //     object.tickLogic();
        // }
        gameObjects.forEach(GameObject::tickLogic);

        // https://www.baeldung.com/java-concurrentmodificationexception
        ArrayList<FoodDelivery> toRemove = new ArrayList<>();

        for (FoodDelivery delivery : foodDeliveries) {
            if (delivery.shouldRemove) {
                toRemove.add(delivery);
            }
        }

        foodDeliveries.removeAll(toRemove);

        foodDeliveries.forEach(FoodDelivery::tickLogic);
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

    public static void addFoodDelivery(Point from, Point to, Settlement target) {
        foodDeliveries.add(new FoodDelivery(from, to, target));
    }

    // Getters & Setters
    public static int getPlayerMaterials() {
        return playerMaterials;
    }

    public static void setPlayerMaterials(int playerMaterials) {
        GameState.playerMaterials = playerMaterials;
    }
}
