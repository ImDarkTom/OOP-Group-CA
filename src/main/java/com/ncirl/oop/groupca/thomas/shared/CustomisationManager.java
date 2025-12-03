package com.ncirl.oop.groupca.thomas.shared;

import com.ncirl.oop.groupca.alex.util.FileLoader;
import com.ncirl.oop.groupca.antonio.util.Customisations;
import com.ncirl.oop.groupca.thomas.ImageLoader;
import com.ncirl.oop.groupca.thomas.util.RenderUtils;
import com.ncirl.oop.groupca.thomas.GameValues;

import java.awt.*;
/**
 *
 * @author anton
 */
public class CustomisationManager {
    // "/tom_game/farm.png"
    //temp Asset and image values change to better variables for hats
    private static Point pos;
    private static String imageOne = "/tom_game/farm.png";
    private static Image ASSET = ImageLoader.load(imageOne);

    private static Customisations customisationsInstance;
    private static final String CUSTOMISATION_FILENAME = "customisations.ser";

    public static Customisations getInstance() {
        if (customisationsInstance == null) {
            // This will automatically create a blank file for us
            customisationsInstance = FileLoader.loadFromFile(CUSTOMISATION_FILENAME, Customisations.class);
        }

        return customisationsInstance;
    }



    private static void loadImage() {

        FileLoader.saveToFile(getInstance(), CUSTOMISATION_FILENAME);
    }

    // Setters
    public static void setBody(int bodyNum) {
        getInstance().setBody(bodyNum);
        // loadImage();
    }
    public static void setHat(int hat) {
        getInstance().setHat(hat);
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
//    public static void resetScores() {
//        FileLoader.saveToFile(new Scores(), SCORE_FILENAME);
//
//        scoresInstance = null;
//        getInstance();
//    }
//
//    //
//    public static String getAlexScoreText() {
//        return getInstance().getAlexScoreText();
//    }
//
//    public static String getAntonioScoreText() {
//        return getInstance().getAntonioScoreText();
//    }
//
//    public static String getThomasScoreText() {
//        return getInstance().getThomasScoreText();
//    }
//
//    public static String getFullScoreText() {
//        return getInstance().getFullScoreText();
//    }

}
