package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.util.RenderUtils;
import com.ncirl.oop.groupca.thomas.util.Renderable;
import com.ncirl.oop.groupca.thomas.util.Tickable;

import java.awt.*;

public class FoodDelivery implements Renderable, Tickable {
    int progress = 0;

    private Point from;
    private Point to;
    private Settlement target;

    private Point ourPos;
    public boolean shouldRemove = false;

    private Image asset;

    public FoodDelivery(Point from, Point to, Settlement target) {
        this.from = from;
        this.to = to;
        this.target = target;

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        asset = toolkit.getImage(getClass().getResource("/tom_game/truck.png"));
    }

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        RenderUtils.drawImage(g2, asset, ourPos);

        g2.setColor(Color.black);
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        g2.drawString(progress + "", ourPos.x, ourPos.y);
    }

    @Override
    public void tickLogic() {
        if (progress >= 100) {
            target.setHunger(target.getHunger() - 100);

            // We use this since `GameState.foodDeliveries.remove(this)` has
            // the potential to throw a ConcurrentModificationException as
            // we might be iterating the list during removal. To fix this we
            // set a flag to have it removed in the tickLogic before the next
            // logic tick.
            shouldRemove = true;
            return;
        }

        progress += 5;
        System.out.println(progress);

        ourPos = new Point(100, 100);
    }
}
