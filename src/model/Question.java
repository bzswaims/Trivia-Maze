/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package model;

/**
 * Interface for the Question objects.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 *         Abby Yrojo
 *         Mahammod
 * @version 0.1
 */
public interface Question {

    void setQuestion(String theQuestion);

    void setCorrectAnswer(String theAnswer);

    void setID(int theID);

    String getQuestion();

    String getCorrectAnswer();

    int getID();

}
