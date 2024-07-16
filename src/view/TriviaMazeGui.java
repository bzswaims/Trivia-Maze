package view;

//need to reorganize these later.
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;

/**
 * Starts the GUI for the Trivia Maze. Still has not been tested
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class TriviaMazeGui implements PropertyChangeListener {

    private final String myTitle = "Trivia Maze Title Placeholder";

    /**
     * The window.
     */
    private final JFrame myFrame;

    /**
     * The board to draw to.
     */
    private final Board myBoard;

    /**
     * Constructor.
     */
    public TriviaMazeGui() {
        super();
        myFrame = new JFrame(myTitle);
        myBoard = new Board();

        start();
    }

    //This barely even runs the window, purely at testing stages.
    /**
     * Performs all tasks necessary to display the UI.
     */
    protected void start() {
        myFrame.add(myBoard, BorderLayout.CENTER);

        myFrame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
