public class Door {
    private String myQuestion;
    private String myAnswer;
    private boolean myIsLocked;
    private boolean myIsAttempted;
    private Direction myDirection;

    public Door(final Direction theDirection) {
        myDirection = theDirection;
        setIsLocked(true);
        setIsAttempted(true);
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

    private char getDoorChar() {
        if (myIsLocked) {
            if (myIsAttempted) {
                return 'X';
            } else {
                return '?';
            }
        }
        return 'O';
    }
}

/*
JUST SOME NOTES WHILE WRITING THE CODE:

in the 2d array, the door may need its own int or char to be represented
so the door can have a wall state, which is:
    myIsLocked is true
    myIsAttempted is true

 */