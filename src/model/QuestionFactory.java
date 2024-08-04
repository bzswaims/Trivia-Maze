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
import java.util.Random;

//TODO: make it properly, mostly toying with database since I have never worked with one before.

/**
 * Gathers questions from database. All question types should be kept together.
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
     * random number generator
     */
    private final Random myRand;

    /**
     * Amount of multiple choice questions
     */
    private int myMultiAmt;

    /**
     * Amount of true false choice questions
     */
    private int myTFAmt;

    /**
     * Amount of short choice questions
     */
    private int myShortAmt;

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

        myMultiAmt = 0;
        myTFAmt = 0;
        myShortAmt = 0;

        myQuestions = new ArrayList<>();

        myRand = new Random();

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
                        myMultiAmt++;
                    case 2:
                        TrueFalseQuestion questtf = new TrueFalseQuestion(questionText, "", questionID, questionType);
                        myQuestions.add(questtf);
                        myTFAmt++;
                    case 3:
                        ShortQuestion quests = new ShortQuestion(questionText, "", questionID, questionType);
                        myQuestions.add(quests);
                        myShortAmt++;
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

    /**
     * This will return a question, fully built, from the database of the type
     * asked for
     * @param theType
     * @return
     */
    public Question getQuestion(final int theType) {
        //1 is multi, 2 is tf, 3 is short, im thinking 4 is just any.

        //my plan:
        //Step one: Generate random number from 0 to 29.
        //Step two: Mod random number by 3 (or just random number generate up to 3 since we have 3 question types)
        //Step three: step through the list of questions start at 0, and go up by the random number until we find the
        //            question type we need
        //Step four: if we reach the end or past the end, off set by 1 and restart the process.
        //Step five: if we find it, return the question, if we do not find it (IE the mod number is less than the offset)
        //           return null.

        //new plan, I have added amounts of each question in the array list, they are stored in the following order:
        //multi, then tf, then short answer.

        //TODO: lastly, I need a way to flag a question as having been pulled from the list once.
        // So we do not reuse questions, I have steps to figure out one that has not been used yet, but need a flag.
        // I will likely add a flag to the question, boolean ifUsed and check that one, set them all as false
        // then if all have been used, I do not know what to do from there

        int randomQuestion;

        AbstractQuestion tempQuestion;

        switch(theType) {
            case 1:
                randomQuestion = myRand.nextInt(myMultiAmt);
                tempQuestion = myQuestions.get(randomQuestion);
            case 2:
                randomQuestion = myRand.nextInt(myTFAmt);
                tempQuestion = myQuestions.get(randomQuestion + myMultiAmt);
            case 3:
                randomQuestion = myRand.nextInt(myShortAmt);
                tempQuestion = myQuestions.get(randomQuestion + myMultiAmt + myTFAmt);
            case 4:
                randomQuestion = myRand.nextInt(myQuestions.size());
                tempQuestion = myQuestions.get(randomQuestion);
            default:
                tempQuestion = null;
        }

        return tempQuestion;
    }
}
