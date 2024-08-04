//TODO there is probably a way to utilize boolean to store stuff in here

package model;

/**
 * Object for storing the true and false questions.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 *         Abby Yrojo
 *         Mahammod
 * @version 0.1
 */
public class TrueFalseQuestion extends AbstractQuestion {
    public TrueFalseQuestion(final String theQuestion, final String theCorrectAnswer, final int theID, final int theType) {
        super(theQuestion, theCorrectAnswer, theID, theType);
    }
    public TrueFalseQuestion(final int theType) {
        super(theType);
    }
}
