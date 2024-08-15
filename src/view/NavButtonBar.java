/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

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
 * @author Zane Swaims
 * @version 1.0
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
            //TODO: delete
        }
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
        for (JButton myNavButton : myNavButtons) {
            myNavBar.add(myNavButton);
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
