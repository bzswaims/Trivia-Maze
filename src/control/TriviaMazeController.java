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
import model.Maze;
import view.TriviaMazeGui;

/**
 * Controls data flow between model and view.
 *
 * @author Abbygaile Yrojo
 * @author Zane Swaims
 * @version July 30, 2024
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
        myView.setMapValues(myMaze.getRows(), myMaze.getCols());
        myView.showRoom(myMaze.getCurrentRoom().getRow(), myMaze.getCurrentRoom().getCol());

        //TODO remove
        System.out.println(myMaze);
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
            // for when direction changes
            String name = theEvt.getPropertyName();
            if (name.equals("Save")) {
                //creates a save file
                createSave();
            } else if (name.equals("Load")) {
                //loads a save file
                loadSave();
            } else if (name.equals("Movement")) {
                String value = theEvt.getNewValue().toString();
                if (value.equals("left") || value.equals("right")) {
                    myMaze.setCurrentDirection(value);
                } else if (value.equals("forward")) {
                    final int state = myMaze.getDoorLockState();
                    System.out.println("Door state: " + state);
                    if (state == 2) {
                        myMaze.moveForward();
                        myView.showRoom(myMaze.getCurrentRoom().getRow(),
                                        myMaze.getCurrentRoom().getCol());
                        myView.movePlayer(myMaze.getCurrentDirection());

                        myView.updateView("up");
                    } else if (state == 1) {
                        // disable nav bar
                        System.out.println("Show question");
                        myView.setUpQuestion(
                                myMaze.getCurrentQuestion().getType(),
                                myMaze.getCurrentQuestion().getQuestion(),
                                myMaze.getAnswers());
                    }
                }

            } else if (name.equals("Answer")) {
                System.out.println("Correct? : " + myMaze.isCorrect(theEvt.getNewValue().toString()));
                if (myMaze.isCorrect(theEvt.getNewValue().toString())) {
                    myMaze.setDoorState(2);
                    myView.stopQuestion();
                    // myView.stopQuestion()
                      // clear QAPanel
                      // re-enable navbar
                      // update view
                } else {
                    myMaze.setDoorState(0);
                    myView.stopQuestion();
                }
            }
        };
    }
}