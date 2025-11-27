package com.ncirl.oop.groupca.thomas.GameObjects;

import java.awt.*;

public class Farm extends GameObject {
    private int progress = 0;

    private final Image asset;

    public Farm(double startX, double startY) {
        super(startX, startY, 70);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        asset = toolkit.getImage(getClass().getResource("/tom_game/farm.png.png"));
    }

    @Override
    public void onClicked() {}

    @Override
    public void setup() {

    }

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        g2.setColor(Color.BLUE);
        g2.fillRect((int)pos.x, (int)pos.y, size, size);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2.drawString("Progress: " + progress, (int)pos.x, (int)pos.y);
    }

    @Override
    public void tickLogic() {
        this.progress += 1;
    }
}
