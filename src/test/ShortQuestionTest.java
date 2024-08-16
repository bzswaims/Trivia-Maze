package test;

import model.ShortQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortQuestionTest {

    private ShortQuestion shortQuestion;

    @BeforeEach
    void setUp() {
        shortQuestion = new ShortQuestion();
        shortQuestion.setQuestion("What is the largest planet in our solar system?");
        shortQuestion.setCorrectAnswer("Jupiter");
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(shortQuestion, "ShortQuestion instance should be created.");
    }

    @Test
    void testSetQuestion() {
        shortQuestion.setQuestion("What is the smallest planet in our solar system?");
        assertEquals("What is the smallest planet in our solar system?", shortQuestion.getQuestion(), "Question text should be updated correctly.");
    }

    @Test
    void testSetCorrectAnswer() {
        shortQuestion.setCorrectAnswer("Mercury");
        assertEquals("Mercury", shortQuestion.getCorrectAnswer(), "Correct answer should be updated correctly.");
    }

    @Test
    void testGetQuestion() {
        assertEquals("What is the largest planet in our solar system?", shortQuestion.getQuestion(), "Initial question should match the set value.");
    }

    @Test
    void testGetCorrectAnswer() {
        assertEquals("Jupiter", shortQuestion.getCorrectAnswer(), "Initial correct answer should match the set value.");
    }

    @Test
    void testSetID() {
        shortQuestion.setID(101);
        assertEquals(101, shortQuestion.getID(), "ID should be set correctly.");
    }

    @Test
    void testSetType() {
        shortQuestion.setType(3); // Assuming 3 is the type for short answer questions.
        assertEquals(3, shortQuestion.getType(), "Type should be set correctly.");
    }
}
