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
import java.awt.*;
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

    private final Board myBoard;

    /**
     * Constructor.
     */
    NavButtonBar()
    {
        myNavigation = new ArrayList<>();
        myNavButtons = new ArrayList<>();
        myNavBar = new JPanel();
        myBoard = new Board();

        createButtons();
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
     * Mass creates buttons and puts them into the JPanel.
     */
    private void createButtons() {
        //I will likely use this for left forward and right since they are similar.
        myNavigation.add(new ImageIcon("files/left.png"));
        myNavigation.add(new ImageIcon("files/up.png"));
        myNavigation.add(new ImageIcon("files/right.png"));
        //The rest of the buttons will need to be done one by one since they are all different.
        myNavigation.add(new ImageIcon("files/hand.png"));
        myNavigation.add(new ImageIcon("files/bag.png"));

        for(final ImageIcon i : myNavigation) {
            myNavButtons.add(buttonMaker(i));
        }

        for (final JButton b : myNavButtons) {
            myNavBar.add(b);
        }
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
                System.exit(1);
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
}
