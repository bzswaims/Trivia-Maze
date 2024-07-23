/*
 * TCSS 360 Software Development and Assurance Techniques
 * Summer 2024
 */

// TO DO : change each Door in each path room to proper state
// CHANGES: end room added, but still needs edge of door as exit

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This represents a 2D Maze that is travelled
 * horizontally or vertically.
 *
 * @author Abbygaile Yrojo
 * @version July 22, 2024
 */
public class Maze {
    /** To control how filled the maze is with Rooms. */
    private final static double MAX_RATIO = 0.5;
    /** The maze itself. */
    private final Room[][] myRooms;
    /** Rooms that may be travelled through. */
    private List<Room> myPathRooms;
    /** The beginning of the maze. */
    private Room myStartRoom;

    /**
     * Constructs the Maze.
     * @param theWidth int.
     * @param theHeight int.
     */
    public Maze(final int theWidth, final int theHeight) {
        // toss in exception for < 1?

        myRooms = new Room[theWidth][theHeight];
        myStartRoom = null;
        myPathRooms = new ArrayList<>();
    }

    /**
     * Generates paths in the maze.
     */
    public void assembleMaze() {
        if (!myPathRooms.isEmpty()) {
            myPathRooms = new ArrayList<>();
        }

        Random random = new Random();
        // rooms default to "block" state
        fillDefaultRooms();
        // set start
        setStart(random);
        // carve a "winning path"
        createPaths(random);
        // set end
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
        myPathRooms.add(myStartRoom);
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
            if (!canGo(currentRoom, dir) || i++ % 5 == 0) {
                dir = chooseDirection(currentRoom, theRandom);
            }
            // find and convert that room to a path
            currentRoom = myRooms[currentRoom.getRow() + dir.dy()]
                    [currentRoom.getCol() + dir.dx()];
            currentRoom.setBlock(false);
            if (!myPathRooms.contains(currentRoom)) {
                myPathRooms.add(currentRoom);
                // this will be for finding a possible ending door
                if (isAlongEdge(currentRoom)) {
                    edgeRooms.add(currentRoom);
                }
            }
            currentRatio = (double) myPathRooms.size() / size;
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
        while (!room.getIsEnd()) {
            if (getDistanceBetweenRooms(myStartRoom, room) >= minDistance) {
                room.setIsEnd(true);
            } else {
                room = theList.get(theRandom.nextInt(theList.size()));
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
     * Returns a string representation of the maze.
     * @return String.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Room[] myRoom : myRooms) {
            for (int j = 0; j < myRooms[0].length; j++) {
                s.append(myRoom[j].toChar());
            }
            s.append("\n");
        }
        return s.toString();
    }
}