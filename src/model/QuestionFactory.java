/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

//TODO actually make question factory work

package model;

import org.sqlite.SQLiteDataSource;

/**
 * Gathers questions from database.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class QuestionFactory {

    /**
     * Number of questions in database
     */
    int totalQuestions = 30;

    /**
     * The database.
     */
    SQLiteDataSource ds;

    /**
     * The questions.
     */
    Question[] myQuestions;

    /**
     * Constructor.
     */
    public QuestionFactory() {
        ds = null;
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:questions.sqlite");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        myQuestions = new Question[totalQuestions];
    }

    /**
     * Builds a question from the database
     */
    public void buildQuestion() {
        //Go into database
        //go into question table
        //Pull a question and its ID
        //go into answer table
        //pull all answers associated with question ID
        //Store the question into proper question type (1 multi, 2 t/f, 3 short)
        //add question into array
    }
}
