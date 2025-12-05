package com.ncirl.oop.groupca.thomas.util;

import com.ncirl.oop.groupca.thomas.GameValues;

/**
 * @author Thomas
 */
public class UpgradeableValue {
    private final String label;
    private final String description;

    // Current state
    private int value;
    private int neededForUpgrade;

    // Immutable values for resetting on exit
    private final int defaultValue;
    private final int defaultNeededForUpgrade;

    // Upgrade-related
    private final int upgradeChange;
    private final int neededChange;
    private Runnable onUpgrade;

    public UpgradeableValue(String label, String description, int defaultValue, int defaultNeededForUpgrade, int upgradeChange, int neededChange) {
        this(label, description, defaultValue, defaultNeededForUpgrade, upgradeChange, neededChange, null);
    }

    public UpgradeableValue(String label, String description, int defaultValue, int defaultNeededForUpgrade, int upgradeChange, int neededChange, Runnable onUpgrade) {
        this.label = label;
        this.description = description;

        this.defaultValue = defaultValue;
        this.defaultNeededForUpgrade = defaultNeededForUpgrade;

        value = defaultValue;
        neededForUpgrade = defaultNeededForUpgrade;

        this.upgradeChange = upgradeChange;
        this.neededChange = neededChange;
        this.onUpgrade = onUpgrade;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label + " [" + neededForUpgrade + " mat.] (" + value + ")";
    }

    public String getDescription() {
        return description;
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

            if (this.onUpgrade != null) {
                this.onUpgrade.run();
            }

            return true;
        } else {
            return false;
        }
    }

    public void reset() {
        this.value = this.defaultValue;
        this.neededForUpgrade = this.defaultNeededForUpgrade;

        // To update any values that might need to be refreshed e.g. river visual placement distance
        if (this.onUpgrade != null) {
            this.onUpgrade.run();
        }
    }
}
