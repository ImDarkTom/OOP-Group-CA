package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.GameState;
import com.ncirl.oop.groupca.thomas.util.RenderUtils;

import java.awt.*;
import java.util.ArrayList;

public class Farm extends GameObject {
    ArrayList<Settlement> inRangeSettlements = new ArrayList<>();

    private int totalDeliveryProgress = 50;
    private int nextDeliveryProgress = 0;

    private int deliveryRange = 200;

    private final Image asset;

    public Farm(int startX, int startY) {
        super(startX, startY, 50);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        asset = toolkit.getImage(getClass().getResource("/tom_game/farm.png"));

        refreshInRangeSettlements();
    }

    @Override
    public void onClicked() {}

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        RenderUtils.drawImage(g2, asset, pos);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2.drawString((int)(((float)nextDeliveryProgress/(float)totalDeliveryProgress) * 100) + "%", pos.x, pos.y);
    }

    @Override
    public void tickLogic() {
        this.nextDeliveryProgress += 1;

        if (nextDeliveryProgress >= totalDeliveryProgress) {
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

            GameState.addFoodDelivery(this, mostHungrySettlement);
        }
    }

    public void refreshInRangeSettlements() {
        ArrayList<Settlement> inRangeSettlements = new ArrayList<>();

        GameState.objectsOfType(Settlement.class).forEach(settlement -> {
            if (this.getPos().distance(settlement.getPos()) < deliveryRange) {
                inRangeSettlements.add(settlement);
            }
        });

        this.inRangeSettlements = inRangeSettlements;
    }

    public ArrayList<Settlement> getInRangeSettlements() {
        return inRangeSettlements;
    }
}
