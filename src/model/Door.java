package model;

public class Door {
    /**
     * Question.
     */
    private String myQuestion;

    /**
     * Correct answer to question.
     */
    private String myAnswer;

    /**
     * If Door is locked.
     */
    private boolean myIsLocked;

    /**
     * If Door's question has been attempted.
     */
    private boolean myIsAttempted;

    /**
     * Constructs Door.
     */
    public Door() {
        setIsLocked(false);
        setIsAttempted(false);
    }

    /**
     * Sets if Door is locked.
     * @param theIsLocked boolean.
     */
    private void setIsLocked(final boolean theIsLocked) {
        myIsLocked = theIsLocked;
    }

    /**
     * Sets if Door's question has been attempted.
     * @param theIsAttempted boolean.
     */
    private void setIsAttempted(final boolean theIsAttempted) {
        myIsAttempted = theIsAttempted;
    }

    /**
     * Sets the question.
     * @param theQuestion String.
     */
    private void setQuestion(final String theQuestion) {
        myQuestion = theQuestion;
    }

    /**
     * Returns if Door can be passed.
     * @return boolean.
     */
    private boolean canPass() {
        return !myIsLocked && myIsAttempted;
    }

    /**
     * Returns if Door is locked.
     * @return boolean.
     */
    public boolean getIsLocked() {
        return myIsLocked;
    }

    /**
     * Returns Door string.
     * @return String.
     */
    @Override
    public String toString() {
        if (myIsLocked) {
            return "t";
        } else if (myIsAttempted) {
            return "o";
        }

        return "?";
    }
}
