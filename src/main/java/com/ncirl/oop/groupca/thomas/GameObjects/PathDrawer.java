package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.GameObjectManager;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Thomas
 */
public class PathDrawer extends GameObject {
    public PathDrawer() {
        super(0, 0, 0);
    }

    @Override
    public void onClicked() {}

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        g2.setColor(new Color(133, 77, 54));
        g2.setStroke(new BasicStroke(12f));

        GameObjectManager.objectsOfType(Farm.class).forEach(farm -> {
            ArrayList<Settlement> settlementsInRange = farm.getInRangeSettlements();

            for (Settlement settlement : settlementsInRange) {
                g2.drawLine(
                        farm.getPos().x + (farm.getSize() / 2),
                        farm.getPos().y + (farm.getSize() / 2),
                        settlement.getPos().x + (settlement.getSize() / 2),
                        settlement.getPos().y + (settlement.getSize() / 2)
                );
            }
        });
    }

    @Override
    public void tickLogic() {}
}
