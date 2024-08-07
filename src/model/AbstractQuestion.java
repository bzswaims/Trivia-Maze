/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */


//potentially drop question interface

package model;

/**
 * Abstract data type for the Question objects.
 * The question proper is stored in a String, as is
 * the correct answer.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 *         Abby Yrojo
 *         Mahammod
 * @version 0.1
 */
public class AbstractQuestion implements Question {
    /**
     * Question.
     */
    private String myQuestion;

    /**
     * Correct answer.
     */
    private String myCorrectAnswer;

    /**
     * ID associated with the question
     */
    private int myID;

    /**
     * Constructs AbstractQuestion.
     */
    public AbstractQuestion() {
        myQuestion = "";
        myCorrectAnswer = "";
        myID = -1;
    }

    /**
     * Sets the question text.
     */
    public void setQuestion (final String theQuestionText) {
        myQuestion = theQuestionText;
    }

    /**
     * Sets the correct answer.
     * @param theString the Correct answer.
     */
    public void setCorrectAnswer(final String theString)
    {
        myCorrectAnswer = theString;
    }

    /**
     * Sets the Question ID. Might be superfluous data.
     * @param theID the number to associate with the Question.
     */
    public void setID (final int theID) {
        myID = theID;
    }

    /**
     * Returns question.
     * @return String.
     */
    @Override
    public String getQuestion() {
        return myQuestion;
    }

    /**
     * Returns correct answer.
     * @return String.
     */
    @Override
    public String getCorrectAnswer() {
        return myCorrectAnswer;
    }

    /**
     * Returns the question ID.
     * @return the Question ID.
     */
    public int getID() {
        return myID;
    }
}
