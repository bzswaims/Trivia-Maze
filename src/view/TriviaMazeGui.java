//TODO clean up my code, its so not good

package view;

//need to reorganize these later.
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/**
 * Starts the GUI for the Trivia Maze. Still has not been tested
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class TriviaMazeGui implements PropertyChangeListener {

    /**
     * title of the game to be used all over
     */
    private final String myTitle = "Trivia Maze Title Placeholder";

    /**
     * The icon for the window. bag is a place holder
     */
    private final ImageIcon myIcon = new ImageIcon("files/bag.png");

    /**
     * Fetches the screen size.
     */
    private final Dimension myScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Finds 1/3 of the width of the screen.
     */
    private final int myWidth = (int) myScreenSize.getWidth() / 3;

    /**
     * Finds 1/3 of the height of the screen.
     */
    private final int myHeight = (int) myScreenSize.getHeight() / 3;

    /**
     * The window.
     */
    private final JFrame myFrame;

    /**
     * The board to draw to.
     */
    private final Board myBoard;

    //jpanel for the nav bar
    private final NavButtonBar myNavBar;

    //jpanel for menu
    private final MainMenu myMainMenu;

    /**
     * Constructor.
     */
    public TriviaMazeGui() {
        super();

        myFrame = new JFrame(myTitle);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setIconImage(myIcon.getImage());

        myBoard = new Board();
        myMainMenu = new MainMenu();
        myNavBar = new NavButtonBar();

        start();
    }

    //This barely even runs the window, purely at testing stages.
    /**
     * Performs all tasks necessary to display the UI.
     */
    protected void start() {
        myFrame.add(myMainMenu.getMenu(), BorderLayout.CENTER);

        myFrame.setSize(new Dimension(myWidth, myHeight));
        myFrame.setLocationRelativeTo(null);

        myFrame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
