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
        myView.setMinimapRooms(myMaze.getRooms());
        myView.setMinimapCurrentRoom(myMaze.getCurrentRoom());
        System.out.println(myMaze);
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
                        if (state == 0) {
                            // say the door is locked somewhere
                        } else if (state == 1) {
                            // prompt question!!
                        } else if (state == 2) {
                            myView.updateView("up");
                        }
                    }
                    myView.setMinimapCurrentRoom(myMaze.getCurrentRoom());

                }
            }
        };
    }
}