/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package model;

import java.util.Random;

/**
 * An enumeration (and associated functionality) for directions in which a
 * vehicle may travel.
 *
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler
 * @version 1.2
 */
public enum Direction {
    /**
     * North (which is up on the screen).
     */
    NORTH('N'),

    /**
     * West (which is left on the screen).
     */
    WEST('W'),

    /**
     * South (which is down on the screen).
     */
    SOUTH('S'),

    /**
     * East (which is right on the screen).
     */
    EAST('E');

    /**
     * A Random that we use for generating random directions.
     */
    private static final Random RANDOM = new Random();

    /**
     * The letter corresponding to a particular value of the enumeration.
     */
    private final char myLetter;

    /**
     * Constructs a new Terrain with the specified letter.
     *
     * @param theLetter The letter.
     */
    Direction(final char theLetter) {
        myLetter = theLetter;
    }

    /**
     * Returns the letter corresponding to this direction.
     *
     * @return the letter corresponding to this direction.
     */
    public char letter() {
        return myLetter;
    }

    /**
     * Returns a random Direction.
     *
     * @return a random Direction.
     */
    public static Direction randomDirection() {
        return values()[RANDOM.nextInt(values().length)];
    }

    /**
     * Returns the change in x-coordinate by moving one space in this direction
     * (for example, WEST would be -1, and NORTH would be 0).
     *
     * @return the change in x-coordinate.
     */
    public int dx() {
        int result = 0;

        switch (this) {
            case WEST:
                result = -1;
                break;

            case EAST:
                result = 1;
                break;

            default:
        }

        return result;
    }

    /**
     * Returns the change in y-coordinate by moving one space in this direction
     * (for example, WEST would be 0, and NORTH would be -1).
     *
     * @return the change in y-coordinate.
     */
    public int dy() {
        int result = 0;

        switch (this) {
            case SOUTH:
                result = 1;
                break;

            case NORTH:
                result = -1;
                break;

            default:
        }

        return result;
    }
}
