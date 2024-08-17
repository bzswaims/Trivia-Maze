/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package view;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a panel that contains a save button and an exit button.
 *
 * @author Zane Swaims
 * @version 1.0
 */
public class OptionsPanel extends JPanel {

    /**
     * List for storing the buttons.
     */
    private final List<JButton> myOptionButtons;

    /**
     * Array of listeners for the buttons.
     */
    private final ActionListener[] myListeners;

    /**
     * To communicate with Controller.
     */
    private final PropertyChangeSupport myPCSupport;

    /**
     * Constructor.
     */
    public OptionsPanel() {
        myOptionButtons = new ArrayList<>();
        myListeners = createListeners();

        myPCSupport = new PropertyChangeSupport(this);

        createButtons();
        setUpOptionsPanel();
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
    }

    private void setUpOptionsPanel() {
        for (JButton myOptionButton : myOptionButtons) {
            add(myOptionButton);
        }
    }

    /**
     * Returns an array of action listeners for the buttons.
     *
     * @return an array of action listeners for the buttons.
     */
    private ActionListener[] createListeners() {
        return new ActionListener[] {theEvent -> {
            //Save button
            myPCSupport.firePropertyChange("Save", 0, "Save");
        }, theEvent -> {
            //Quit button
            System.exit(0);
        }};
    }

    /**
     * Creates the buttons.
     */
    private void createButtons() {
        final String[] imageNames = {"saveicon", "quiticon"};
        for (int i = 0; i < imageNames.length; i++) {
            JButton button = buttonMaker(new ImageIcon(
                    String.format("files/%s.png", imageNames[i])));
            button.addActionListener(myListeners[i]);
            myOptionButtons.add(button);
        }
    }

    /**
     * Creates the buttons proper.
     *
     * @param theIcon The icon attached to the button.
     *
     * @return The button proper.
     */
    private JButton buttonMaker(final ImageIcon theIcon) {
        final JButton button = new JButton(theIcon);
        button.setEnabled(true);

        return button;
    }

    /**
     * Adds PropertyChangeListener to PropertyChangeSupport.
     * @param theListener PropertyChangeListener.
     */
    public void addPropertyChangeListener(
            final PropertyChangeListener theListener) {
        myPCSupport.addPropertyChangeListener(theListener);
    }
}
