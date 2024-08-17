/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package test;

import model.ShortQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for ShortQuestion.
 *
 * @author Zane Swaims
 * @version 1.0
 */
public class ShortQuestionTest {

    private ShortQuestion myQuestion;

    @BeforeEach
    void setUp() {
        myQuestion = new ShortQuestion();
        myQuestion.setQuestion("What is the largest planet in our solar system?");
        myQuestion.setCorrectAnswer("Jupiter");
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(myQuestion, "ShortQuestion instance should be created.");
    }

    @Test
    void testSetQuestion() {
        myQuestion.setQuestion("What is the smallest planet in our solar system?");
        assertEquals("What is the smallest planet in our solar system?", myQuestion.getQuestion(), "Question text should be updated correctly.");
    }

    @Test
    void testSetCorrectAnswer() {
        myQuestion.setCorrectAnswer("Mercury");
        assertEquals("Mercury", myQuestion.getCorrectAnswer(), "Correct answer should be updated correctly.");
    }

    @Test
    void testGetQuestion() {
        assertEquals("What is the largest planet in our solar system?", myQuestion.getQuestion(), "Initial question should match the set value.");
    }

    @Test
    void testGetCorrectAnswer() {
        assertEquals("Jupiter", myQuestion.getCorrectAnswer(), "Initial correct answer should match the set value.");
    }

    @Test
    void testSetID() {
        myQuestion.setID(101);
        assertEquals(101, myQuestion.getID(), "ID should be set correctly.");
    }

    @Test
    void testSetType() {
        myQuestion.setType(3); // Assuming 3 is the type for short answer questions.
        assertEquals(3, myQuestion.getType(), "Type should be set correctly.");
    }
}
