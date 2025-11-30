package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.util.UpgradeableValue;

import java.awt.event.ActionEvent;

public class GameValues {

    // Constants
    public static final int DAYS_TOTAL = 3650;
    public static final int FARM_PRICE = 100;

    // Game Values, changed with upgrades
    // delivery
    public static UpgradeableValue deliveryHungerDecreaseAmount = new UpgradeableValue(
            "Delivery Size",
            "How much hunger is decreased per delivery.",
            25,
            175,
            3,
            50
    );

    public static UpgradeableValue deliveryRange = new UpgradeableValue(
            "Delivery Range",
            "How far deliveries can be made.",
            200,
            200,
            50,
            150
    );

    public static UpgradeableValue deliveryDelay = new UpgradeableValue(
            "Delivery Delay",
            "How fast new deliveries are sent out.",
            50,
            125,
            -5,
            50
    );

    public static UpgradeableValue deliverySpeed = new UpgradeableValue(
            "Delivery Speed",
            "The speed at which delivery vans travel across the road.",
            2,
            150,
            1,
            125);

    // irrigation
    public static UpgradeableValue irrigationDistance = new UpgradeableValue(
            "Irrigation Distance",
            "Distance from the river farms can be built at.",
            200,
            1000,
            100,
            500
    );

    public static UpgradeableValue[] upgradesRegistry = { deliveryDelay, deliveryRange, deliverySpeed, deliveryHungerDecreaseAmount, irrigationDistance };

    // Player values
    public static int day = 0;
    public static int score = 0;
    private static int playerMaterials = 100;

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
        return deliveryRange.getValue();
    }

    public static int getDeliveryDelay() {
        return deliveryDelay.getValue();
    }

    public static int getDeliveryHungerDecreaseAmount() {
        return deliveryHungerDecreaseAmount.getValue();
    }
}
