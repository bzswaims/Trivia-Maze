package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class OptionsPanel extends JPanel {

    //need to make a panel that contains a save button, a quit button, and a help button.
    //They should be stacked vertically.
    //the order will be help, save, quit

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
        for (int i = 0; i < myOptionButtons.size(); i++) {
            add(myOptionButtons.get(i));
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
            //TODO
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
    public JButton buttonMaker(ImageIcon theIcon) {
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
