/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Abstract data type for the Question objects.
 * The question proper is stored in a String, as is
 * the correct answer.
 *
 * @author Zane Swaims
 * @author Abbygaile Yrojo
 * @author Mahammod
 * @version 1.0
 */
public class AbstractQuestion implements Serializable, Question {
    @Serial
    private static final long serialVersionUID = 7788337777844466666L;

    /**
     * The question text.
     */
    private String myQuestion;

    /**
     * The correct answer.
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
     * Constructs an AbstractQuestion.
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
     *
     * @param theString the Correct answer.
     */
    public void setCorrectAnswer(final String theString)
    {
        myCorrectAnswer = theString;
    }

    /**
     * Sets the Question ID.
     *
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
