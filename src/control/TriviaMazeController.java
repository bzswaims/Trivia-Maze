/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package control;

import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Direction;
import model.Maze;
import view.TriviaMazeGui;

/**
 * Controls data flow between model and view.
 *
 * @author Abbygaile Yrojo
 * @author Zane Swaims
 * @author Mohammed
 * @version August 16, 2024
 */
public class TriviaMazeController {
    /**
     * To update view.
     */
    private final TriviaMazeGui myView;

    /**
     * File name for the save file.
     */
    private final String myFileName;

    /**
     * To use data in Maze.
     */
    private Maze myMaze;

    /**
     * Listener for view.
     */
    private final PropertyChangeListener myListener;

    /**
     * Construct the controller.
     * @param theMaze Maze for model.
     * @param theView TriviaMazeGui for view.
     */
    public TriviaMazeController(final Maze theMaze,
                                final TriviaMazeGui theView) {
        myMaze = theMaze;
        myView = theView;
        myFileName = "triviagame.sav";
        myListener = createListener();
        myView.addPropertyChangeListener(myListener);
    }

    /**
     * Start the game.
     */
    public void start() {
        myView.start();
        myView.setMapValues(myMaze.getRows());
        myView.showRoom(myMaze.getCurrentRoom().getRow(),
                myMaze.getCurrentRoom().getCol());

        for ( Direction dir : Direction.values()){
            if(myMaze.getCurrentRoom().getDoor(dir)!=null){
                myView.showDoor(myMaze.getCurrentRoom().getRow(),
                        myMaze.getCurrentRoom().getCol(),
                        dir,
                        myMaze.getCurrentRoom().getDoor(dir).getLockState());
            }
        }
    }

    /**
     * Creates the save file from serialization.
     */
    private void createSave() {
        try {
            FileOutputStream file = new FileOutputStream(myFileName);
            ObjectOutputStream out = new ObjectOutputStream(file);


            out.writeObject(myMaze);

            out.close();
            file.close();
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    /**
     * Loads in a save file and sets myMaze to the maze loaded in.
     */
    private void loadSave() {
        try {
            FileInputStream file = new FileInputStream(myFileName);
            ObjectInputStream in = new ObjectInputStream(file);

            myMaze = (Maze)in.readObject();

            in.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates listener for GUI.
     *
     * @return PropertyChangeListener.
     */
    private PropertyChangeListener createListener() {
        return theEvt -> {
            String name = theEvt.getPropertyName();
            if (name.equals("Save")) {
                createSave();
            } else if (name.equals("Load")) {
                loadSave();
            } else if (name.equals("Movement")) {
                String value = theEvt.getNewValue().toString();
                if (value.equals("left") || value.equals("right")) {
                    myMaze.setCurrentDirection(value);
                    myView.rotatePlayer(myMaze.getCurrentDirection());

                } else if (value.equals("forward")) {
                    final int state = myMaze.getDoorLockState();
                    if (state == 2) {
                        myMaze.moveForward();
                        myView.showRoom(myMaze.getCurrentRoom().getRow(),
                                        myMaze.getCurrentRoom().getCol());

                        for ( Direction dir : Direction.values()) {
                            if(myMaze.getCurrentRoom().getDoor(dir)!=null) {
                                myView.showDoor
                                        (myMaze.getCurrentRoom().getRow(),
                                        myMaze.getCurrentRoom().getCol(),
                                        dir,
                                        myMaze.getCurrentRoom().getDoor(dir)
                                                .getLockState());
                            }
                        }


                        myView.movePlayer(myMaze.getCurrentDirection());

                        myView.updateView("up");
                    } else if (state == 1) {
                        // disable nav bar
                        myView.setUpQuestion(
                                myMaze.getCurrentQuestion().getType(),
                                myMaze.getCurrentQuestion().getQuestion(),
                                myMaze.getAnswers());
                    }

                    if(myMaze.getCurrentRoom().isEnd()){
                        myView.win();
                    }
                }

            } else if (name.equals("Answer")) {
                if (myMaze.isCorrect(theEvt.getNewValue().toString())) {
                    myMaze.setDoorState(2);
                    myView.stopQuestion();
                } else {
                    myMaze.setDoorState(0);
                    myView.stopQuestion();
                }
                for ( Direction dir : Direction.values()){
                    if(myMaze.getCurrentRoom().getDoor(dir)!=null){
                        myView.showDoor(myMaze.getCurrentRoom().getRow(),
                                myMaze.getCurrentRoom().getCol(),
                                dir,
                                myMaze.getCurrentRoom().getDoor(dir)
                                        .getLockState());
                    }
                }
                if(myMaze.hasLost()){
                    myView.lost();
                }
            }
        };
    }
}