package com.ncirl.oop.groupca.thomas.util;

import com.ncirl.oop.groupca.thomas.GameValues;

public class UpgradeableValue {
    private String label;
    private int value;
    private int neededForUpgrade;
    private final int upgradeChange;
    private final int neededChange;

    public UpgradeableValue(String label, int defaultValue, int defaultNeededForUpgrade, int upgradeChange, int neededChange) {
        this.label = label;
        value = defaultValue;
        neededForUpgrade = defaultNeededForUpgrade;
        this.upgradeChange = upgradeChange;
        this.neededChange = neededChange;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label + " [" + neededForUpgrade + " mat.] (" + value + ")";
    }

    @Override
    public String toString() {
        // When the `showInputDialog` iterator stringifies each object, it gives this
        return getLabel();
    }

    public boolean upgrade() {
        if (GameValues.getPlayerMaterials() >= neededForUpgrade) {
            GameValues.adjustPlayerMaterials(-neededForUpgrade);

            this.value += upgradeChange;
            this.neededForUpgrade += neededChange;

            return true;
        } else {
            return false;
        }
    }
}
