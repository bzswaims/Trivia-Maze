/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package model;

/**
 * Room in maze.
 *
 * @author Abbygaile Yrojo
 * @version July 30, 2024
 */
public class Room {
    /**
     * Array of Doors, ordered N-E-S-W.
     */
    private final Door[] myDoors;

    /**
     * If Room cannot be entered.
     */
    private boolean myIsBlock;

    /**
     * If Room is the start room.
     */
    private boolean myIsStart;

    /**
     * Room's row in maze.
     */
    private int myRow;

    /**
     * Room's column in maze
     */
    private int myCol;

    /**
     * If Room contains end door.
     */
    private boolean myIsEnd;

    //TODO: field for if you have visited the room
    /**
     * If Room has been visited.
     */
    private boolean myHasBeenVisited;

    /**
     * Constructs Room.
     * @param theRow Int row.
     * @param theCol Int col.
     */
    public Room(final int theRow, final int theCol) {
        setRow(theRow);
        setCol(theCol);
        myDoors = new Door[4];
        setBlock(true);
        setStart(false);
        setEnd(false);
        myHasBeenVisited = false;
    }

    /**
     * Sets if Room has end door.
     * @param theBool boolean.
     */
    public void setEnd(final boolean theBool) {
        myIsEnd = theBool;
    }

    /**
     * Sets Room's row.
     * @param theRow Int row.
     */
    public void setRow(final int theRow) {
        myRow = theRow;
    }

    /**
     * Sets Room's column.
     * @param theCol Int column.
     */
    public void setCol(final int theCol) {
        myCol = theCol;
    }

    /**
     * Returns row.
     * @return The row the room is in.
     */
    public int getRow() {
        return myRow;
    }

    /**
     * Returns column.
     * @return The column the room is in.
     */
    public int getCol() {
        return myCol;
    }

    /**
     * Returns if Room has the end door.
     * @return If this has the end door.
     */
    public boolean isEnd() {
        return myIsEnd;
    }

    /**
     * Returns if Room is a blocked room.
     * @return If this is a blocked room.
     */
    public boolean isBlock() {
        return myIsBlock;
    }

    //TODO: accessor for if you've visited the room, feel free to change the name of it
    /**
     * Returns if the room has been visited.
     * @return if the room has been visited.
     */
    public boolean haveIBeen() { return myHasBeenVisited;}

    /**
     * Adds Door to Room based on direction.
     * @param theDirection Direction.
     */
    public void addDoor(final Direction theDirection) {
        if (myIsBlock) {
            setBlock(false);
        }
        Door door = new Door();
        switch (theDirection) {
            case NORTH:
                myDoors[0] = door;
                break;
            case EAST:
                myDoors[1] = door;
                break;
            case SOUTH:
                myDoors[2] = door;
                break;
            case WEST:
                myDoors[3] = door;
                break;
        }
    }

    /**
     * Returns Door based on direction.
     * @param theDirection Direction.
     * @return Door.
     */
    public Door getDoor(final Direction theDirection) {
        switch (theDirection) {
            case NORTH:
                return myDoors[0];
            case EAST:
                return myDoors[1];
            case SOUTH:
                return myDoors[2];
            case WEST:
                return myDoors[3];
            default:
                break;
        }
        return null;
    }

    /**
     * Sets if Room is the start room.
     * @param theBool boolean.
     */
    public void setStart(final boolean theBool) {
        myIsStart = theBool;

        //TODO: added this in to set the start room as having been visited.
        if(theBool) {
            myHasBeenVisited = true;
        }
    }

    /**
     * Sets if Room is a blocked room.
     * @param theBool boolean.
     */
    public void setBlock(final boolean theBool) {
        myIsBlock = theBool;
    }

    //TODO: mutator for if you've visited the room or not
    /**
     * Sets if Room has been visited.
     * @param theBool boolean flag if the room has been visited.
     */
    public void setHasBeenVisited (final boolean theBool) { myHasBeenVisited = theBool;}

    /**
     * Returns String of Room (but not doors).
     * @return String.
     */
    @Override
    public String toString() {
        if (myIsStart) {
            return "s";
        } else if (myIsBlock) {
            return "w";
        } else if (myIsEnd) { // REMOVE THIS LATER. WILL ADD DOORS TO MAZE STRING
            return "e";
        } else {
            return "+";
        }
    }
}

/*
NOTES (possibly but can be changed):
Door[] Index guide:
    0 - North
    1 - East
    2 - South
    3 - West
I like to remember directions like "Never Eat Soggy Worms" or something lol


 */