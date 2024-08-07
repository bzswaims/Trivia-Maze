/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 * Gathers questions from database. All question types should be kept together.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class QuestionFactory {

    /**
     * constant for total number of questions in database.
     */
    static final int TOTAL_QUESTIONS = 30;

    /**
     * constant for total number of multiple choice questions in database.
     */
    static final int TOTAL_MULTI_QUESTIONS = 10;

    /**
     * constant for total number of true false questions in database.
     */
    static final int TOTAL_TRUE_FALSE_QUESTIONS = 10;

    /**
     * constant for total number of short answer questions in database.
     */
    static final int TOTAL_SHORT_QUESTIONS = 10;

    /**
     * The database.
     */
    private SQLiteDataSource ds;

    /**
     * random number generator.
     */
    Random myRand;

    /**
     * arraylist to store IDs we have used already.
     */
    int[] myUsedQuestions;

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

        myRand = new Random();

        //This is +1 because our ID tags for questions start at 1 not 0.
        myUsedQuestions = new int[TOTAL_QUESTIONS + 1];
    }

    public AbstractQuestion makeQuestion(final int theType) {

        AbstractQuestion tempQuestion = null;

        if(theType == 1) {
            tempQuestion = buildMultiQuestion(theType);
        } else if(theType == 2) {
            tempQuestion = buildTrueFalseAnswer(theType);
        } else if(theType == 3) {
            tempQuestion = buildShortAnswer(theType);
        }

        return tempQuestion;
    }

    /**
     * Builds a multiple choice question
     */
    private MultiQuestion buildMultiQuestion(final int theType) {

        MultiQuestion tempQuestion = (MultiQuestion) randomQuestion(theType);

            if(tempQuestion.getID() == -1)
            {
                //reflag all multi questions as having not been used.
                for(int i = 1; i <= TOTAL_MULTI_QUESTIONS; i++){
                    myUsedQuestions[i] = 0;
                }

                tempQuestion = (MultiQuestion) randomQuestion(theType);
            }

            String query = "SELECT * FROM Answers";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                int answerID = rs.getInt( "AnswerID" );
                int answerQuestionID = rs.getInt( "QuestionID" );
                String answerText = rs.getString( "AnswerText" );
                boolean isCorrect = rs.getBoolean( "IsCorrect" );

                if(answerQuestionID == tempQuestion.getID() && isCorrect) {
                    tempQuestion.setCorrectAnswer(answerText);
                }
                if(answerQuestionID == tempQuestion.getID() && !isCorrect) {
                    tempQuestion.addIncorrectAnswer(answerText);
                }

                //This continually cycles through even after finding all pertinent information. Maybe fix that.
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return tempQuestion;
    }

    /**
     * Pulls a random question of type desired from the database.
     */
    private AbstractQuestion randomQuestion(final int theType){
        AbstractQuestion tempQuestion = new AbstractQuestion();

        String query = "SELECT * FROM Questions ORDER BY RANDOM()";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int questionID = rs.getInt("QuestionID");
                String questionText = rs.getString("QuestionText");
                int questionType = rs.getInt("QuestionType");

                if (questionType == 1 && myUsedQuestions[questionID] == 0) {

                    tempQuestion.setQuestion(questionText);
                    tempQuestion.setID(questionID);

                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return tempQuestion;
    }

    /**
     * Builds a short answer question
     */
    private AbstractQuestion buildShortAnswer (final int theType) {
        ShortQuestion tempQuestion = (ShortQuestion) randomQuestion(theType);

        if(tempQuestion.getID() == -1)
        {
            //reflag all short answer questions as having not been used.
            for(int i = TOTAL_MULTI_QUESTIONS + TOTAL_TRUE_FALSE_QUESTIONS + 1; i <= TOTAL_MULTI_QUESTIONS + TOTAL_TRUE_FALSE_QUESTIONS + TOTAL_SHORT_QUESTIONS; i++){
                myUsedQuestions[i] = 0;
            }

            tempQuestion = (ShortQuestion) randomQuestion(theType);
        }

        String query = "SELECT * FROM Answers";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                int answerID = rs.getInt( "AnswerID" );
                int answerQuestionID = rs.getInt( "QuestionID" );
                String answerText = rs.getString( "AnswerText" );
                boolean isCorrect = rs.getBoolean( "IsCorrect" );

                if(answerQuestionID == tempQuestion.getID() && isCorrect) {
                    tempQuestion.setCorrectAnswer(answerText);

                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return tempQuestion;
    }

    /**
     * Builds a short answer question
     */
    private AbstractQuestion buildTrueFalseAnswer (final int theType) {
        ShortQuestion tempQuestion = (ShortQuestion) randomQuestion(theType);

        if(tempQuestion.getID() == -1)
        {
            //reflag all true false questions as having not been used.
            for(int i =TOTAL_MULTI_QUESTIONS + 1; i <= TOTAL_MULTI_QUESTIONS + TOTAL_TRUE_FALSE_QUESTIONS; i++){
                myUsedQuestions[i] = 0;
            }

            tempQuestion = (ShortQuestion) randomQuestion(theType);
        }

        String query = "SELECT * FROM Answers";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                int answerID = rs.getInt( "AnswerID" );
                int answerQuestionID = rs.getInt( "QuestionID" );
                String answerText = rs.getString( "AnswerText" );
                boolean isCorrect = rs.getBoolean( "IsCorrect" );

                if(answerQuestionID == tempQuestion.getID() && isCorrect) {
                    tempQuestion.setCorrectAnswer(answerText);

                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return tempQuestion;
    }
}