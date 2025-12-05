package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.ImageLoader;
import com.ncirl.oop.groupca.thomas.GameObjectManager;
import com.ncirl.oop.groupca.thomas.GameValues;
import com.ncirl.oop.groupca.thomas.util.RenderUtils;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * The object that appears when we start placing down a farm.
 *
 * @author Thomas
 */
public class FarmGhost extends GameObject {
    // Assets
    private static final Image ASSET = ImageLoader.load("/tom_game/farm.png");

    public FarmGhost() {
        super(0, 0, 70);

        RiverDrawer.showPlacementOverlay();
    }

    @Override
    public void onClicked() {
        if (RiverDrawer.isNearRiver(pos) && GameObjectManager.isNotColliding(pos)) {
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
                mousePos.x - (ASSET.getWidth(null) / 2),
                mousePos.y - (ASSET.getHeight(null) / 2)
        );

        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(2f));
        int deliveryRange = GameValues.getDeliveryRange();
        g2.draw(new Ellipse2D.Double(
                pos.x + 25 - deliveryRange,
                pos.y + 25 - deliveryRange,
                GameValues.getDeliveryRange() * 2,
                GameValues.getDeliveryRange() * 2));

        RenderUtils.drawImage(g2, ASSET, pos, 0.5f);
    }

    @Override
    public void tickLogic() {}
}
