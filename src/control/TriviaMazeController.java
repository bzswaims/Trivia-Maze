/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package control;

import model.Maze;
import view.TriviaMazeGui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Controls data flow between model and view.
 *
 * @author Abbygaile Yrojo
 * @version July 29, 2024
 */
public class TriviaMazeController {
    private TriviaMazeGui myView;
    private Maze myMaze;
    private PropertyChangeListener myListener;

    public TriviaMazeController(final Maze theMaze,
                                final TriviaMazeGui theView) {
        myMaze = theMaze;
        myView = theView;
        myListener = createListener();
        myView.addPropertyChangeListener(myListener);
    }

    public void start() {
        myMaze.assembleMaze(4, 4);
        myView.start();
        System.out.println(myMaze);
    }

    private PropertyChangeListener createListener() {
        return new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent theEvt) {
                // for when direction changes
                String name = theEvt.getPropertyName();
                if (name == "Movement") {
                    String value = theEvt.getNewValue().toString();
                    if (value == "left" || value == "right") {
                        myMaze.setCurrentDirection(value);
                    } else if (value == "forward") {
                        if (myMaze.moveForward()) {
                            myView.updateView("up");
                        }
                    }
                }
            }
        };
    }
}

/*
NOTES:
Controller should *use* the model/maze to *update* view

 */