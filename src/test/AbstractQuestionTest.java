/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package test;

import model.AbstractQuestion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AbstractQuestionTest {

    @Test
    void testDefaultConstructor() {
        AbstractQuestion question = new AbstractQuestion();
        assertEquals("", question.getQuestion(), "Default question text should be an empty string.");
        assertEquals("", question.getCorrectAnswer(), "Default correct answer should be an empty string.");
        assertEquals(-1, question.getID(), "Default ID should be -1.");
        assertEquals(-1, question.getType(), "Default type should be -1.");
    }

    @Test
    void testSetQuestion() {
        AbstractQuestion question = new AbstractQuestion();
        question.setQuestion("What is the capital of France?");
        assertEquals("What is the capital of France?", question.getQuestion(), "Question text should match the set value.");
    }

    @Test
    void testSetCorrectAnswer() {
        AbstractQuestion question = new AbstractQuestion();
        question.setCorrectAnswer("Paris");
        assertEquals("Paris", question.getCorrectAnswer(), "Correct answer should match the set value.");
    }

    @Test
    void testSetID() {
        AbstractQuestion question = new AbstractQuestion();
        question.setID(123);
        assertEquals(123, question.getID(), "ID should match the set value.");
    }

    @Test
    void testSetType() {
        AbstractQuestion question = new AbstractQuestion();
        question.setType(1);
        assertEquals(1, question.getType(), "Type should match the set value.");
    }
}
