package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.util.Vector2D;

import javax.swing.*;
import java.awt.*;

public class FarmGhost extends GameObject {
    private final Image asset;

    private String errorText;

    public FarmGhost(double startX, double startY) {
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
        this.pos = new Vector2D(
                mousePos.x - ((double) asset.getWidth(null) / 2),
                mousePos.y - ((double) asset.getHeight(null) / 2)
        );

        int centeredX = (int)this.pos.x;
        int centeredY = (int)this.pos.y;

        g2.drawImage(asset, centeredX, centeredY, null);
    }

    @Override
    public void tickLogic() {}
}
