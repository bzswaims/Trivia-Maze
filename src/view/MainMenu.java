//TODO check if closing java virtual machine is ok.
//TODO actually map the new game button to launch the game.
//TODO find a way to clear the screen and refill it with the help screen.
//TODO remove the myMenu list and the createButton method, both used for testing.

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds the main menu for the game.
 *
 * @author Zane Swaims (bzswaims@uw.edu), Abbygaile Yrojo
 * @version 0.2
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
        return new ActionListener[]{new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myMenuBar.setVisible(false);
                setValue("New game");
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                // load
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //help
                // this and action on the back button could go in a method hmmm
                myMenuBar.removeAll();
                myMenuBar.add(new Help());
                JButton button = myMenuButtons.get(4);
                myMenuBar.add(button);
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                button.setMaximumSize(new Dimension(200, 40));
                myMenuBar.revalidate();
                myMenuBar.repaint();
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //exit
                System.exit(0);
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                // back
                myMenuBar.removeAll();
                setUpMainMenu();
                myMenuBar.revalidate();
                myMenuBar.repaint();
            }
        }};
    }

    /**
     * Methods that compiles the buttons into the panel.
     */
    private void createButtons() {
        final String[] names = {"New Game", "Continue", "Help", "Exit", "Back"};
        for (int i = 0; i < names.length; i++) {
            JButton button = new JButton(names[i]);
            button.setEnabled(true);
            button.setFont(new Font("Tomorrow SemiBold", Font.PLAIN, 24));
//            JButton button = buttonMaker(new ImageIcon(
//                    String.format("files/%s.png", imageNames[i])));
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
     * This is for mass creation of buttons with no functions
     * this is primarily for testing and should be removed for the final product.
     *
     * @param theIcon The image attached to the button.
     * @return The completed button.
     */
    public JButton buttonMaker(ImageIcon theIcon) {
        final JButton button = new JButton(theIcon);
        button.setEnabled(true);

        return button;
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
    public void setValue(String theNewValue) {
        String oldValue = "Main menu";
        this.myPCSupport.firePropertyChange("Change view",
                oldValue, theNewValue);
    }

    public void setColors(final Color thePurple, final Color theDark) {
        for (int i = 0; i < myMenuButtons.size(); i++) {
            JButton button = myMenuButtons.get(i);
            button.setForeground(thePurple);
            button.setBackground(theDark);
        }
        myMenuBar.setBackground(theDark);
    }
}