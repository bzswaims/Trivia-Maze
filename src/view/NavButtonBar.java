//TODO make buttons actually do things
//TODO make images a decent size, or variable (small medium large depending on screen size)
//I find 150px or 200x square work well proabbly
//TODO potentially make the buttons their own object like filters from project 4 in 305
//TODO remove the test object myNavigation.
//TODO remove the test method buttonMaker.
//TODO add a minimap/map button.

package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds the navigation bar for the game. This is used by the user
 * to navigate the maze.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class NavButtonBar {

    /**
     * This is for storing the icons used by the buttons.
     * This is for testing and should be removed.
     */
    private final List<ImageIcon> myNavigation;

    /**
     * List for storing the buttons.
     */
    private final List<JButton> myNavButtons;

    /**
     * JPanel to store the buttons onto.
     */
    private final JPanel myNavBar;

    /**
     * The board that will display what the user sees.
     */
    private final Board myBoard;

    /**
     * Array of listeners for the buttons.
     */
    private final ActionListener myListeners[];

    /**
     * Constructor.
     */
    NavButtonBar(Board theBoard)
    {
        myNavigation = new ArrayList<>();
        myNavButtons = new ArrayList<>();
        myNavBar = new JPanel();
        myBoard = theBoard;
        myNavBar.add(myBoard);

        myListeners = createListeners();
        createButtons();
        setUpNavBar();
    }

    /**
     * Accessor for the navigation bar.
     *
     * @return The navigation bar.
     */
    public JPanel getNavBar() {
        return myNavBar;
    }

    /**
     * Creates the buttons proper, this is for testing and should be removed.
     *
     * @param theIcon The icon attached to the button.
     *
     * @return The button proper.
     */
    public JButton buttonMaker(ImageIcon theIcon) {
        final JButton button = new JButton(theIcon);
        button.setEnabled(true);

        return button;
    }

    /**
     * This creates an array of action listeners.
     *
     * @return an array of action listeners for my buttons
     */
    private ActionListener[] createListeners() {
        return new ActionListener[]{new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //left button
                myBoard.left();
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                // up button
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                // right button
                myBoard.right();
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                // interact
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                // inventory
                //Inventory should open inventory if it is not open, and close it if it is open i think
                //or it could be implamented like help on the menus
                myNavBar.removeAll();
                //setUpMainMenu();
                myNavBar.revalidate();
                myNavBar.repaint();
            }
        }};
    }

    /**
     * Methods that compiles the buttons into the panel.
     */
    private void createButtons() {
        final String[] imageNames = {"left", "up", "right", "hand", "bag"};
        for (int i = 0; i < imageNames.length; i++) {
            JButton button = buttonMaker(new ImageIcon(
                    String.format("files/%s.png", imageNames[i])));
            button.addActionListener(myListeners[i]);
            myNavButtons.add(button);
        }
    }

    /**
     * Populates the menu bar.
     */
    private void setUpNavBar() {
        for (int i = 0; i < myNavButtons.size(); i++) {
            myNavBar.add(myNavButtons.get(i));
        }
    }
}
