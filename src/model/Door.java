package model;

public class Door {
    /**
     * Question.
     */
    private AbstractQuestion myQuestion;

    /**
     * Correct answer to question.
     */
    private String myAnswer;

    /**
     * Current door state, 0 = locked
     *             1 = unattempted
     *             2 = unlocked
     */
    private int myLockState;

    /**
     * Constructs Door.
     */
    public Door() {
        setLockState(1);

        //call question factory to grab a question to slap onto this door!
    }

    /**
     * Sets the lock state.
     */
    public void setLockState(final int theState) {
        if (theState < 0 || theState > 2) {
            throw new IllegalArgumentException("State must be 0, 1, or 2!");
        }
        myLockState = theState;
    }

    /**
     * Sets the question.
     * @param theQuestion String.
     */
    private void setQuestion(final AbstractQuestion theQuestion) {
        myQuestion = theQuestion;
    }

    /**
     * Returns if Door can be passed.
     * @return boolean.
     */
    public int getLockState() {
        return myLockState;
    }

    /**
     * Returns Door string.
     * @return String.
     */
    @Override
    public String toString() {
        if (myLockState == 0) {
            return "t";
        } else if (myLockState == 1) {
            return "?";
        }

        return "o";
    }
}
