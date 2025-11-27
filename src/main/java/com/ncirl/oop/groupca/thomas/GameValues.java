package com.ncirl.oop.groupca.thomas;

public class GameValues {
    public static int score = 0;

    public static final int DAYS_TOTAL = 3000;
    public static int day = 0;

    public static int hungerDecreaseAmount = 25;

    public static void addScore(int score) {
        GameValues.score += score;
    }
}
