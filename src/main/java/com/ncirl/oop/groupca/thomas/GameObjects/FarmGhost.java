package com.ncirl.oop.groupca.thomas.GameObjects;

import javax.swing.*;
import java.awt.*;

public class FarmGhost extends GameObject {
    private final Image asset;

    public FarmGhost(int startX, int startY) {
        super(startX, startY, 70);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        asset = toolkit.getImage(getClass().getResource("/tom_game/farm.png"));
    }

    @Override
    public void onClicked() {
        System.out.println("farm ghost clicked");
    }

    @Override
    public void setup() {}

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        this.pos = new Point(
                mousePos.x - (asset.getWidth(null) / 2),
                mousePos.y - (asset.getHeight(null) / 2)
        );

        g2.drawImage(asset, pos.x, pos.y, null);
    }

    @Override
    public void tickLogic() {}
}
