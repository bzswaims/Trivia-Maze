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

        populateDataBase();
    }

    public void populateDataBase(){
        //database structure is as follows:
        //Questions table, has columns Question Text and Question Type
        //question type 1: multiple choice
        //question type 2: true/false
        //question type 3: short answer
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            //THESE ARE THE MULTIPLE CHOICE QUESTIONS\\

            //TODO: adjust to a real question
            query = "INSERT INTO Questions ( QuestionText, QuestionType, ) VALUES ( 'Test Multiple Choice " +
                    "Question One?', '1' )";

            int rv = stmt.executeUpdate( query );

            //THESE ARE THE TRUE FALSE QUESTIONS\\

            //TODO: adjust to a real question
            query = "INSERT INTO Questions ( QuestionText, QuestionType ) VALUES ( 'Test True False " +
                    "Question One?', '2' )";

            rv = stmt.executeUpdate( query );

            //THESE ARE THE SHORT ANSWER QUESTIONS\\

            //TODO: adjust to a real question
            query = "INSERT INTO Questions ( QuestionText, QuestionType ) VALUES ( 'Test Short Answer " +
                    "Question One?', '3' )";

            rv = stmt.executeUpdate( query );

            //POPULATAING ANSWERS\\

            //TODO: adjust to a real answer
            query = "INSERT INTO Answers ( QuestionID, AnswerText, " +
                    "IsCorrect ) VALUES ( '0', 'Test Multiple Choice " +
                    "Answer?', 'True' )";

            rv = stmt.executeUpdate( query );

            //TODO: adjust to a real answer
            query = "INSERT INTO Answers ( QuestionID, AnswerText, " +
                    "IsCorrect ) VALUES ( '0', 'Test Multiple Choice " +
                    "Answer True?', 'True' )";

            rv = stmt.executeUpdate( query );

            //TODO: adjust to a real answer
            query = "INSERT INTO Answers ( QuestionID, AnswerText, " +
                    "IsCorrect ) VALUES ( '0', 'Test Multiple Choice " +
                    "Answer False?', 'False' )";

            rv = stmt.executeUpdate( query );

            //TODO: adjust to a real answer
            query = "INSERT INTO Answers ( QuestionID, AnswerText, " +
                    "IsCorrect ) VALUES ( '1', 'True', 'False' )";

            rv = stmt.executeUpdate( query );

            //TODO: adjust to a real answer
            query = "INSERT INTO Answers ( QuestionID, AnswerText, " +
                    "IsCorrect ) VALUES ( '1', 'False', 'True' )";

            rv = stmt.executeUpdate( query );

            //TODO: adjust to a real answer
            query = "INSERT INTO Answers ( QuestionID, AnswerText, " +
                    "IsCorrect ) VALUES ( '2', 'Answer', 'True' )";

            rv = stmt.executeUpdate( query );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    public void buildQuestion() {
        //This needs to build questions
        //I need to build an object called question I imagine
        //Said object needs to contain the following:
        //the type of question it is
        //The question proper
        //the correct answer
        //the incorrect answers in the case of multiple choice

        //Probably store them all into an array to be randomly selected from

        //to do this, I plan to make an abstract data thingy for questions in general, then make an interfacey thingy
        //for the 3 different types of questions
        //then build all the questions that way, that is likely what I will do tomorrow, mostly following the same
        //series of steps I did in project 4 in 305. (the filter thingies)

        //I plan to store them in an array list or some kinda struct like that, as I intend to randomly select questions
        //from the array list thingy to attach to the doors (still need to work on that aspect of the game, sounds like
        //a next week mes pronblem)
    }
}
