/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

//TODO actually make question factory work

package model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//TODO: make it properly, mostly toying with database since I have never worked with one before.

/**
 * Gathers questions from database.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class QuestionFactory {

    /**
     * The database.
     */
    private SQLiteDataSource ds;

    /**
     * The questions.
     */
    ArrayList<AbstractQuestion> myQuestions;

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

        //call build question

        myQuestions = new ArrayList<>();

        buildQuestion();
    }

    /**
     * Builds a question from the database
     */
    private void buildQuestion() {
        //Go into database
        //go into question table
        //Pull a question and its ID
        //go into answer table
        //pull all answers associated with question ID
        //Store the question into proper question type (1 multi, 2 t/f, 3 short)
        //add question into array

        //now ive pulled data from the database, I need to store it into my question array list

        String query = "SELECT * FROM Questions";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                int questionID = rs.getInt( "QuestionID" );
                String questionText = rs.getString( "QuestionText" );
                int questionType = rs.getInt( "QuestionType" );

                switch(questionType)
                {
                    case 1:
                        MultiQuestion questm = new MultiQuestion(questionText, "", questionID, questionType);
                        myQuestions.add(questm);
                    case 2:
                        TrueFalseQuestion questtf = new TrueFalseQuestion(questionText, "", questionID, questionType);
                        myQuestions.add(questtf);
                    case 3:
                        ShortQuestion quests = new ShortQuestion(questionText, "", questionID, questionType);
                        myQuestions.add(quests);
                }
            }

            query = "SELECT * FROM Answers";

            rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                //we don't use this, but we need it to cycle past the answerID, I stored it in case we might want to use it
                //if we end up not, we can delete the variable and just have the statement rs.getint
                int answerID = rs.getInt( "AnswerID" );
                int questionID = rs.getInt( "QuestionID" );
                String answerText = rs.getString( "AnswerText" );
                boolean isCorrect = rs.getBoolean( "IsCorrect" );

                int i = 0;
                while(true) {
                    if(myQuestions.get(i).getID() == questionID) {
                        break;
                    }
                }
                if(isCorrect) {
                    myQuestions.get(i).setCorrectAnswer(answerText);
                }

                if(myQuestions.get(i).getType() == 1 && !isCorrect) {
                    myQuestions.get(i).addIncorrectAnswer(answerText);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Question getQuestion(int Type) {
        return null;
    }
    //need a method to spit out a random question, a random multi question, a random TF question, and one for random short
}
