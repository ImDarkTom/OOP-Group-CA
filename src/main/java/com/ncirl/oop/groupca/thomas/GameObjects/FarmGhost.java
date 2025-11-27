package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.GameState;
import com.ncirl.oop.groupca.thomas.util.RenderUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * The object that appears when we start placing down a farm.
 */
public class FarmGhost extends GameObject {
    private final Image asset;

    public FarmGhost(int startX, int startY) {
        super(startX, startY, 70);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        asset = toolkit.getImage(getClass().getResource("/tom_game/farm.png"));
    }

    @Override
    public void onClicked() {
        GameState.removeGameObject(this);
        GameState.addGameObject(new Farm(pos.x, pos.y));
    }

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        // Set pos to mouse coordinates (adjusted to put the object in the middle)
        this.pos = new Point(
                mousePos.x - (asset.getWidth(null) / 2),
                mousePos.y - (asset.getHeight(null) / 2)
        );

        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(2f));
        g2.draw(new Ellipse2D.Double(pos.x - 175, pos.y - 175, 350, 350));

        RenderUtils.drawImage(g2, asset, pos, 0.5f);
    }

    @Override
    public void tickLogic() {}
}
