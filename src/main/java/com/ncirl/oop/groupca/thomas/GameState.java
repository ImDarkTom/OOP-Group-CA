package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        gameObjects.add(new PathDrawer(0, 0, 0));

        gameObjects.add(new Settlement(
                50,
                50
        ));
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

    public static void addFoodDelivery(Farm from, Settlement target) {
        foodDeliveries.add(new FoodDelivery(from, target));
    }

    // loop
    static Timer frameTimer;
    static Timer logicTimer;

    public static void startGameLoop(TomGameWindow windowInstance, GameCanvas canvasInstance) {
        // 10 ticks/s
        logicTimer = new Timer(100, _ -> {
            GameValues.day++;

            windowInstance.setBuildingMaterialAmount(GameState.getPlayerMaterials());

            windowInstance.setFarmBtnEnabled(GameState.getPlayerMaterials() >= GameState.FARM_PRICE);

            windowInstance.setScoreText(GameValues.score);
            windowInstance.setDayText();

            GameState.tickLogic();
        });

        // 30 fps
        frameTimer = new Timer(33, _ -> canvasInstance.repaint()); // this re-runs `paintComponent`

        frameTimer.start();
        logicTimer.start();
    }

    public static void pauseGame() {
        if (frameTimer != null && logicTimer != null) {
            frameTimer.stop();
            logicTimer.stop();
        }
    }

    public static void unpauseGame() {
        if (frameTimer != null && logicTimer != null) {
            frameTimer.start();
            logicTimer.start();
        }
    }

    // spawn settlements
    public static void spawnSettlement() {
        gameObjects.add(new Settlement(
                (int)(Math.random() * 1000),
                (int)(Math.random() * 600)
        ));
    }

    // Getters & Setters
    public static int getPlayerMaterials() {
        return playerMaterials;
    }

    public static void setPlayerMaterials(int playerMaterials) {
        GameState.playerMaterials = playerMaterials;
    }
}
