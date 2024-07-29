//TODO actually make question factory work

package view;

import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Question;

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
