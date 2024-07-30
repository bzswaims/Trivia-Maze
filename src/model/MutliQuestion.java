/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package model;

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
public class MutliQuestion extends AbstractQuestion {
    /**
     * Array of incorrect answers.
     */
    String[] myIncorrectAnswers;

    /**
     * Constructs MultiQuestion.
     * @param theQuestion String question.
     * @param theCorrectAnswer String correct answer.
     */
    public MutliQuestion(String theQuestion, String theCorrectAnswer) {
        super(theQuestion, theCorrectAnswer);

        myIncorrectAnswers = new String[3];
    }

    /**
     * Returns incorrect answers.
     * @return String[].
     */
    String[] getIncorrectAnswers() {
        return myIncorrectAnswers;
    }
}
