package com.ncirl.oop.groupca.thomas;

import com.ncirl.oop.groupca.thomas.GameObjects.GameObject;
import com.ncirl.oop.groupca.thomas.GameObjects.Settlement;

import java.util.ArrayList;

public class GameState {
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();

    private GameState() {};

    public static void generateWorld() {
        gameObjects.add(new Settlement(
                50,
                50
        ));
    }
}
