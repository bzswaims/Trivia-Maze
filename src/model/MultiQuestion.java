/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package model;

import java.util.ArrayList;

/**
 * Object for storing multiple choice questions.
 * The incorrect choices are stored in a String array list
 * to be fetched when needed.
 *
 * @author Zane Swaims
 * @author Abbygaile Yrojo
 * @author Mohammed
 * @version 1.0
 */
public class MultiQuestion extends AbstractQuestion {
    /**
     * Array of incorrect answers.
     */
    private final ArrayList<String> myIncorrectAnswers;

    /**
     * Constructs MultiQuestion.
     */
    public MultiQuestion() {
        myIncorrectAnswers = new ArrayList<>();
    }

    /**
     * Adds answer to list of incorrect answers.
     * @param theAnswer
     */
    public void addIncorrectAnswer(final String theAnswer) {
        myIncorrectAnswers.add(theAnswer);
    }

    /**
     * Returns incorrect answers in an arraylist
     * @return ArrayList of answers.
     */
    public ArrayList<String> getIncorrectAnswers() {
        return (ArrayList<String>) myIncorrectAnswers.clone();
    }
}
