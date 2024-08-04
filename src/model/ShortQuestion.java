package model;

/**
 * Object for storing the short answer questions.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 *         Abby Yrojo
 *         Mahammod
 * @version 0.1
 */
public class ShortQuestion extends AbstractQuestion{
    public ShortQuestion(final String theQuestion, final String theCorrectAnswer, final int theID, final int theType) {
        super(theQuestion, theCorrectAnswer, theID, theType);
    }

    public ShortQuestion(final int theType) {
        super(theType);
    }
}
