package com.ncirl.oop.groupca.thomas.shared;

import com.ncirl.oop.groupca.alex.util.FileLoader;
import com.ncirl.oop.groupca.alex.util.Scores;

/**
 * Singleton ScoreManager to interface with the scores file.
 * <code>getInstance()</code> is used to interact with the single instance of the scores.
 */
public class ScoreManager {
    private static Scores scoresInstance;
    private static final String SCORE_FILENAME = "scores.ser";

    private static Scores getInstance() {
        if (scoresInstance == null) {
            // This will automatically create a blank file for us if needed
            scoresInstance = FileLoader.loadFromFile(SCORE_FILENAME, Scores.class);
        }

        return scoresInstance;
    }

    private static void save() {
        FileLoader.saveToFile(getInstance(), SCORE_FILENAME);
    }

    public static void setAlexScore(int alexInt) {
        getInstance().setAlexScore(alexInt);
        save();
    }

    public static void setThomasScore(int thomasInt) {
        getInstance().setThomasScore(thomasInt);
        save();
    }

    public static void setAntonioScore(int antonioInt) {
        getInstance().setAntonioScore(antonioInt);
        save();
    }

    public static void setAlexMsg(String alexMsg) {
        getInstance().setAlexMsg(alexMsg);
        save();
    }

    public static void setThomasMsg(String thomasMsg) {
        getInstance().setThomasMsg(thomasMsg);
        save();
    }

    public static void setAntonioMsg(String antonioMsg) {
        getInstance().setAntonioMsg(antonioMsg);
        save();
    }

    public static void resetScores() {
        FileLoader.saveToFile(new Scores(), SCORE_FILENAME);

        scoresInstance = null;
        getInstance();
    }

    //
    public static String getAlexScoreText() {
        return getInstance().getAlexScoreText();
    }

    public static String getAntonioScoreText() {
        return getInstance().getAntonioScoreText();
    }

    public static String getThomasScoreText() {
        return getInstance().getThomasScoreText();
    }

    public static String getFullScoreText() {
        return getInstance().getFullScoreText();
    }
}
