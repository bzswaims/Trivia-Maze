//TODO: working on this in case I need to redo my question factory.
package model;

public class AbstractQuestionFactory {

    private AbstractQuestion myQuestion;

    public AbstractQuestionFactory(final int theType) {
        switch(theType)
        {
            case 1:
                myQuestion = new MultiQuestion(theType);
            case 2:
                myQuestion = new TrueFalseQuestion(theType);
            case 3:
                myQuestion = new ShortQuestion(theType);
            default:
                myQuestion = null;
        }
    }

    //I do not know how to handle this next.
}
