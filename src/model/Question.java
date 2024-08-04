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

    String getQuestion();

    String getCorrectAnswer();

    int getID();

    void addIncorrectAnswer(String theString);
}
