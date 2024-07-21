//TODO Make an actual icon for the game.
//TODO fix the way I am making the help screen. currently causes 2 way dependendencies with the method setScreen.

//What I need to do is create panels, the panels will each house the different classes.
//trivia mazegui will pass the frame down to the mainmenu class, and well pretty much pass the jframe around the program
//from there when main menu clicks a button i can pass the jframe into a new class and adjust it there.

//instead I can pass in the jframe and return the jframe, and set the jframe into the returned jframe

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Starts the GUI for the Trivia Maze.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class TriviaMazeGui implements PropertyChangeListener {

    /**
     * Title of the game.
     */
    private final String myTitle = "Trivia Maze Title Placeholder";

    /**
     * The icon for the window. bag is a placeholder.
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
    private static JFrame myFrame;

    /**
     * The board to draw to.
     */
    private final Board myBoard;

    /**
     * The navigation bar.
     */
    private final NavButtonBar myNavBar;

    /**
     * The main menu.
     */
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

    /**
     * Performs all tasks necessary to display the UI.
     */
    private void start() {
        myFrame.add(myMainMenu.getMenu(), BorderLayout.CENTER);

        myFrame.setSize(new Dimension(myWidth, myHeight));
        myFrame.setLocationRelativeTo(null);

        myFrame.setVisible(true);
    }

    //TODO THIS IS TERRIBLE NEED TO FIND A BETTER WAY
    /**
     * This takes in a JFrame to set the new screen as.
     *
     * This is likely the worst way to handle this.
     *
     * @param theNewScreen The new screen to display.
     */
    public static void setScreen(final JFrame theNewScreen) {
        myFrame.dispose();
        myFrame = theNewScreen;
    }

    //place holder because I do not know if I am using it yet.
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
