package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.GameValues;
import com.ncirl.oop.groupca.thomas.util.RenderUtils;
import com.ncirl.oop.groupca.thomas.util.Renderable;
import com.ncirl.oop.groupca.thomas.util.Tickable;

import java.awt.*;

public class FoodDelivery implements Renderable, Tickable {
    int progress = 0;

    private final Settlement targetSettlement;

    private final Point from;
    private Point to;

    private Point ourPos;
    public boolean shouldRemove = false;

    private final Image asset;

    public FoodDelivery(Farm fromFarm, Settlement toSettlement) {
        this.targetSettlement = toSettlement;

        int farmSize = fromFarm.getSize();
        this.from = new Point(
                fromFarm.getPos().x + (farmSize / 2),
                fromFarm.getPos().y + (farmSize / 2)
        );

        int settlementSize = toSettlement.getSize();
        this.to = toSettlement.getPos();
        this.to = new Point(
                toSettlement.getPos().x + (settlementSize / 2),
                toSettlement.getPos().y + (settlementSize / 2)
        );

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        asset = toolkit.getImage(getClass().getResource("/tom_game/truck.png"));
    }

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        RenderUtils.drawImage(g2, asset, ourPos);
    }

    @Override
    public void tickLogic() {
        if (progress >= 100) {
            targetSettlement.decreaseHunger(GameValues.hungerDecreaseAmount);

            // We use this since `GameState.foodDeliveries.remove(this)` has
            // the potential to throw a ConcurrentModificationException as
            // we might be iterating the list during removal. To fix this we
            // set a flag to have it removed in the tickLogic before the next
            // logic tick.
            shouldRemove = true;
            return;
        }

        progress += 1;

        float xPos = from.x + (to.x - from.x) * ((float) progress / 100);
        float yPos = from.y + (to.y - from.y) * ((float) progress / 100);

        ourPos = new Point(
                (int)xPos,
                (int)yPos
        );
    }
}
