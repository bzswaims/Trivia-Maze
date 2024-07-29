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

    String[] myIncorrectAnswers;

    public MutliQuestion(String theQuestion, String theCorrectAnswer) {
        super(theQuestion, theCorrectAnswer);

        myIncorrectAnswers = new String[3];
    }

    String[] getIncorrectAnswers() {
        return myIncorrectAnswers;
    }
}
