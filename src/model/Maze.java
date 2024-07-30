/*
 * TCSS 360 Software Development and Assurance Techniques
 * Summer 2024
 */

package model;

// TO DO - in moveForward(), check door's state
//          maybe have it return smthn too
//          for controller to see we needa question or smthn

// Abby - also dunno what to do with String yet
// might have it somehow used for map
// by drawing image icons in a jpanel depending on the char

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This represents a 2D Maze that is travelled
 * horizontally or vertically.
 *
 * @author Abbygaile Yrojo
 * @version July 29, 2024
 */
public class Maze {
    /** To control how filled the maze is with Rooms. */
    private final static double MAX_RATIO = 0.6;
    /** For turning directions. */
    private final static Direction[] myDirections = {Direction.NORTH,
                    Direction.EAST, Direction.SOUTH, Direction.WEST};
    /** The maze itself. */
    private Room[][] myRooms;
    /** Rooms that may be travelled through. */
    private List<Room> myPathRooms;
    /** The beginning of the maze. */
    private Room myStartRoom;
    /** The room with the exit. */
    private Room myEndRoom;
    /** Player's current room. */
    private Room myCurrentRoom;
    /** Int representation of player's current direction. */
    private int myDirIndex;

    /**
     * Constructs the Maze.
     */
    public Maze() {
        myPathRooms = new ArrayList<>();
        myDirIndex = 0;
    }

    /**
     * Generates paths in the maze.
     * @param theWidth int.
     * @param theHeight int.
     */
    public void assembleMaze(final int theWidth, final int theHeight) {
        myRooms = new Room[theWidth][theHeight];
        Random random = new Random();
        // rooms default to "block" state
        fillDefaultRooms();
        // set start
        setStart(random);
        // carve a "winning path"
        createPaths(random);
        // set up doors
        createDoors();
    }

    /**
     * Sets the starting room.
     * @param theRandom Random.
     */
    private void setStart(final Random theRandom) {
        Direction startSide = Direction.randomDirection();
        int[] index = {0, 0};
        switch (startSide) {
            case NORTH:
                index[1] = theRandom.nextInt(myRooms[0].length);
                break;
            case EAST:
                index[0] = theRandom.nextInt(myRooms.length);
                index[1] = myRooms[0].length - 1;
                break;
            case SOUTH:
                index[0] = myRooms.length - 1;
                index[1] = theRandom.nextInt(myRooms[0].length);
                break;
            case WEST:
                index[0] = theRandom.nextInt(myRooms.length);
                break;
        }
        myStartRoom = myRooms[index[0]][index[1]];
        myStartRoom.setStart(true);
        myStartRoom.setBlock(false);
        myPathRooms.add(myStartRoom);
        myCurrentRoom = myStartRoom;
    }

    /**
     * Fills maze with blocked Rooms.
     */
    private void fillDefaultRooms() {
        for (int i = 0; i < myRooms.length; i++) {
            for (int j = 0; j < myRooms[0].length; j++) {
                myRooms[i][j] = new Room(i, j);
            }
        }
    }

    /**
     * Creates a path from the start until the Maze is full enough.
     * @param theRandom Random.
     */
    private void createPaths(final Random theRandom) {
        final int size = myRooms.length * myRooms[0].length;
        double currentRatio = (double) myPathRooms.size() / size;
        Room currentRoom = myRooms[myPathRooms.get(0).getRow()]
                [myPathRooms.get(0).getCol()];
        List<Room> edgeRooms = new ArrayList<Room>();
        Direction dir = null;
        int i = 0;
        while (currentRatio <= MAX_RATIO) {
            // pick a valid direction
            if (!canGo(currentRoom, dir) || i == 2) {
                dir = chooseDirection(currentRoom, theRandom);
                i = 0;
            }
            // find and convert that room to a path
            currentRoom = myRooms[currentRoom.getRow() + dir.dy()]
                    [currentRoom.getCol() + dir.dx()];
            currentRoom.setBlock(false);
            if (!myPathRooms.contains(currentRoom)) {
                myPathRooms.add(currentRoom);
                // for finding a possible ending door
                if (isAlongEdge(currentRoom)) {
                    edgeRooms.add(currentRoom);
                }
            }
            currentRatio = (double) myPathRooms.size() / size;
            i++;
        }
        setEnd(edgeRooms, theRandom);
    }

    /**
     * Sets Room with door to exit.
     * @param theList List of Rooms along the edge of the maze.
     * @param theRandom Random.
     */
    private void setEnd(final List<Room> theList, final Random theRandom) {
        Room room = myStartRoom;
        final double minDistance = getDistanceBetweenRooms(myStartRoom,
                myRooms[myRooms.length / 2]
                        [myRooms[0].length / 2]);
        while (!room.isEnd()) {
            if (getDistanceBetweenRooms(myStartRoom, room) > minDistance) {
                room.setEnd(true);
            } else {
                room = theList.get(theRandom.nextInt(theList.size()));
            }
        }
        // open up the right door for exit
        for (Direction d : Direction.values()) {
            if (!canGo(room, d)) {
                room.addDoor(d);
                break;
            }
        }
        myEndRoom = room;
    }

    private void createDoors() {
        Room room;
        for (int i = 0; i < myPathRooms.size(); i++) {
            room = myPathRooms.get(i);
            for (Direction d : Direction.values()) {
                if (isOpenRoom(room, d)) {
                    room.addDoor(d);
                }
            }
        }
    }

    /**
     * Returns distance between Rooms.
     * @param theRoom1 Room operand 1.
     * @param theRoom2 Room operand 2.
     * @return double.
     */
    private double getDistanceBetweenRooms(final Room theRoom1,
                                           final Room theRoom2) {
        return Math.sqrt(Math.pow(theRoom1.getCol() - theRoom2.getCol(), 2) +
                Math.pow(theRoom1.getRow() - theRoom2.getRow(), 2));
    }

    /**
     * Returns if a room is along the edge of the maze.
     * @param theRoom Room.
     * @return boolean.
     */
    private boolean isAlongEdge(final Room theRoom) {
        return !(canGo(theRoom, Direction.NORTH)
                && canGo(theRoom, Direction.EAST)
                && canGo(theRoom, Direction.SOUTH)
                && canGo(theRoom, Direction.WEST));
    }

    /**
     * Returns if there is a room in a certain direction.
     * @param theRoom Current room.
     * @param theDirection Direction.
     * @return boolean.
     */
    private boolean canGo(final Room theRoom, final Direction theDirection) {
        return theDirection != null &&
                isInBounds(theRoom.getCol() +
                        theDirection.dx(), myRooms[0].length) &&
                isInBounds(theRoom.getRow()
                        + theDirection.dy(), myRooms.length);
    }

    /**
     * Returns if the Room can be entered.
     * @param theRoom Current room.
     * @param theDirection Direction.
     * @return boolean.
     */
    private boolean isOpenRoom(final Room theRoom, final Direction theDirection) {
        return canGo(theRoom, theDirection) &&
                (!myRooms[theRoom.getRow()]
                        [theRoom.getCol() + theDirection.dx()].isBlock() &&
                        !myRooms[theRoom.getRow() + theDirection.dy()][theRoom.getCol()]
                                .isBlock());
    }

    /**
     * Chooses direction to travel from the current Room.
     * @param theRoom Room.
     * @param theRandom Random.
     * @return Direction.
     */
    private Direction chooseDirection(final Room theRoom,
                                      final Random theRandom) {
        List<String> validDirections = getValidDirections(theRoom);
        return Direction.valueOf(validDirections.get(
                theRandom.nextInt(validDirections.size())));
    }

    /**
     * Returns a list of directions that a Room can lead to.
     * @param theRoom Room.
     * @return List of directions as Strings.
     */
    private List<String> getValidDirections(final Room theRoom) {
        List<String> list = new ArrayList<String>();
        Direction[] directions = Direction.values();
        int change, bound;
        for (int i = 0; i < directions.length; i++) {
            if (directions[i].dx() != 0) {
                change = theRoom.getCol() + directions[i].dx();
                bound = myRooms[0].length;
            } else {
                change = theRoom.getRow() + directions[i].dy();
                bound = myRooms.length;
            }
            if (isInBounds(change, bound)) {
                list.add("" + directions[i]);
            }
        }
        return list;
    }

    /**
     * Checks if value is between 0 and given bound.
     * @param theX int.
     * @param theBound int.
     * @return boolean.
     */
    private boolean isInBounds(final int theX, final int theBound) {
        return theX < theBound && theX >= 0;
    }

    /**
     * Attempts to move to the room player is facing.
     */
    public boolean moveForward() {
        // IMPORTANT *****
        // Still have to check door state
        // Maybe return if door is locked, unanswered, or unlocked as int or smthn
        if (isOpenRoom(myCurrentRoom, myDirections[myDirIndex])) {
            myCurrentRoom = myRooms[myCurrentRoom.getRow()
                    + myDirections[myDirIndex].dy()]
                    [myCurrentRoom.getCol() + myDirections[myDirIndex].dx()];
            return true;
        }
        return false;
    }

    /**
     * Sets player's current direction.
     * @param theS String of which way to turn.
     */
    public void setCurrentDirection(final String theS) {
        if (theS.toLowerCase() == "left") {
            myDirIndex--;
        } else if (theS.toLowerCase() == "right") {
            myDirIndex++;
        }

        if(myDirIndex == -1) {
            myDirIndex = 3;
        }
        else if (myDirIndex == 4) {
            myDirIndex = 0;
        }
    }

    /**
     * Returns a string representation of the maze.
     * @return String.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        String space = " ";
        for (int i = 0; i < myRooms.length; i++) {
            // add the horizontal doors
            for (int j = 0; j < myRooms[0].length; j++) {
                s.append(space + space + space);
                if (myRooms[i][j].getDoor(Direction.NORTH) == null) {
                    s.append("=");
                } else {
                    s.append(myRooms[i][j].getDoor(Direction.NORTH));
                }
            }
            s.append(" \n");
            // add the vertical doors
            for (int j = 0; j < myRooms[0].length; j++) {
                if (myRooms[i][j].getDoor(Direction.WEST) == null) {
                    s.append(" | ");
                } else {
                    s.append(space + myRooms[i][j].getDoor(Direction.WEST) + space);
                }
                s.append(myRooms[i][j]);
            }
            // add last vertical door
            if (myRooms[i][myRooms[0].length - 1].
                    getDoor(Direction.EAST) == null) {
                s.append(" | ");
            } else {
                s.append(space + myRooms[i][myRooms[0].length - 1].
                        getDoor(Direction.EAST));
            }
            s.append("\n");
        }
        // bottom row
        for (int k = 0; k < myRooms[0].length; k++) {
            s.append(space + space + space);
            if (myRooms[myRooms.length - 1][k].
                    getDoor(Direction.SOUTH) == null) {
                s.append("=");
            } else {
                s.append(myRooms[myRooms.length - 1][k].
                        getDoor(Direction.SOUTH) + space);
            }
        }

        return s.toString();
    }
}