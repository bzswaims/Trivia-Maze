/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package test;

import java.util.ArrayList;
import model.MultiQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for MultiQuestion.
 *
 * @author Zane Swaims
 * @version 1.0
 */
public class MultiQuestionTest {
    /**
     * Question object to test on.
     */
    private MultiQuestion myQuestion;

    /**
     * Set up MultiQuestion for testing.
     */
    @BeforeEach
    public void setUp() {
        myQuestion = new MultiQuestion();
        myQuestion.setQuestion("What is the capital of Italy?");
        myQuestion.setCorrectAnswer("Rome");
    }

    /**
     * Tests constructor.
     */
    @Test
    public void testDefaultConstructor() {
        assertNotNull(myQuestion.getIncorrectAnswers(),
                "Incorrect answers list should be initialized.");
        assertTrue(myQuestion.getIncorrectAnswers().isEmpty(),
                "Incorrect answers list should be empty by default.");
    }

    /**
     * Tests adding incorrect answers.
     */
    @Test
    public void testAddIncorrectAnswer() {
        myQuestion.addIncorrectAnswer("Venice");
        myQuestion.addIncorrectAnswer("Milan");

        ArrayList<String> incorrectAnswers =
                myQuestion.getIncorrectAnswers();
        assertEquals(2, incorrectAnswers.size(),
                "There should be two incorrect answers.");
        assertTrue(incorrectAnswers.contains("Venice"),
                "Incorrect answers should include 'Venice'.");
        assertTrue(incorrectAnswers.contains("Milan"),
                "Incorrect answers should include 'Milan'.");
    }

    /**
     * Tests if there can be duplicate incorrect answers.
     */
    @Test
    public void testAddDuplicateIncorrectAnswer() {
        myQuestion.addIncorrectAnswer("Florence");
        myQuestion.addIncorrectAnswer("Florence");

        ArrayList<String> incorrectAnswers =
                myQuestion.getIncorrectAnswers();
        assertEquals(2, incorrectAnswers.size(),
                "There should be two incorrect answers," +
                        " even if duplicates are added.");
        assertEquals("Florence", incorrectAnswers.get(0),
                "First incorrect answer should be 'Florence'.");
        assertEquals("Florence", incorrectAnswers.get(1),
                "Second incorrect answer should also be 'Florence'.");
    }

    /**
     * Tests setting the question.
     */
    @Test
    public void testSetQuestion() {
        myQuestion.setQuestion("What is the capital of France?");
        assertEquals("What is the capital of France?",
                myQuestion.getQuestion(),
                "Question text should be updated correctly.");
    }

    /**
     * Tests setting the correct answer.
     */
    @Test
    public void testSetCorrectAnswer() {
        myQuestion.setCorrectAnswer("Paris");
        assertEquals("Paris", myQuestion.getCorrectAnswer(),
                "Correct answer should be updated correctly.");
    }

    /**
     * Tests if list of incorrect answers is empty.
     */
    @Test
    public void testGetIncorrectAnswersEmpty() {
        assertTrue(myQuestion.getIncorrectAnswers().isEmpty(),
                "Incorrect answers list should be empty initially.");
    }

    /**
     * Tests adding to incorrect answers list.
     */
    @Test
    public void testIncorrectAnswersList() {
        myQuestion.addIncorrectAnswer("Florence");
        myQuestion.addIncorrectAnswer("Naples");

        ArrayList<String> incorrectAnswers =
                myQuestion.getIncorrectAnswers();
        assertEquals(2, incorrectAnswers.size(),
                "Incorrect answers list should contain 2 items.");
        assertTrue(incorrectAnswers.contains("Florence"),
                "Incorrect answers should contain 'Florence'.");
        assertTrue(incorrectAnswers.contains("Naples"),
                "Incorrect answers should contain 'Naples'.");
    }
}
