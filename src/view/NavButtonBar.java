/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

//TODO make images a decent size, or variable (small medium large depending on screen size)
//I find 150px or 200x square work well probably
//TODO potentially make the buttons their own object like filters from project 4 in 305
//TODO remove the test object myNavigation.
//TODO remove the test method buttonMaker.
//TODO add a minimap/map button.

package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
     * List for storing the buttons.
     */
    private final List<JButton> myNavButtons;

    /**
     * JPanel to store the buttons onto.
     */
    private final JPanel myNavBar;

    /**
     * Array of listeners for the buttons.
     */
    private final ActionListener[] myListeners;

    /**
     * To notify listener of forward movement.
     */
    private final PropertyChangeSupport myPCSupport;

    /**
     * Constructor.
     */
    public NavButtonBar()
    {
        myNavButtons = new ArrayList<>();
        myNavBar = new JPanel();

        myPCSupport = new PropertyChangeSupport(this);

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
     * Creates the buttons proper.
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
        return new ActionListener[] {theEvent -> {
            //left button
            setValue("left");
        }, theEvent -> {
            // up button
            setValue("forward");
        }, theEvent -> {
            // right button
            setValue("right");
        }, theEvent -> {
            // interact
            //
            //can delete this since no items
            //
            //TODO: replace with a save button
        }//, theEvent -> {
            //Inventory
            //
            //can delete this since no items
            //
            //this should open inventory if it is not open, and close it if it is open I think,
            //or it could be implemented like help on the menus
        //    myNavBar.removeAll();
            //setUpMainMenu();
        //    myNavBar.revalidate();
        //    myNavBar.repaint();
        //}
        };
    }

    /**
     * Methods that compiles the buttons into the panel.
     */
    private void createButtons() {
        final String[] imageNames = {"left", "up", "right", "hand"}; //, "bag"
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

    /**
     * Add property change listener.
     * @param theListener the property change listener.
     */
    public void addPropertyChangeListener(
            final PropertyChangeListener theListener) {
        myPCSupport.addPropertyChangeListener(theListener);
    }

    /**
     * Notifies listener of upward movement.
     * @param theNewValue String.
     */
    public void setValue(String theNewValue) {
        String oldValue = "Still";
        myPCSupport.firePropertyChange("Movement",
                oldValue, theNewValue);
    }
}
