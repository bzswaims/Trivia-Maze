public class Room {
    private final Door[] myDoors;
    private boolean myIsBlock;
    private boolean myIsStart;
    private int myRow;
    private int myCol;

    public Room(final int theRow, final int theCol) {
        setRow(theRow);
        setCol(theCol);
        myDoors = new Door[4];
        setBlock(true);
        setStart(false);
    }

    public Room(final Room theRoom) {
        this(theRoom.getRow(), theRoom.getCol());
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

    public void addDoor(final Direction theDirection) {
        if (myIsBlock) {
            setBlock(false);
        }
        Door door = new Door(theDirection);
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

    public void setStart(final boolean theBool) {
        myIsStart = theBool;
    }

    public void setBlock(final boolean theBool) {
        myIsBlock = theBool;
    }

    public char toChar() {
        if (myIsStart) {
            return 'S';
        } else if (myIsBlock) {
            return 'B';
        } else {
            return '+';
        }
    }

    public boolean isBlock() {
        return myIsBlock;
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