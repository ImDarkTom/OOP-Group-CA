package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.util.RenderUtils;

import java.awt.*;

public class Farm extends GameObject {
    private int progress = 0;

    private final Image asset;

    public Farm(int startX, int startY) {
        super(startX, startY, 50);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        asset = toolkit.getImage(getClass().getResource("/tom_game/farm.png"));
    }

    @Override
    public void onClicked() {}

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        RenderUtils.drawImage(g2, asset, pos);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2.drawString("Progress: " + progress, pos.x, pos.y);
    }

    @Override
    public void tickLogic() {
        this.progress += 1;
    }
}
