package com.ncirl.oop.groupca.thomas;

import java.awt.event.ActionEvent;

public class GameValues {
    // Constants
    public static final int DAYS_TOTAL = 3000;
    public static final int FARM_PRICE = 100;

    // Game Values, changed with upgrades
    public static int deliveryHungerDecreaseAmount = 25;
    public static int deliveryRange = 200;
    public static int deliveryDelay = 50;
    public static float deliverySpeed = 1f;

    public static int irrigationDistance = 200;
    public static int irrigationDistanceUpgradeCost = 100;

    // Player values
    public static int day = 0;
    public static int score = 0;
    private static int playerMaterials = 100;

    // upgrade
    public static void upgradeDeliveryRange(ActionEvent _event) {
        if (playerMaterials > irrigationDistanceUpgradeCost) {
            irrigationDistance += 100;
            irrigationDistanceUpgradeCost += 500;
        }
    }

    public static void upgradeIrrigationDistance(ActionEvent _event) {
        if (playerMaterials > irrigationDistanceUpgradeCost) {
            irrigationDistance += 100;
            irrigationDistanceUpgradeCost += 500;
        }
    }

    // Getters/setters
    public static int getPlayerMaterials() {
        return playerMaterials;
    }

    public static void adjustPlayerMaterials(int addedPlayerMaterials) {
        playerMaterials += addedPlayerMaterials;
    }

    public static void setPlayerMaterials(int playerMaterials) {
        GameValues.playerMaterials = playerMaterials;
    }

    public static void addScore(int score) {
        GameValues.score += score;
    }

    public static int getDeliveryRange() {
        return deliveryRange;
    }

    public static int getDeliveryDelay() {
        return deliveryDelay;
    }
}
