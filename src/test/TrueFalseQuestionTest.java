/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package test;

import model.TrueFalseQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for TrueFalseQuestion.
 *
 * @author Zane Swaims
 * @version 1.0
 */
public class TrueFalseQuestionTest {
    /**
     * Question object for the tests.
     */
    private TrueFalseQuestion myQuestion;

    /**
     * Sets up question for testing.
     */
    @BeforeEach
    public void setUp() {
        myQuestion = new TrueFalseQuestion();
        myQuestion.setQuestion("Is the sky blue?");
        myQuestion.setCorrectAnswer("True");
    }

    /**
     * Tests the constructor.
     */
    @Test
    public void testDefaultConstructor() {
        assertNotNull(myQuestion, "TrueFalseQuestion " +
                "instance should be created.");
    }

    /**
     * Tests setting the question.
     */
    @Test
    public void testSetQuestion() {
        myQuestion.setQuestion("Is 2 + 2 equal to 4?");
        assertEquals("Is 2 + 2 equal to 4?", myQuestion.getQuestion(),
                "Question text should be updated correctly.");
    }

    /**
     * Tests setting the correct answer.
     */
    @Test
    public void testSetCorrectAnswer() {
        myQuestion.setCorrectAnswer("False");
        assertEquals("False", myQuestion.getCorrectAnswer(),
                "Correct answer should be updated correctly.");
    }

    /**
     * Tests getting the question.
     */
    @Test
    public void testGetQuestion() {
        assertEquals("Is the sky blue?", myQuestion.getQuestion(),
                "Initial question should match the set value.");
    }

    /**
     * Tests getting the correct answer.
     */
    @Test
    public void testGetCorrectAnswer() {
        assertEquals("True", myQuestion.getCorrectAnswer(),
                "Initial correct answer should match the set value.");
    }

    /**
     * Tests setting the ID.
     */
    @Test
    public void testSetID() {
        myQuestion.setID(202);
        assertEquals(202, myQuestion.getID(), "ID should be set correctly.");
    }

    /**
     * Tests setting the type of question.
     */
    @Test
    public void testSetType() {
        myQuestion.setType(2); // Assuming 2 is the type for true/false questions.
        assertEquals(2, myQuestion.getType(), "Type should be set correctly.");
    }
}
