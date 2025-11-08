package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.GameObject;

import java.util.ArrayList;

public class GameState {
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();

    private GameState() {};

    public static void generateWorld() {
        gameObjects.add(new GameObject(
                50,
                50,
                50
        ));
    }
}
