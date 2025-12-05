package com.ncirl.oop.groupca.thomas.shared;

import com.ncirl.oop.groupca.alex.util.FileLoader;
import com.ncirl.oop.groupca.antonio.util.Customisations;
import com.ncirl.oop.groupca.thomas.enums.TruckStyle;

import java.awt.*;

/**
 *
 * @author Thomas + Antonio + Alex
 */
public class CustomisationManager {
    private static Customisations customisationsInstance;
    private static final String CUSTOMISATION_FILENAME = "customisations.ser";

    public static Customisations getInstance() {
        if (customisationsInstance == null) {
            // This will automatically create a blank file for us
            customisationsInstance = FileLoader.loadFromFile(CUSTOMISATION_FILENAME, Customisations.class);
        }

        return customisationsInstance;
    }

    private static void save() {
        FileLoader.saveToFile(getInstance(), CUSTOMISATION_FILENAME);
    }

    // Setters
    public static void setBody(int bodyNum) {
        getInstance().setBody(bodyNum);
        save();
    }

    public static void setHat(int hat) {
        getInstance().setHat(hat);
        save();
    }

    public static void setTruckStyle(int truckStyle) {
        getInstance().setTruckStyleIndex(truckStyle);
        save();
    }

    // Getters
    public static int getHat() {
        return getInstance().getHat();
    }
    
    public static int getBodyNum() {
        return getInstance().getBodyNum();
    }

    public static Color getBodyCol() {
        return getInstance().getBodyCol();
    }

    public static int getTruckStyleIndex() {
        return getInstance().getTruckStyleIndex();
    }

    public static TruckStyle getTruckStyle() {
        return getInstance().getTruckStyle();
    }


    public static void resetCustomisations() {
        FileLoader.saveToFile(new Customisations(), CUSTOMISATION_FILENAME);

        customisationsInstance = null;
        getInstance();
    }
}
