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


    public static void setAlexBody(Color alexBody) {
        getInstance().setAlexBody(alexBody);
        // loadImage();
    }

    public static void setAlexHead(Color alexHead) {
        getInstance().setAlexHead(alexHead);
        // loadImage();
    }

    public static void setAlexWheat(Color alexWheat) {
        getInstance().setAlexWheat(alexWheat);
        // loadImage();
    }

    public static void setAlexOnion(Color alexOnion) {

        getInstance().setAlexOnion(alexOnion);
        // loadImage();
    }

    public static int getHat() {
        return getInstance().getHat();
    }
    
    public static Color getAlexBody() {
        return getInstance().getAlexBody();
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
