package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.GameState;

import java.awt.*;

public class Settlement extends GameObject {
    private float hunger = 0;
    private int giveMaterialTick = 0;

    public Settlement(double startX, double startY) {
        super(startX, startY, 50);
    }

    @Override
    public void setup() {}

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect((int)pos.x, (int)pos.y, size, size);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2.drawString("Hunger: " + hunger, (int)pos.x, (int)pos.y);
    }

    @Override
    public void tickLogic() {
        this.hunger += 0.1F;

        giveMaterialTick++;

        if (giveMaterialTick == 10) {
            giveMaterialTick = 0;
            GameState.setMaterials(GameState.getMaterials() + 1);
        }
    }
}
