// 7:14 pm

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
    private final static double MAX_RATIO = 0.5;
    private final Room[][] myRooms;
    private final List<Room> myPathRooms;
    private Direction myStartSide;

    public Maze(final int theWidth, final int theHeight) {
        // toss in exception for < 1?

        myRooms = new Room[theWidth][theHeight];
        myPathRooms = new ArrayList<>();
        myStartSide = null;
    }

    /**
     * Generates paths in the maze.
     */
    public void assembleMaze() {
        Random random = new Random();
        // rooms default to "block" state
        fillDefaultRooms();
        // set start
        setStart(random);
        // carve a "winning path"
        createWinningPath(random);
        // add more paths if maze isn't too full
        // createExtraPaths(random);
    }

    private void setStart(final Random theRandom) {
        myStartSide = Direction.randomDirection();
        Room room;
        int[] index = {0, 0};
        switch (myStartSide) {
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
        room = myRooms[0][index[1]];
        room.setStart(true);
        myPathRooms.add(room);
    }

    private void fillDefaultRooms() {
        for (int i = 0; i < myRooms.length; i++) {
            for (int j = 0; j < myRooms[0].length; j++) {
                myRooms[i][j] = new Room(i, j);
            }
        }
    }

    private void createWinningPath(final Random theRandom) {
        // while number of "path" rooms / total rooms < some number
        final int size = myRooms.length * myRooms[0].length;
        double currentRatio = (double) myPathRooms.size() / size;
        Room currentRoom = myRooms[myPathRooms.get(0).getRow()]
                                    [myPathRooms.get(0).getCol()];
        Direction dir;
        List<Direction> list;
        while (currentRatio <= MAX_RATIO) {
            // pick a valid direction
            list = getValidDirections(currentRoom);
            dir = list.get(theRandom.nextInt(list.size()));
            // find and convert that room to a path
            currentRoom = myRooms[currentRoom.getRow() + dir.dy()]
                            [currentRoom.getCol() + dir.dx()];
            currentRoom.setBlock(false);
            myPathRooms.add(currentRoom);
            currentRatio = (double) myPathRooms.size() / size;
        }
    }

    private List<Direction> getValidDirections(final Room theRoom) {
        List<Direction> directions = new ArrayList<>();
        for (Direction d : Direction.values()) {
            // checks if horizontal or vertical direction
            // then checks if new index is in bounds
            // and thennn checks if the room is already searched
            if (d.dx() != 0) {
                final int x = theRoom.getCol() + d.dx();
                if (x < myRooms[0].length && x >= 0) {
                    directions.add(d);
                }
            } else {
                final int y = theRoom.getRow() + d.dy();
                if (y < myRooms.length && y >= 0) {
                    directions.add(d);
                }
            }
        }
        return directions;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Room[] myRoom : myRooms) {
            for (int j = 0; j < myRooms[0].length; j++) {
                s.append(myRoom[j].roomToChar());
            }
            s.append("\n");
        }
        return s.toString();
    }
}

// break time 8:50 pm im so tired in this killer heat

/*
 10:45 pm -
 testing program
 wondered how the frick do I keep track of the start point index??
      solved that with setStart returning an array
      then ya pass that array to createWinningPath
 omg but that dumb dumbbb?? why not make x and y vars in each room duhh
 i would tech need that for creating paths n all
 alright now figuring out how to choose directions
 btw ive been snatching code from assignment 3 in TCSS 305
 oho I almost forgot creating paths should not go to already made paths

 12:00 AM
 I have quite a bit of untested code oh me oh myyy
 I just need it to make one lil cute path...
 I'm also tryna keep in mind encapsulation stuff while
    writing these dang methods
 Doing fun debugging, I'm kinda getting somewhere!!
 Got the path generation right... I forgot to add
    stuff for skipping already made paths lol
 on second thought, maybe not??
    because I ran into a prob where the path may have
    coiled in on itself and couldn't go back
    (maybe can be fixed)

ok, so far, there are some interesting mazes
ideally I'd like it to be more spread out
since it often bunches up on one side
but I'll get to that later
omg yea and more paths cuz gotta make it a-mazing lol
Bedtime bros

Oh yea next time I gotta do is uhh deal with the doors
Maybe I'll "open" the doors as the path is created?

12:27 AM
 */