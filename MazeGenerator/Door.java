public class Door {
    private String myQuestion;
    private String myAnswer;
    private boolean myIsLocked;
    private boolean myIsAttempted;

    public Door() {
        setIsLocked(false);
        setIsAttempted(false);
    }

    private void setIsLocked(final boolean theIsLocked) {
        myIsLocked = theIsLocked;
    }

    private void setIsAttempted(final boolean theIsAttempted) {
        myIsAttempted = theIsAttempted;
    }

    private boolean canPass() {
        return !myIsLocked && myIsAttempted;
    }

    public boolean getIsLocked() {
        return myIsLocked;
    }

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

/*
JUST SOME NOTES WHILE WRITING THE CODE:

in the 2d array, the door may need its own int or char to be represented
so the door can have a wall state, which is:
    myIsLocked is true

 */