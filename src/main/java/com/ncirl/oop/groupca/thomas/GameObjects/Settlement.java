package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.GameState;

import javax.swing.*;
import java.awt.*;

public class Settlement extends GameObject {
//    private final int MAX_HUNGER = 1000;
    private int hunger = 0;
    private int giveMaterialTick = 0;

    private final Image asset;

    public Settlement(int startX, int startY) {
        super(startX, startY, 50);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        asset = toolkit.getImage(getClass().getResource("/tom_game/settlement.png"));
    }

    @Override
    public void setup() {}

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        g2.setColor(Color.RED);
        g2.drawImage(asset, pos.x, pos.y, null);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2.drawString("Hunger: " + hunger, pos.x, pos.y);
    }

    @Override
    public void tickLogic() {
        this.hunger += 1;

        giveMaterialTick++;

        if (giveMaterialTick == 10) {
            giveMaterialTick = 0;
            GameState.setPlayerMaterials(GameState.getPlayerMaterials() + 1);
        }
    }

    @Override
    public void onClicked() {
        System.out.println("clicked settlement");
    }
}
