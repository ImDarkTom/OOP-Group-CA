package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.RiverDrawer;
import com.ncirl.oop.groupca.thomas.util.UpgradeableValue;

public class GameValues {
    // Constants
    public static final int DAYS_TOTAL = 3650;
    public static final int FARM_PRICE = 100;

    // Game Values, changed with upgrades
    // delivery
    private static final UpgradeableValue deliveryHungerDecreaseAmount = new UpgradeableValue(
            "Delivery Size",
            "How much hunger is decreased per delivery.",
            25,
            175,
            3,
            50
    );

    private static final UpgradeableValue deliveryRange = new UpgradeableValue(
            "Delivery Range",
            "How far deliveries can be made.",
            200,
            200,
            100,
            25
    );

    private static final UpgradeableValue deliveryDelay = new UpgradeableValue(
            "Delivery Delay",
            "How fast new deliveries are sent out.",
            50,
            125,
            -5,
            50
    );

    private static final UpgradeableValue deliverySpeed = new UpgradeableValue(
            "Delivery Speed",
            "The speed at which delivery vans travel across the road.",
            2,
            150,
            1,
            125);

    // irrigation
    private static final UpgradeableValue irrigationDistance = new UpgradeableValue(
            "Irrigation Distance",
            "Distance from the river farms can be built at.",
            200,
            500,
            100,
            100,
            RiverDrawer::updateOverlayStroke
    );

    public static UpgradeableValue[] upgradesRegistry = { deliveryDelay, deliveryRange, deliverySpeed, deliveryHungerDecreaseAmount, irrigationDistance };

    // Player values
    private static int day = 0;
    private static int score = 0;
    private static int playerMaterials = 100;

    // Getters/setters
    public static int getDeliveryHungerDecreaseAmount() {
        return deliveryHungerDecreaseAmount.getValue();
    }

    public static int getDeliveryRange() {
        return deliveryRange.getValue();
    }

    public static int getDeliveryDelay() {
        return deliveryDelay.getValue();
    }

    public static int getDeliverySpeed() {
        return deliverySpeed.getValue();
    }

    public static int getIrrigationDistance() {
        return irrigationDistance.getValue();
    }

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

    // day
    public static int getDay() {
        return day;
    }

    public static void bumpDay() {
        GameValues.day++;
    }

    // score
    public static int getScore() {
        return score;
    }
}
