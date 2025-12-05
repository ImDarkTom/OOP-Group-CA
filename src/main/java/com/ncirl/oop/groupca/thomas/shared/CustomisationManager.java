package com.ncirl.oop.groupca.thomas.shared;

import com.ncirl.oop.groupca.alex.util.FileLoader;
import com.ncirl.oop.groupca.antonio.util.Customisations;
import com.ncirl.oop.groupca.thomas.util.TruckStyle;

import java.awt.*;
/**
 *
 * @author anton
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

    // Setters
    public static void setBody(int bodyNum) {
        getInstance().setBody(bodyNum);
        // loadImage();
    }

    public static void setHat(int hat) {
        getInstance().setHat(hat);
    }

    public static void setTruckStyle(int truckStyle) {
        getInstance().setTruckStyleIndex(truckStyle);
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
