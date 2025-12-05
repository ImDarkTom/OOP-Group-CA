package com.ncirl.oop.groupca.thomas.enums;

/**
 * @author Thomas
 */
public enum TruckStyle {
    DEFAULT("Default", "default.png", 0),
    CHRISTMAS("Christmas", "christmas.png", 0),
    GOLDEN("Golden", "golden.png", 900);

    private final String label;
    private final String fileName;
    private final int requiredScore;

    TruckStyle(String label, String fileName, int requiredScore) {
        this.label = label;
        this.fileName = fileName;
        this.requiredScore = requiredScore;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        if (requiredScore > 0) {
            return label + " (" + requiredScore + " points)";
        }

        return label;
    }
}