/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class for the doors that connect the Room objects.
 *
 * @author Abbygaile Yrojo
 * @author Zane Swaims
 *
 * @version 1.0
 */
public class Door implements Serializable {

    @Serial
    private static final long serialVersionUID = 3666666777L;

    /**
     * Question to be stored on the door.
     */
    private AbstractQuestion myQuestion;

    /**
     * Current door state, 0 = locked
     *             1 = unattempted
     *             2 = unlocked
     */
    private int myLockState;

    /**
     * If the Door is the exit.
     */
    private boolean myExit;

    /**
     * Constructs Door.
     */
    public Door() {
        setLockState(1);
        setExit(false);
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
     *
     * @param theQuestion String.
     */
    public void setQuestion(final AbstractQuestion theQuestion) {
        myQuestion = theQuestion;
    }

    /**
     * Returns the question attached to the door.
     *
     * @return the question attached to the door.
     */
    public AbstractQuestion getQuestion() {
        if (myQuestion != null) {
            return myQuestion;
        }
        return null;
    }

    /**
     * Returns if Door can be passed.
     *
     * @return boolean.
     */
    public int getLockState() {
        return myLockState;
    }

    /**
     * Sets if Door is the exit.
     *
     * @param theBool boolean.
     */
    public void setExit(final boolean theBool) {
        myExit = theBool;
    }

    public boolean getExit() {
        return myExit;
    }

    /**
     * Returns Door string.
     *
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
