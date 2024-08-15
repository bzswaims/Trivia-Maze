/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package model;

import java.io.Serializable;

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

    void setType(int theType);

    String getQuestion();

    String getCorrectAnswer();

    int getID();

    int getType();

}
