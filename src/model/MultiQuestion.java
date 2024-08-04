/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package model;

import java.util.ArrayList;

/**
 * Object for storing multiple choice questions.
 * The incorrect choices are stored in a String
 * to be fetched when needed.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 *         Abby Yrojo
 *         Mahammod
 * @version 0.1
 */
public class MultiQuestion extends AbstractQuestion {
    /**
     * Array of incorrect answers.
     */
    private final ArrayList<String> myIncorrectAnswers;

    //array list in case longer than 3 ^^

    /**
     * Constructs MultiQuestion.
     * @param theQuestion String question.
     * @param theCorrectAnswer String correct answer.
     */
    public MultiQuestion(final String theQuestion, final String theCorrectAnswer, final int theID, final int theType) {
        super(theQuestion, theCorrectAnswer, theID, theType);

        myIncorrectAnswers = new ArrayList<> ();
    }

    public MultiQuestion(final int theType) {
        super(theType);

        myIncorrectAnswers = new ArrayList<> ();
    }

    public void addIncorrectAnswer(final String theAnswer) {
        myIncorrectAnswers.add(theAnswer);
    }

    /**
     * Returns incorrect answers in an arraylist
     * @return ArrayList of answers.
     */
    public ArrayList<String> getIncorrectAnswers() {
        return myIncorrectAnswers;
    }
}
