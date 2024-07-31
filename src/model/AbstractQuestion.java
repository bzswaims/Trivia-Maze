/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */


//potentially drop quesiton interface

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
     * Constructs AbstractQuestion.
     * @param theQuestion String question.
     * @param theCorrectAnswer String correct answer.
     */
    public AbstractQuestion(String theQuestion, String theCorrectAnswer) {
        myQuestion = theQuestion;
        myCorrectAnswer = theCorrectAnswer;
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
}
