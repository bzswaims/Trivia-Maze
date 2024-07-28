package model;

public class MutliQuestion extends AbstractQuestion {

    String[] myIncorrectAnswers;

    public MutliQuestion(String theQuestion, String theCorrectAnswer) {
        super(theQuestion, theCorrectAnswer);

        myIncorrectAnswers = new String[3];
    }

    String[] getIncorrectAnswers() {
        return myIncorrectAnswers;
    }
}
