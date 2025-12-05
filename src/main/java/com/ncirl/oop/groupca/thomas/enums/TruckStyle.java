package com.ncirl.oop.groupca.thomas.enums;

/**
 * @author Thomas
 */
public enum TruckStyle {
    DEFAULT("Default", "default.png"),
    CHRISTMAS("Christmas", "christmas.png");

    private final String label;
    private final String fileName;

    TruckStyle(String label, String fileName) {
        this.label = label;
        this.fileName = fileName;
    }

    public String getLabel() {
        return label;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return label;
    }
}