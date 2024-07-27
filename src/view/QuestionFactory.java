package view;

import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuestionFactory {

    SQLiteDataSource ds;
    String query;

    public QuestionFactory() {
        ds = null;
        //This is to shut up warnings, it will always be reset before use.
        //Its primary use is to make this code more readible, since we declare, and then just use the string
        //in the next line
        query = "null";

        createDataBase();
        populateDataBase();
    }

    public void createDataBase() {
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jbdc:sqlite:questions.sqlite");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        //Here I made a SUPER QUERY as I call it, it just builds 3 tables I hope. It has not been tested yet,
        //that is for later tonight or tomorrow
        query = "CREATE TABLE IF NOT EXISTS multiquestions ( " +
                "QUESTION TEXT NOT NULL, " +
                "CORRECTANSWER TEXT NOT NULL " +
                "INCORRECTANSWERONE TEXT NOT NULL " +
                "INCORRECTANSWERTWO TEXT NOT NULL " +
                "INCORRECTANSWERTHREE TEXT NOT NULL ) " +
                "CREATE TABLE IF NOT EXISTS truefalsequestions ( " +
                "QUESTION TEXT NOT NULL, " +
                "CORRECTANSWER TEXT NOT NULL )" +
                "CREATE TABLE IF NOT EXISTS shortquestions ( " +
                "QUESTION TEXT NOT NULL, " +
                "CORRECTANSWER TEXT NOT NULL )";
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    public void populateDataBase(){
        //going to be 30 questions
        //im looking at 10 multiple choice
        //10 t/f
        //10 short answer

        //The plan with these are, since we add all into 3 different tables, we can create the questions,
        //with the multiple choice questions, we can jumble the answers like they do on canvas when we take our quizes
        //Next up is the true false, it just stores the correct of the two (no reason to store the incorrect value)
        //And short answer just stores the correct answer to do a comparison to when the user inputs something.
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            //THESE ARE THE MULTIPLE CHOICE QUESTIONS\\

            //TODO: adjust to a real question
            query = "INSERT INTO multiquestions ( QUESTION, CORRECTANSWER, " +
                    "INCORRECTANSWERONE, INCORRECTANSWERTWO, " +
                    "INCORRECTANSWERTHREE ) VALUES ( 'Test Multiple Choice " +
                    "Question One?', 'Correct', 'Incorrect', 'Incorrect', 'Incorrect' )";

            int rv = stmt.executeUpdate( query );

            //TODO: adjust to a real question
            query = "INSERT INTO multiquestions ( QUESTION, CORRECTANSWER, " +
                    "INCORRECTANSWERONE, INCORRECTANSWERTWO, " +
                    "INCORRECTANSWERTHREE ) VALUES ( 'Test Multiple Choice " +
                    "Question Two?', 'Correct', 'Incorrect', 'Incorrect', 'Incorrect' )";

            rv = stmt.executeUpdate( query );

            //THESE ARE THE TRUE FALSE QUESTIONS\\

            //TODO: adjust to a real question
            query = "INSERT INTO truefalsequestions ( QUESTION, CORRECTANSWER, " +
                    "INCORRECTANSWERONE, INCORRECTANSWERTWO, " +
                    "INCORRECTANSWERTHREE ) VALUES ( 'Test Multiple Choice " +
                    "Question One?', 'False' )";

            rv = stmt.executeUpdate( query );

            //TODO: adjust to a real question
            query = "INSERT INTO truefalsequestions ( QUESTION, CORRECTANSWER, " +
                    "INCORRECTANSWERONE ) VALUES ( 'Test Multiple Choice " +
                    "Question Two?', 'True' )";

            rv = stmt.executeUpdate( query );

            //THESE ARE THE SHORT ANSWER QUESTIONS\\

            //TODO: adjust to a real question
            query = "INSERT INTO shortquestions ( QUESTION, CORRECTANSWER, " +
                    "INCORRECTANSWERONE, INCORRECTANSWERTWO, " +
                    "INCORRECTANSWERTHREE ) VALUES ( 'Test Multiple Choice " +
                    "Question One?', 'Correct' )";

            rv = stmt.executeUpdate( query );

            //TODO: adjust to a real question
            query = "INSERT INTO shortquestions ( QUESTION, CORRECTANSWER, " +
                    "INCORRECTANSWERONE, INCORRECTANSWERTWO, " +
                    "INCORRECTANSWERTHREE ) VALUES ( 'Test Multiple Choice " +
                    "Question Two?', 'Correct' )";

            rv = stmt.executeUpdate( query );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }
}
