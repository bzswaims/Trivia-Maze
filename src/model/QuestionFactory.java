/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package model;

import org.sqlite.SQLiteDataSource;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Takes in an int value representing a question type, and gives a random question of that type back.
 * If all questions of that type have been fetched from the database, it will restart selecting that question
 * type as though non have been fetched.
 * Types: 1 - Multiple choice question
 *        2 - True or false question
 *        3 - Short answer question
 *
 * @author Zane Swaims
 * @version 1.0
 */
public class QuestionFactory implements Serializable {

    @Serial
    private static final long serialVersionUID = 33322228666777999L;

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
    private static SQLiteDataSource ds;

    /**
     * arraylist to store IDs we have used already.
     */
    private final int[] myUsedQuestions;

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

        //This is +1 because our ID tags for questions start at 1 not 0.
        myUsedQuestions = new int[TOTAL_QUESTIONS + 1];
    }

    /**
     * Makes a question at random based on the type value fed in. It will cycle through all questions of the
     * specified type until all are selected and then will recycle through them as though none of that type
     * have been selected.
     * 1 - Multiple choice question
     * 2 - True false question
     * 3 - Short answer question
     * @param theType int value of the type of question to be returned.
     * @return AbstractQuestion made at random.
     */
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
        MultiQuestion tempQuestion = new MultiQuestion();

        String query = "SELECT * FROM Questions ORDER BY RANDOM()";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int questionID = rs.getInt("QuestionID");
                String questionText = rs.getString("QuestionText");
                int questionType = rs.getInt("QuestionType");

                if (questionType == theType && myUsedQuestions[questionID] == 0) {

                    tempQuestion.setQuestion(questionText);
                    tempQuestion.setID(questionID);
                    tempQuestion.setType(questionType);

                    myUsedQuestions[questionID] = 1;

                    break;
                }
            }

            if(tempQuestion.getID() == -1)
            {
                for(int i = 1; i <= TOTAL_MULTI_QUESTIONS; i++){
                    myUsedQuestions[i] = 0;
                }

                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    int questionID = rs.getInt("QuestionID");
                    String questionText = rs.getString("QuestionText");
                    int questionType = rs.getInt("QuestionType");

                    if (questionType == theType && myUsedQuestions[questionID] == 0) {

                        tempQuestion.setQuestion(questionText);
                        tempQuestion.setID(questionID);
                        tempQuestion.setType(questionType);

                        myUsedQuestions[questionID] = 1;

                        break;
                    }
                }
            }

            query = "SELECT * FROM Answers";

            rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                rs.getInt( "AnswerID" );
                int answerQuestionID = rs.getInt( "QuestionID" );
                String answerText = rs.getString( "AnswerText" );
                boolean isCorrect = rs.getBoolean( "IsCorrect" );

                if(answerQuestionID == tempQuestion.getID() && isCorrect) {
                    tempQuestion.setCorrectAnswer(answerText);
                }
                if(answerQuestionID == tempQuestion.getID() && !isCorrect) {
                    tempQuestion.addIncorrectAnswer(answerText);
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
    private ShortQuestion buildShortAnswer (final int theType) {
        ShortQuestion tempQuestion = new ShortQuestion();

        String query = "SELECT * FROM Questions ORDER BY RANDOM()";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int questionID = rs.getInt("QuestionID");
                String questionText = rs.getString("QuestionText");
                int questionType = rs.getInt("QuestionType");

                if (questionType == theType && myUsedQuestions[questionID] == 0) {

                    tempQuestion.setQuestion(questionText);
                    tempQuestion.setID(questionID);
                    tempQuestion.setType(questionType);

                    myUsedQuestions[questionID] = 1;

                    break;
                }
            }

        if(tempQuestion.getID() == -1)
        {
            for(int i = TOTAL_MULTI_QUESTIONS + TOTAL_TRUE_FALSE_QUESTIONS + 1; i <= TOTAL_MULTI_QUESTIONS + TOTAL_TRUE_FALSE_QUESTIONS + TOTAL_SHORT_QUESTIONS; i++){
                myUsedQuestions[i] = 0;
            }

            rs = stmt.executeQuery(query);

                while (rs.next()) {
                    int questionID = rs.getInt("QuestionID");
                    String questionText = rs.getString("QuestionText");
                    int questionType = rs.getInt("QuestionType");

                    if (questionType == theType && myUsedQuestions[questionID] == 0) {

                        tempQuestion.setQuestion(questionText);
                        tempQuestion.setID(questionID);
                        tempQuestion.setType(questionType);

                        myUsedQuestions[questionID] = 1;

                        break;
                    }
                }
        }

        query = "SELECT * FROM Answers";

            rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                rs.getInt( "AnswerID" );
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
     * Builds a true false answer question
     */
    private TrueFalseQuestion buildTrueFalseAnswer (final int theType) {
        TrueFalseQuestion tempQuestion = new TrueFalseQuestion();

        String query = "SELECT * FROM Questions ORDER BY RANDOM()";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int questionID = rs.getInt("QuestionID");
                String questionText = rs.getString("QuestionText");
                int questionType = rs.getInt("QuestionType");

                if (questionType == theType && myUsedQuestions[questionID] == 0) {

                    tempQuestion.setQuestion(questionText);
                    tempQuestion.setID(questionID);
                    tempQuestion.setType(questionType);

                    myUsedQuestions[questionID] = 1;

                    break;
                }
            }

        if(tempQuestion.getID() == -1)
        {
            for(int i =TOTAL_MULTI_QUESTIONS + 1; i <= TOTAL_MULTI_QUESTIONS + TOTAL_TRUE_FALSE_QUESTIONS; i++){
                myUsedQuestions[i] = 0;
            }

            rs = stmt.executeQuery(query);

                while (rs.next()) {
                    int questionID = rs.getInt("QuestionID");
                    String questionText = rs.getString("QuestionText");
                    int questionType = rs.getInt("QuestionType");

                    if (questionType == theType && myUsedQuestions[questionID] == 0) {

                        tempQuestion.setQuestion(questionText);
                        tempQuestion.setID(questionID);
                        tempQuestion.setType(questionType);

                        myUsedQuestions[questionID] = 1;

                        break;
                    }
                }
        }

        query = "SELECT * FROM Answers";

        rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                rs.getInt( "AnswerID" );
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