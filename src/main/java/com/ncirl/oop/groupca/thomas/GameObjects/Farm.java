package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.GameState;
import com.ncirl.oop.groupca.thomas.util.Vector2D;

import java.awt.*;

public class Farm extends GameObject {
    private int progress = 0;

    public Farm(double startX, double startY) {
        super(startX, startY, 70);
    }

    @Override
    public void setup() {}

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.fillRect((int)pos.x, (int)pos.y, size, size);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2.drawString("Progress: " + progress, (int)pos.x, (int)pos.y);
    }

    /**
     * Draw the ghost that appears when the player is placing down a farm.
     * @param g2 The AWT graphics object.
     */
    public static void drawGhost(Graphics2D g2, Vector2D pos) {
        g2.setColor(new Color(0F, 0.1F, 1F, 0.7F));
        g2.fillRect((int)pos.x, (int)pos.y, 70, 70);
    }

    @Override
    public void tickLogic() {
        this.progress += 1;
    }
}
