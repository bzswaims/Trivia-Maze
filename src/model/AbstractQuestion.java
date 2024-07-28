package model;

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
