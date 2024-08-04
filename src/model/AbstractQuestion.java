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
    private final String myQuestion;

    /**
     * Correct answer.
     */
    private String myCorrectAnswer;

    /**
     * ID associated with the question
     */
    private final int myID;

    /**
     * The type of question it is, 1 is multi, 2 is true/false, 3 is short answer
     */
    private final int myType;

    /**
     * Constructs AbstractQuestion.
     * @param theQuestion String question.
     * @param theCorrectAnswer String correct answer.
     */
    public AbstractQuestion(final String theQuestion, final String theCorrectAnswer, final int theID, final int theType) {
        myQuestion = theQuestion;
        myCorrectAnswer = theCorrectAnswer;
        myID = theID;
        myType = theType;
    }

    public AbstractQuestion(final int theType) {
        //default constructor
        myQuestion = null;
        myCorrectAnswer = null;
        myID = -1;
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

    @Override
    public void addIncorrectAnswer(String theString) {
        //Place holder for multiple choice questions.
        //TODO: need to find a better way.
    }

    public int getType() {
        return myType;
    }

    public void setCorrectAnswer(final String theString)
    {
        myCorrectAnswer = theString;
    }
}
