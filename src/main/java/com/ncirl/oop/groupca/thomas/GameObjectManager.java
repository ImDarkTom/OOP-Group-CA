package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.*;

import java.util.ArrayList;

public class GameObjectManager {
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    public static ArrayList<FoodDelivery> foodDeliveries = new ArrayList<>();

    public static void placeFarm() {
        if (GameValues.getPlayerMaterials() < GameValues.FARM_PRICE) {
            // Not enough materials
            return;
        }

        GameValues.adjustPlayerMaterials(-GameValues.FARM_PRICE);
        gameObjects.add(new FarmGhost(0, 0));
    }

    public static void generateWorld() {
        gameObjects.add(new PathDrawer());
        gameObjects.add(new RiverDrawer());

        gameObjects.add(new Settlement(50, 50));
    }

    public static void resetState() {
        gameObjects = new ArrayList<>();
        GameValues.setPlayerMaterials(100);
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

    // spawn settlements
    public static void spawnSettlement() {
        gameObjects.add(new Settlement(
                (int)(Math.random() * 1000),
                (int)(Math.random() * 600)
        ));
    }
}
