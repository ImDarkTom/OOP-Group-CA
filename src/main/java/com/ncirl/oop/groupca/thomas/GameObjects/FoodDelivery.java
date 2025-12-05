package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.ImageLoader;
import com.ncirl.oop.groupca.thomas.GameValues;
import com.ncirl.oop.groupca.thomas.shared.CustomisationManager;
import com.ncirl.oop.groupca.thomas.util.RenderUtils;
import com.ncirl.oop.groupca.thomas.util.Renderable;
import com.ncirl.oop.groupca.thomas.util.Tickable;

import java.awt.*;

/**
 * The trucks that deliver food from the farms to the towns.
 *
 * @author Thomas
 */
public class FoodDelivery implements Renderable, Tickable {
    // Assets
    private static final String truckFilepath = "/tom_game/truck/" + CustomisationManager.getTruckStyle().getFileName();
    private static final Image ASSET = ImageLoader.load(truckFilepath);

    private final Point pos;
    private final Point from;
    private final Point to;

    private final float requiredProgress;
    private float progress = 0;

    private final Settlement targetSettlement;

    public boolean shouldRemove = false;

    public FoodDelivery(Farm fromFarm, Settlement toSettlement) {
        this.targetSettlement = toSettlement;

        int farmSize = fromFarm.getSize();
        this.from = new Point(
                fromFarm.getPos().x + (farmSize / 2),
                fromFarm.getPos().y + (farmSize / 2)
        );

        // Set the initial position to the `from` pos.
        this.pos = this.from.getLocation();

        int settlementSize = toSettlement.getSize();
        this.to = new Point(
                toSettlement.getPos().x + (settlementSize / 2),
                toSettlement.getPos().y + (settlementSize / 2)
        );

        // https://www.geeksforgeeks.org/maths/euclidean-distance/
        requiredProgress = (float) Math.sqrt(Math.pow(to.x - from.x, 2) + Math.pow(to.y - from.y, 2));
    }

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        RenderUtils.drawImage(g2, ASSET, pos);
    }

    @Override
    public void tickLogic() {
        // Prevent null exception
        if (requiredProgress == -1) return;

        if (progress >= requiredProgress) {
            targetSettlement.decreaseHunger(GameValues.getDeliveryHungerDecreaseAmount());

            // We use this since `GameState.foodDeliveries.remove(this)` has
            // the potential to throw a ConcurrentModificationException as
            // we might be iterating the list during removal. To fix this we
            // set a flag to have it removed in the tickLogic before the next
            // logic tick.
            shouldRemove = true;
            return;
        }

        progress += GameValues.getDeliverySpeed();

        float xPos = from.x + (to.x - from.x) * (progress / requiredProgress);
        float yPos = from.y + (to.y - from.y) * (progress / requiredProgress);

        pos.setLocation(xPos, yPos);
    }
}
