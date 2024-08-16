package test;

import model.MultiQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MultiQuestionTest {

    private MultiQuestion multiQuestion;

    @BeforeEach
    void setUp() {
        multiQuestion = new MultiQuestion();
        multiQuestion.setQuestion("What is the capital of Italy?");
        multiQuestion.setCorrectAnswer("Rome");
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(multiQuestion.getIncorrectAnswers(), "Incorrect answers list should be initialized.");
        assertTrue(multiQuestion.getIncorrectAnswers().isEmpty(), "Incorrect answers list should be empty by default.");
    }

    @Test
    void testAddIncorrectAnswer() {
        multiQuestion.addIncorrectAnswer("Venice");
        multiQuestion.addIncorrectAnswer("Milan");

        ArrayList<String> incorrectAnswers = multiQuestion.getIncorrectAnswers();
        assertEquals(2, incorrectAnswers.size(), "There should be two incorrect answers.");
        assertTrue(incorrectAnswers.contains("Venice"), "Incorrect answers should include 'Venice'.");
        assertTrue(incorrectAnswers.contains("Milan"), "Incorrect answers should include 'Milan'.");
    }

    @Test
    void testAddDuplicateIncorrectAnswer() {
        multiQuestion.addIncorrectAnswer("Florence");
        multiQuestion.addIncorrectAnswer("Florence");

        ArrayList<String> incorrectAnswers = multiQuestion.getIncorrectAnswers();
        assertEquals(2, incorrectAnswers.size(), "There should be two incorrect answers, even if duplicates are added.");
        assertEquals("Florence", incorrectAnswers.get(0), "First incorrect answer should be 'Florence'.");
        assertEquals("Florence", incorrectAnswers.get(1), "Second incorrect answer should also be 'Florence'.");
    }

    @Test
    void testSetQuestion() {
        multiQuestion.setQuestion("What is the capital of France?");
        assertEquals("What is the capital of France?", multiQuestion.getQuestion(), "Question text should be updated correctly.");
    }

    @Test
    void testSetCorrectAnswer() {
        multiQuestion.setCorrectAnswer("Paris");
        assertEquals("Paris", multiQuestion.getCorrectAnswer(), "Correct answer should be updated correctly.");
    }

    @Test
    void testGetIncorrectAnswersEmpty() {
        assertTrue(multiQuestion.getIncorrectAnswers().isEmpty(), "Incorrect answers list should be empty initially.");
    }

    @Test
    void testIncorrectAnswersList() {
        multiQuestion.addIncorrectAnswer("Florence");
        multiQuestion.addIncorrectAnswer("Naples");

        ArrayList<String> incorrectAnswers = multiQuestion.getIncorrectAnswers();
        assertEquals(2, incorrectAnswers.size(), "Incorrect answers list should contain 2 items.");
        assertTrue(incorrectAnswers.contains("Florence"), "Incorrect answers should contain 'Florence'.");
        assertTrue(incorrectAnswers.contains("Naples"), "Incorrect answers should contain 'Naples'.");
    }
}
