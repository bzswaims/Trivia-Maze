/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package model;

/**
 * Interface for the Question objects.
 *
 * @author Zane Swaims
 * @author Abbygaile Yrojo
 * @author Mohammed
 * @version 1.0
 */
public interface Question {

    /**
     * Mutator for the question text.
     * @param theQuestion The text for the question.
     */
    void setQuestion(final String theQuestion);

    /**
     * Mutator for the correct answer text.
     * @param theAnswer The text for the correct answer.
     */
    void setCorrectAnswer(final String theAnswer);

    /**
     * Mutator for setting the ID for this question.
     * @param theID The ID to be applied to this question.
     */
    void setID(final int theID);

    /**
     * Mutator for the type of question it is. Stored as an int value.
     * 1 - Multiple choice question
     * 2 - True false question
     * 3 - Short answer question
     * @param theType The type of question it is.
     */
    void setType(final int theType);

    /**
     * Accessor for retrieving the text of the question.
     * @return String of text for the question.
     */
    String getQuestion();

    /**
     * Accessor for retrieving the text of the correct answer.
     * @return String of text for the correct answer.
     */
    String getCorrectAnswer();

    /**
     * Accessor for retrieving the ID.
     * @return int for the ID.
     */
    int getID();

    /**
     * Accessor for retrieving the type value as an int.
     * 1 - Multiple choice question
     * 2 - True false question
     * 3 - Short answer question.
     * @return int for the type.
     */
    int getType();

}
