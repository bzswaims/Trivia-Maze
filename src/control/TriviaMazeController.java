/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package control;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import model.Maze;
import view.TriviaMazeGui;

/**
 * Controls data flow between model and view.
 *
 * @author Abbygaile Yrojo
 * @version July 30, 2024
 */
public class TriviaMazeController {
    /**
     * To update view.
     */
    private final TriviaMazeGui myView;

    /**
     * To use data in Maze.
     */
    private final Maze myMaze;

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
        myView.getMapToString();

        System.out.println(myMaze);
        // myView.getMapToString();
    }

    /**
     * Creates listener for GUI.
     * @return PropertyChangeListener.
     */
    private PropertyChangeListener createListener() {
        return new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent theEvt) {
                // for when direction changes
                String name = theEvt.getPropertyName();
                if (name.equals("Movement")) {
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
                            myView.getMapToString();

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
                          // clear qapanel
                          // re-enable navbar
                          // update view
                    } else {
                        myMaze.setDoorState(0);
                        myView.stopQuestion();
                    }
                }
            }
        };
    }
}