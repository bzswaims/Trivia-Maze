//TODO Make an actual icon for the game.

//What I need to do is create panels, the panels will each house the different classes.
//trivia mazegui will pass the frame down to the mainmenu class, and well pretty much pass the jframe around the program
//from there when main menu clicks a button i can pass the jframe into a new class and adjust it there.

//instead I can pass in the jframe and return the jframe, and set the jframe into the returned jframe

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Starts the GUI for the Trivia Maze.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class TriviaMazeGui {

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
     * Used to load game.
     */
    private final PropertyChangeListener myPCListener;

    /**
     * To communicate with Controller.
     */
    private final PropertyChangeSupport myPCSupport;

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
        myNavBar = new NavButtonBar(myBoard);
        myPCListener = createPCListener();
        myPCSupport = new PropertyChangeSupport(this);

        myMainMenu.addPropertyChangeListener(myPCListener);
        myNavBar.addPropertyChangeListener(myPCListener);
    }

    /**
     * Create listener for player input.
     * @return PropertyChangeListener
     */
    private PropertyChangeListener createPCListener() {
        return new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent theEvt) {
                String value = theEvt.getNewValue().toString();
                if (value == "New game") {
                    myFrame.remove(myMainMenu.getMenu());
                    myFrame.add(myNavBar.getNavBar());
                    myFrame.revalidate();
                    myFrame.repaint();
                    myFrame.pack();
                    myFrame.setLocationRelativeTo(null);
                } else if (value == "forward" ||
                           value == "left" || value == "right") {
                    setValue(value);
                }
            }
        };
    }

    /**
     * Performs all tasks necessary to display the UI.
     */
    public void start() {
        myFrame.add(myMainMenu.getMenu(), BorderLayout.CENTER);

        myFrame.setSize(new Dimension(myWidth, myHeight));
        myFrame.setLocationRelativeTo(null);

        myFrame.setVisible(true);
    }

    /**
     * Adds PropertyChangeListener to PropertyChangeSupport.
     * @param theListener PropertyChangeListener.
     */
    public void addPropertyChangeListener(
            final PropertyChangeListener theListener) {
        myPCSupport.addPropertyChangeListener(theListener);
    }

    /**
     * Changes the menu by notifying listener.
     * @param theNewValue String.
     */
    public void setValue(String theNewValue) {
        String oldValue = "Still";
        this.myPCSupport.firePropertyChange("Movement",
                oldValue, theNewValue);
    }

    /**
     * Update board.
     * @param theChange String.
     */
    public void updateView(String theChange) {
        if (theChange == "up") {
            myBoard.up();
        }
    }
}
