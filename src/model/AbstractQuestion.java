package model;

/**
 * Abstract data type for the Question objects.
 * The question proper is stored in a String, as is
 * the correct answer.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 *         Abby Yrojo
 *         Mahammod
 * @version 0.1
 */
public class AbstractQuestion implements Question {

    String myQuestion;

    String myCorrectAnswer;

    public AbstractQuestion(String theQuestion, String theCorrectAnswer) {
        myQuestion = theQuestion;
        myCorrectAnswer = theCorrectAnswer;
    }


    @Override
    public String getQuestion() {
        return myQuestion;
    }

    @Override
    public String getCorrectAnswer() {
        return myCorrectAnswer;
    }
}
