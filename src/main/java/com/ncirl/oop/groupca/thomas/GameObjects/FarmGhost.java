package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.GameObjectManager;
import com.ncirl.oop.groupca.thomas.GameValues;
import com.ncirl.oop.groupca.thomas.util.RenderUtils;

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
        RiverDrawer.showPlacementOverlay();
    }

    @Override
    public void onClicked() {
        if (RiverDrawer.isNearRiver(pos) && GameObjectManager.checkCollision(pos)) {
            RiverDrawer.hidePlacementOverlay();
            GameObjectManager.removeGameObject(this);

            GameValues.adjustPlayerMaterials(-GameValues.FARM_PRICE);
            GameObjectManager.addGameObject(new Farm(pos.x, pos.y));
        }
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
        int deliveryRange = GameValues.getDeliveryRange();
        g2.draw(new Ellipse2D.Double(
                pos.x + 25 - deliveryRange,
                pos.y + 25 - deliveryRange,
                GameValues.getDeliveryRange() * 2,
                GameValues.getDeliveryRange() * 2));

        RenderUtils.drawImage(g2, asset, pos, 0.5f);
    }

    @Override
    public void tickLogic() {}
}
