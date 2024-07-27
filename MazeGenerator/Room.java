public class Room {
    private final Door[] myDoors;
    private boolean myIsBlock;
    private boolean myIsStart;
    private int myRow;
    private int myCol;
    // testing purposes
    private boolean myIsEnd;

    public Room(final int theRow, final int theCol) {
        setRow(theRow);
        setCol(theCol);
        myDoors = new Door[4];
        setBlock(true);
        setStart(false);
        setEnd(false);
    }

    public Room(final Room theRoom) {
        this(theRoom.getRow(), theRoom.getCol());
        setBlock(theRoom.isBlock());
        setStart(theRoom.isStart());
        setEnd(theRoom.isEnd());
    }

    public void setEnd(final boolean theBool) {
        myIsEnd = theBool;
    }

    public void setRow(final int theRow) {
        myRow = theRow;
    }

    public void setCol(final int theCol) {
        myCol = theCol;
    }

    public int getRow() {
        return myRow;
    }

    public int getCol() {
        return myCol;
    }

    public boolean isEnd() {
        return myIsEnd;
    }

    public boolean isBlock() {
        return myIsBlock;
    }

    public boolean isStart() {
        return myIsStart;
    }

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

    public void setStart(final boolean theBool) {
        myIsStart = theBool;
    }

    public void setBlock(final boolean theBool) {
        myIsBlock = theBool;
    }

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
I like to remember directions like "Never Eat Soggy Worms" or smthn lol


 */