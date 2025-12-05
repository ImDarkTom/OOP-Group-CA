package com.ncirl.oop.groupca.thomas.GameObjects;

import com.ncirl.oop.groupca.thomas.ImageLoader;
import com.ncirl.oop.groupca.thomas.GameObjectManager;
import com.ncirl.oop.groupca.thomas.GameValues;
import com.ncirl.oop.groupca.thomas.enums.SettlementType;
import com.ncirl.oop.groupca.thomas.shared.FontManager;
import com.ncirl.oop.groupca.thomas.util.RenderUtils;

import java.awt.*;

public class Settlement extends GameObject {
    // Assets
    // asset will be changed individually with each settlement
    private Image asset = ImageLoader.load("/tom_game/settlement.png");
    // static is better for optimisation, as each settlement instance won't need to re-create the same font.
    private static final Font HUNGER_FONT = FontManager.getFont(14);
    private static final Font UPGRADE_FONT = FontManager.getFont(12);

    private SettlementType settlementType = SettlementType.SETTLEMENT;

    private float hunger = 0;

    private int upgradeProgress = 0;
    private int giveMaterialTick = 0;

    // Level dependent
    private float hungerTickAmount = 0.4f;
    private int materialsPerSecond = 5;
    private int upgradeRequirement = 100;

    public Settlement(int startX, int startY) {
        super(startX, startY, 50);
    }

    @Override
    public void render(Graphics2D g2, Point mousePos) {
        RenderUtils.drawImage(g2, asset, pos);

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
    public void onClicked() {}

    private void upgrade() {
        switch (settlementType) {
            case SETTLEMENT -> {
                settlementType = SettlementType.TOWN;
                asset = ImageLoader.load("/tom_game/town.png");

                hungerTickAmount = 1f;
                materialsPerSecond = 10;
                upgradeRequirement = 150;

                GameValues.addScore(10);
            }
            case TOWN -> {
                settlementType = SettlementType.CITY;
                asset = ImageLoader.load("/tom_game/city.png");

                hungerTickAmount = 4f;
                materialsPerSecond = 20;
                upgradeRequirement = 250;

                GameValues.addScore(25);
            }
            case CITY -> {
                GameObjectManager.spawnSettlement();

                GameValues.addScore(50);
            }
            default -> {
                System.err.println("Error upgrading settlement: Invalid type.");
            }
        }
    }

    // Get/set
    public float getHunger() {
        return hunger;
    }

    public void decreaseHunger(int decreaseAmount) {
        float newHunger = hunger - decreaseAmount;

        if (newHunger < 1) {
            this.hunger = 0;
            // Bump upgrade progress by 10% of invested food/hunger decrease.
            upgradeProgress += (decreaseAmount / 10);

            if (upgradeProgress >= upgradeRequirement) {
                upgradeProgress = 0;
                upgrade();
            }
        } else {
            this.hunger = newHunger;
        }

    }
}
