package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.*;

import java.awt.*;
import java.util.ArrayList;

public class GameObjectManager {
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    public static ArrayList<FoodDelivery> foodDeliveries = new ArrayList<>();

    public static void placeFarm() {
        for (FarmGhost ghost : GameObjectManager.objectsOfType(FarmGhost.class)) {
            // If a ghost is already being placed down, cancel it
            GameObjectManager.removeGameObject(ghost);
            RiverDrawer.hidePlacementOverlay();
            return;
        }

        if (GameValues.getPlayerMaterials() < GameValues.FARM_PRICE) {
            // Not enough materials
            return;
        }

        gameObjects.add(new FarmGhost());
    }

    public static void generateWorld() {
        gameObjects.add(new RiverDrawer());
        gameObjects.add(new PathDrawer());

        for (int i = 0; i < 200; i++) {
            spawnSettlement();
        }
        gameObjects.add(new Settlement(
                50 + (int)(Math.random() * 50),
                100 + (int)(Math.random() * 100)
        ));
    }

    public static void resetState() {
        GameLoop.stopLoops();
        gameObjects = new ArrayList<>();
        GameValues.setPlayerMaterials(100);
    }

    public static void tickLogic() {
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
        // 50px margin at edge of screen where it can't generate
        int generatedX = (int) ((Math.random() * (TomGameWindow.getCanvasWidth())));
        int generatedY = (int) ((Math.random() * (TomGameWindow.getCanvasHeight())));

        Point generatedPoint = new Point(generatedX, generatedY);

        // Repeat until we have a settlement between 100 and 300 px away from the river
        // todo: collision check
        System.out.println(RiverDrawer.distanceToRiver(generatedPoint));
        while (
                (RiverDrawer.distanceToRiver(generatedPoint) > 300d ||
                        RiverDrawer.distanceToRiver(generatedPoint) < 200d)
        ) {
            System.out.println("Invalid distance to river: " + RiverDrawer.distanceToRiver(generatedPoint));
            generatedX = (int) (50 + (Math.random() * (TomGameWindow.getCanvasWidth() - 100)));
            generatedY = (int) (50 + (Math.random() * (TomGameWindow.getCanvasHeight() - 100)));
            System.out.println(RiverDrawer.distanceToRiver(generatedPoint));

            generatedPoint.setLocation(generatedX, generatedY);
        }

        gameObjects.add(new Settlement(generatedX, generatedY));

        // When we add a new settlement, refresh every farm's inRangeSettlements
        // to account for this new settlement.
        refreshInRangeSettlements();
    }

    public static void refreshInRangeSettlements() {
        GameObjectManager.objectsOfType(Farm.class).forEach(Farm::refreshInRangeSettlements);
    }

    // collision
    public static boolean checkCollision(Point point) {
        for (GameObject object : gameObjects) {
            Point boundingTL = object.getPos();
            Point boundingBR = new Point(
                    object.getPos().x + object.getSize(),
                    object.getPos().y + object.getSize()
            );

            if (
                    (point.x > boundingTL.x && point.y > boundingTL.y)
                    && (point.x < boundingBR.x && point.y < boundingBR.y)
            ) {
                return false;
            }
        }

        return true;
    }
}
