package test;

import model.TrueFalseQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrueFalseQuestionTest {

    private TrueFalseQuestion trueFalseQuestion;

    @BeforeEach
    void setUp() {
        trueFalseQuestion = new TrueFalseQuestion();
        trueFalseQuestion.setQuestion("Is the sky blue?");
        trueFalseQuestion.setCorrectAnswer("True");
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(trueFalseQuestion, "TrueFalseQuestion instance should be created.");
    }

    @Test
    void testSetQuestion() {
        trueFalseQuestion.setQuestion("Is 2 + 2 equal to 4?");
        assertEquals("Is 2 + 2 equal to 4?", trueFalseQuestion.getQuestion(), "Question text should be updated correctly.");
    }

    @Test
    void testSetCorrectAnswer() {
        trueFalseQuestion.setCorrectAnswer("False");
        assertEquals("False", trueFalseQuestion.getCorrectAnswer(), "Correct answer should be updated correctly.");
    }

    @Test
    void testGetQuestion() {
        assertEquals("Is the sky blue?", trueFalseQuestion.getQuestion(), "Initial question should match the set value.");
    }

    @Test
    void testGetCorrectAnswer() {
        assertEquals("True", trueFalseQuestion.getCorrectAnswer(), "Initial correct answer should match the set value.");
    }

    @Test
    void testSetID() {
        trueFalseQuestion.setID(202);
        assertEquals(202, trueFalseQuestion.getID(), "ID should be set correctly.");
    }

    @Test
    void testSetType() {
        trueFalseQuestion.setType(2); // Assuming 2 is the type for true/false questions.
        assertEquals(2, trueFalseQuestion.getType(), "Type should be set correctly.");
    }
}
