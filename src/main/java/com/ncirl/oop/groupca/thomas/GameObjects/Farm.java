package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.*;
import com.ncirl.oop.groupca.thomas.shared.FontManager;
import com.ncirl.oop.groupca.thomas.util.RenderUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Farms generate food and send deliveries to settlements.
 *
 * @author Thomas
 */
public class Farm extends GameObject {
    // Assets
    private static final Image ASSET = ImageLoader.load("/tom_game/farm.png");
    private static final Font DELIVERY_PROGRESS_FONT = FontManager.getFont(16, Font.BOLD);

    private ArrayList<Settlement> inRangeSettlements = new ArrayList<>();
    private int nextDeliveryProgress = 0;

    public Farm(int startX, int startY) {
        super(startX, startY, 50);

        refreshInRangeSettlements();
    }

    @Override
    public void onClicked() {
        GameLoop.pauseGame();

        if (JOptionPane.showConfirmDialog(
                TomGameWindow.gameWindow,
                "Are you sure you want to delete this farm? (Returns 50 materials)",
                "Confirm delete farm",
                JOptionPane.YES_NO_OPTION) == 0
        ) {
            GameObjectManager.removeGameObject(this);
            GameValues.adjustPlayerMaterials(50);
        }

        GameLoop.unpauseGame();
    }

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        RenderUtils.drawImage(g2, ASSET, pos);

        g2.setColor(Color.BLACK);
        g2.setFont(DELIVERY_PROGRESS_FONT);
        if (inRangeSettlements.isEmpty()) {
            g2.drawString("No settlements in range", pos.x, pos.y);
        } else {
            g2.drawString((int)(((float)nextDeliveryProgress/(float) GameValues.getDeliveryDelay()) * 100) + "%", pos.x, pos.y);
        }
    }

    @Override
    public void tickLogic() {
        if (inRangeSettlements.isEmpty()) {
            return;
        }

        this.nextDeliveryProgress += 1;

        if (nextDeliveryProgress >= GameValues.getDeliveryDelay()) {
            nextDeliveryProgress = 0;

            // Get most hungry in-range settlement;
            Settlement mostHungrySettlement = null;
            for (Settlement settlement : inRangeSettlements) {
                if (mostHungrySettlement == null) {
                    mostHungrySettlement = settlement;
                    continue;
                }

                if (settlement.getHunger() > mostHungrySettlement.getHunger()) {
                    mostHungrySettlement = settlement;
                }
            }

            if (mostHungrySettlement == null) {
                return;
            }

            GameObjectManager.addFoodDelivery(this, mostHungrySettlement);
        }
    }

    public void refreshInRangeSettlements() {
        ArrayList<Settlement> inRangeSettlements = new ArrayList<>();

        GameObjectManager.objectsOfType(Settlement.class).forEach(settlement -> {
            if (this.getPos().distance(settlement.getPos()) < GameValues.getDeliveryRange()) {
                inRangeSettlements.add(settlement);
            }
        });

        this.inRangeSettlements = inRangeSettlements;
    }

    public ArrayList<Settlement> getInRangeSettlements() {
        return inRangeSettlements;
    }
}
