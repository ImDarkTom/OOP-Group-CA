package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.GameObjectManager;
import com.ncirl.oop.groupca.thomas.GameValues;

import java.awt.*;

enum SettlementType {
    SETTLEMENT,
    TOWN,
    CITY
}

public class Settlement extends GameObject {
    private SettlementType type = SettlementType.SETTLEMENT;

    private float hunger = 0;

    private int upgradeProgress = 0;
    private int giveMaterialTick = 0;

    // Level dependent
    private float hungerTickAmount = 0.4f;
    private int materialsPerSecond = 5;
    private int upgradeRequirement = 100;

    // asset-related
    private Image asset;
    // static is better for optimisation, as each settlement instance won't need to re-create the same font.
    private static final Font HUNGER_FONT = new Font("SansSerif", Font.PLAIN, 14);
    private static final Font UPGRADE_FONT = new Font("SansSerif", Font.PLAIN, 12);

    public Settlement(int startX, int startY) {
        super(startX, startY, 50);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        asset = toolkit.getImage(getClass().getResource("/tom_game/settlement.png"));

        // When we add a new settlement, refresh every farm's inRangeSettlements
        // to account for this new settlement.
        GameObjectManager.objectsOfType(Farm.class).forEach(Farm::refreshInRangeSettlements);
    }

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        g2.setColor(Color.RED);
        g2.drawImage(asset, pos.x, pos.y, null);

        if (hunger > 200) {
            g2.setColor(Color.RED);
        } else if (hunger > 50) {
            g2.setColor(Color.ORANGE);
        } else if (hunger > 20) {
            g2.setColor(Color.YELLOW);
        } else {
            g2.setColor(Color.GREEN);
        }
        g2.setFont(HUNGER_FONT);
        g2.drawString("Hunger: " + (int)hunger, pos.x, pos.y - 14);

        if (upgradeProgress > 0) {
            g2.setColor(Color.GREEN);
            g2.setFont(UPGRADE_FONT);

            int upgradePercent = (int)(((float)upgradeProgress / (float)upgradeRequirement) * 100);
            g2.drawString("Upgrading: " + upgradePercent + "%", pos.x, pos.y);
        }
    }

    @Override
    public void tickLogic() {
        this.hunger += hungerTickAmount;

        giveMaterialTick++;

        if (giveMaterialTick == 10) {
            giveMaterialTick = 0;
            GameValues.adjustPlayerMaterials(materialsPerSecond);
        }
    }

    @Override
    public void onClicked() {
        System.out.println("clicked settlement");
    }

    public float getHunger() {
        return hunger;
    }

    public void decreaseHunger(int decreaseAmount) {
        float newHunger = hunger - decreaseAmount;

        if (newHunger < 1) {
            // Bump upgrade progress by 10% of invested food/hunger decrease.
            upgradeProgress += (decreaseAmount / 10);
            this.hunger = 0;

            if (upgradeProgress >= upgradeRequirement) {
                upgradeProgress = 0;
                upgrade();
            }

            return;
        }

        this.hunger = newHunger;
    }

    private void upgrade() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        if (type == SettlementType.SETTLEMENT) {
            type = SettlementType.TOWN;
            asset = toolkit.getImage(getClass().getResource("/tom_game/town.png"));

            hungerTickAmount = 1f;
            materialsPerSecond = 10;
            upgradeRequirement = 150;

            GameValues.addScore(10);
        } else if (type == SettlementType.TOWN) {
            type = SettlementType.CITY;
            asset = toolkit.getImage(getClass().getResource("/tom_game/city.png"));

            hungerTickAmount = 4f;
            materialsPerSecond = 20;
            upgradeRequirement = 250;

            GameValues.addScore(25);
        } else if (type == SettlementType.CITY) {
            GameObjectManager.spawnSettlement();

            GameValues.addScore(50);
        }
    }
}
