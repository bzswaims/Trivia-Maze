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
     * Value associated with what type of question it is.
     */
    private int myType;

    /**
     * Constructs AbstractQuestion.
     */
    public AbstractQuestion() {
        myQuestion = "";
        myCorrectAnswer = "";
        myID = -1;
        myType = -1;
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
     * Sets the question type value:
     * 1 multiple choice
     * 2 true or false
     * 3 short answer
     */
    public void setType(final int theType) {
        myType = theType;
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

    /**
     * Returns the question type.
     * @return the question type.
     */
    public int getType() {
        return myType;
    }
}
