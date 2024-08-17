/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Builds the main menu for the game.
 *
 * @author Zane Swaims
 * @author Abbygaile Yrojo
 * @version 1.0
 */
public class MainMenu {
    /** To add to buttons. */
    private final ActionListener[] myListeners;

    /** To store buttons in. */
    private final List<JButton> myMenuButtons;

    /** To show buttons in. */
    private final JPanel myMenuBar;

    /**
     * For notifying changes to listeners.
     */
    private final PropertyChangeSupport myPCSupport =
            new PropertyChangeSupport(this);

    /**
     * Constructor.
     */
    public MainMenu() {
        myMenuButtons = new ArrayList<>();
        myMenuBar = new JPanel();
        myListeners = createListeners();
        createButtons();
        setUpMainMenu();
    }

    /**
     * Accessor to pass to finished menu bar back.
     * @return the completed menu bar.
     */
    public JPanel getMenu() {
        return myMenuBar;
    }

    /**
     * Creates an array of ActionListeners for buttons.
     * @return ActionListener array.
     */
    private ActionListener[] createListeners() {
        return new ActionListener[]{theEvent -> {
            myMenuBar.setVisible(false);
            setValue("New game");
        }, theEvent -> {
            // load
            myMenuBar.setVisible(false);
            setValue("Load");
            setValue("New game");
        }, theEvent -> {
            //help
            myMenuBar.removeAll();
            myMenuBar.add(new Help());
            JButton button = myMenuButtons.get(4);
            myMenuBar.add(button);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(200, 40));
            myMenuBar.revalidate();
            myMenuBar.repaint();
        }, theEvent -> {
            //exit
            System.exit(0);
        }, theEvent -> {
            // back (exit help screen)
            myMenuBar.removeAll();
            setUpMainMenu();
            myMenuBar.revalidate();
            myMenuBar.repaint();
        }};
    }

    /**
     * Methods that compiles the buttons into the panel.
     */
    private void createButtons() {
        final String[] names = {"New Game", "Continue",
                "Help", "Exit", "Back"};
        final Font font = new Font("Tomorrow SemiBold", Font.PLAIN, 24);
        for (int i = 0; i < names.length; i++) {
            JButton button = new JButton(names[i]);
            button.setEnabled(true);
            button.setFont(font);
            button.setFocusPainted(false);
            button.addActionListener(myListeners[i]);
            myMenuButtons.add(button);
        }
    }

    /**
     * Populates the menu bar.
     */
    private void setUpMainMenu() {
        myMenuBar.setLayout(null);
        myMenuBar.setVisible(true);
        myMenuBar.setVisible(true);
        myMenuBar.setLayout(new BoxLayout(myMenuBar, BoxLayout.Y_AXIS));
        myMenuBar.setOpaque(false);
        myMenuBar.add(Box.createVerticalGlue());
        for (int i = 0; i < myMenuButtons.size() - 1; i++) {
            final JButton button = myMenuButtons.get(i);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(200, 40));
            myMenuBar.add(button);
            myMenuBar.add(Box.createVerticalStrut(10));
        }
        myMenuBar.add(Box.createVerticalStrut(20));
    }

    /**
     * Add property change listener.
     * @param theListener the action listener.
     */
    public void addPropertyChangeListener(
            final PropertyChangeListener theListener) {
        this.myPCSupport.addPropertyChangeListener(theListener);
    }

    /**
     * Changes the menu by notifying listener.
     * @param theNewValue String.
     */
    public void setValue(final String theNewValue) {
        String oldValue = "Main menu";
        this.myPCSupport.firePropertyChange("Change view",
                oldValue, theNewValue);
    }

    /**
     * Sets the colors for the main menu.
     * @param thePurple the purple used for the menu.
     * @param theDark the darker color used for the menu.
     */
    public void setColors(final Color thePurple, final Color theDark) {
        for (JButton button : myMenuButtons) {
            button.setForeground(thePurple);
            button.setBackground(theDark);
        }
        myMenuBar.setBackground(theDark);
    }
}