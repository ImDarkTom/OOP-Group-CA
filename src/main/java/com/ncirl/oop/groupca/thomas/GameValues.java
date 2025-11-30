package com.ncirl.oop.groupca.thomas;

public class GameValues {
    // Constants
    public static final int DAYS_TOTAL = 3000;
    public static final int FARM_PRICE = 100;

    // Game Values, changed with upgrades
    public static int deliveryHungerDecreaseAmount = 25;

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
}
