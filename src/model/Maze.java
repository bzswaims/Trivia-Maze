/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This represents a 2D Maze that is travelled
 * horizontally or vertically.
 *
 * @author Abbygaile Yrojo
 * @version August 14, 2024
 */
public class Maze implements Serializable {
    /** For saving. */
    @Serial
    private static final long serialVersionUID = 62999933L;

    /** Length of maze. */
    private static final int LENGTH = 4;

    /** To control how filled the maze is with Rooms. */
    private final static double MAX_RATIO = 0.6;

    /** For turning directions. */
    private final static Direction[] DIRECTIONS = {Direction.NORTH,
                    Direction.EAST, Direction.SOUTH, Direction.WEST};

    /** The maze itself. */
    private Room[][] myRooms;

    /** Rooms that may be travelled through. */
    private final List<Room> myPathRooms;

    /** The beginning of the maze. */
    private Room myStartRoom;

    /** Player's current room. */
    private Room myCurrentRoom;

    /** Int representation of player's current direction. */
    private int myDirIndex;

    /**
     * Question factory to build questions with.
     */
    private final QuestionFactory myQuestionFactory;

    /**
     * Constructs the Maze.
     */
    public Maze() {
        myPathRooms = new ArrayList<>();
        myDirIndex = 0;
        myQuestionFactory = new QuestionFactory();

        assembleMaze();
    }

    /**
     * Generates paths in the maze.
     */
    private void assembleMaze() {
        myRooms = new Room[LENGTH][LENGTH];
        Random random = new Random();
        // rooms default to "block" state
        fillDefaultRooms();
        // set start
        setStart(random);
        // carve "path" rooms until full enough
        createPaths(random);
        // set up doors
        createDoors();
        //apply questions to doors
        setQuestions(random);
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
        Room currentRoom = myRooms[myPathRooms.getFirst().getRow()]
                [myPathRooms.getFirst().getCol()];
        List<Room> edgeRooms = new ArrayList<>();
        Direction dir = null;
        int i = 0;
        while (currentRatio <= MAX_RATIO) {
            // pick a valid direction
            if (!doesRoomExist(currentRoom, dir) || i == 2) {
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
            if (!doesRoomExist(room, d)) {
                room.addDoor(d, null);
                room.getDoor(d).setExit(true);
                break;
            }
        }
    }

    /**
     * Creates doors for every path room.
     */
    private void createDoors() {
        Room room;
        for (Room myPathRoom : myPathRooms) {
            room = myPathRoom;
            for (Direction d : Direction.values()) {
                // if the room is open in a certain d
                // canEnter also handles if there is a room in a certain d
                if (canEnter(room, d)) {
                    Room neighbor = myRooms[room.getRow() + d.dy()]
                            [room.getCol() + d.dx()];
                    // neighbor room's direction would be opposite
                    Door neighborDoor = neighbor.getDoor(d.flip(d));
                    if (neighborDoor != null) room.addDoor(d, neighborDoor);
                    else room.addDoor(d, null);
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
        return !(doesRoomExist(theRoom, Direction.NORTH)
                && doesRoomExist(theRoom, Direction.EAST)
                && doesRoomExist(theRoom, Direction.SOUTH)
                && doesRoomExist(theRoom, Direction.WEST));
    }

    /**
     * Returns if there is a room in a certain direction.
     * @param theRoom Current room.
     * @param theDirection Direction.
     * @return boolean.
     */
    private boolean doesRoomExist(final Room theRoom,
                                  final Direction theDirection) {
        return theDirection != null &&
                isInBounds(theRoom.getCol() +
                        theDirection.dx(), myRooms[0].length) &&
                isInBounds(theRoom.getRow()
                        + theDirection.dy(), myRooms.length);
    }

    /**
     * Returns if the Room can be opened.
     * @param theRoom Current room.
     * @param theDirection Direction.
     * @return boolean.
     */
    private boolean canEnter(final Room theRoom,
                             final Direction theDirection) {
        return doesRoomExist(theRoom, theDirection) &&
                (!myRooms[theRoom.getRow()]
                        [theRoom.getCol() + theDirection.dx()].isBlock() &&
                        !myRooms[theRoom.getRow() + theDirection.dy()]
                                [theRoom.getCol()]
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
        List<String> list = new ArrayList<>();
        Direction[] directions = Direction.values();
        int change, bound;
        for (Direction direction : directions) {
            if (direction.dx() != 0) {
                change = theRoom.getCol() + direction.dx();
                bound = myRooms[0].length;
            } else {
                change = theRoom.getRow() + direction.dy();
                bound = myRooms.length;
            }
            if (isInBounds(change, bound)) {
                list.add("" + direction);
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
     * Returns the door's locked state.
     * 0 = locked, 1 = unattempted, 2 = unlocked
     * @return int.
     */
    public int getDoorLockState() {
        if (canEnter(myCurrentRoom, DIRECTIONS[myDirIndex])) {
            return myCurrentRoom.getDoor(DIRECTIONS[myDirIndex])
                    .getLockState();
        }
        return -1;
    }

    /**
     * Attempts to move to the room player is facing.
     */
    public void moveForward() {
        Room room = getNextRoom(myCurrentRoom, DIRECTIONS[myDirIndex]);
        if (room != null) {
            myCurrentRoom = room;
        }
    }

    /**
     * Gets the Room in a certain Direction.
     * @param theRoom Room.
     * @param theD Direction.
     * @return Room.
     */
    private Room getNextRoom(final Room theRoom, final Direction theD) {
        if (canEnter(theRoom, theD)) {
            return myRooms[theRoom.getRow() + theD.dy()]
                    [theRoom.getCol() + theD.dx()];
        }
        return null;
    }

    /**
     * Sets player's current direction.
     * @param theS String of which way to turn.
     */
    public void setCurrentDirection(final String theS) {
        if (theS.equalsIgnoreCase("left")) {
            myDirIndex--;
        } else if (theS.equalsIgnoreCase("right")) {
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
                s.append(space).append(space).append(space);
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
                    s.append(space).append(myRooms[i][j].
                            getDoor(Direction.WEST)).append(space);
                }
                s.append(myRooms[i][j]);
            }
            // add last vertical door
            if (myRooms[i][myRooms[0].length - 1].
                    getDoor(Direction.EAST) == null) {
                s.append(" | ");
            } else {
                s.append(space).append(myRooms[i][myRooms[0].length - 1]
                        .getDoor(Direction.EAST));
            }
            s.append("\n");
        }
        // bottom row
        for (int k = 0; k < myRooms[0].length; k++) {
            s.append(space).append(space).append(space);
            if (myRooms[myRooms.length - 1][k].
                    getDoor(Direction.SOUTH) == null) {
                s.append("=");
            } else {
                s.append(myRooms[myRooms.length - 1][k].
                        getDoor(Direction.SOUTH)).append(space);
            }
        }

        return s.toString();
    }

    /**
     * Returns if player has no path to the exit.
     * @return boolean.
     */
    public boolean hasLost() {
        for (Room myPathRoom : myPathRooms) {
            myPathRoom.setMazeVisited(false);
        }
        return hasLost(myCurrentRoom);
    }

    /**
     * Helper method for hasLost.
     * @param theCurrent Room.
     * @return boolean.
     */
    private boolean hasLost(final Room theCurrent) {
        theCurrent.setMazeVisited(true);
        final boolean[] lostDirections = new boolean[4];
        // check if each door exists and not locked
        for (int i = 0; i < DIRECTIONS.length; i++) {
            final Door door = theCurrent.getDoor(DIRECTIONS[i]);
            lostDirections[i] = true;
            if (door != null && door.getLockState() != 0) {
                Room neighborRoom = getNextRoom(theCurrent, DIRECTIONS[i]);
                if (neighborRoom != null && !neighborRoom.getMazeVisited()) {
                    lostDirections[i] = hasLost(neighborRoom);
                } else if (door.getExit()) {
                    return false;
                }
            }
        }
        // if there is an open path in a certain direction, then not lost
        for (boolean lostDirection : lostDirections) {
            if (!lostDirection) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the player current room
     * @return Room player's current room
     */
    public Room getCurrentRoom() {
        return myCurrentRoom;
    }

    /**
     * Gets number of rows.
     * @return int.
     */
    public int getRows() {
        return myRooms.length;
    }

    /**
     * Returns current direction index.
     * @return int.
     */
    public int getCurrentDirection() {
        return myDirIndex;
    }

    /**
     * Sets questions to all doors in the maze.
     *
     * @param theRandom Random object to randomly select the questions.
     */
    private void setQuestions(final Random theRandom) {
        for (Room room : myPathRooms) {
            for (Direction direction : DIRECTIONS) {
                Door door = room.getDoor(direction);
                if (door != null && door.getQuestion() == null) {
                    door.setQuestion(myQuestionFactory
                            .makeQuestion(theRandom.nextInt(3) + 1));
                }
            }
        }
    }

    /**
     * Accessor to retrieve the current question.
     * @return AbstractQuestion.
     */
    public AbstractQuestion getCurrentQuestion() {
        return myCurrentRoom.getDoor(DIRECTIONS[myDirIndex]).getQuestion();
    }

    /**
     * Returns answers for multi-choice question.
     * @return String array of answers.
     */
    public String[] getAnswers() {
        AbstractQuestion question = getCurrentQuestion();
        if (question.getType() == 1) {
            List<String> list = ((MultiQuestion) question)
                    .getIncorrectAnswers();
            list.add(question.getCorrectAnswer());
            Collections.shuffle(list);
            String[] answers = new String[4];
            for (int i = 0; i < list.size(); i++) {
                answers[i] = list.get(i);
            }
            return answers;
        }
        return null;
    }

    /**
     * Checks if answer is correct.
     * @param theAnswer String answer.
     * @return boolean.
     */
    public boolean isCorrect(final String theAnswer) {
        return getCurrentQuestion().getCorrectAnswer()
                .equalsIgnoreCase(theAnswer);
    }


    /**
     * Sets current door's state.
     * @param theInt int state.
     */
    public void setDoorState(final int theInt) {
        if (theInt > -1 && theInt < 3) {
            myCurrentRoom.getDoor(DIRECTIONS[myDirIndex]).setLockState(theInt);
        }
    }
}