//TODO check if closing java virtual machine is ok.
//TODO actually map the new game button to launch the game.
//TODO find a way to clear the screen and refill it with the help screen.
//TODO remove the myMenu list and the createButton method, both used for testing.

package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds the main menu for the game.
 *
 * @author Zane Swaims (bzswaims@uw.edu), Abbygaile Yrojo
 * @version 0.2
 */
public class MainMenu {
    /* To add to buttons. */
    private final ActionListener[] myListeners;

    /** To store buttons in. */
    private final List<JButton> myMenuButtons;

    /* To show buttons in. */
    private final JPanel myMenuBar;

    /**
     * Constructor.
     */
    public MainMenu() {
        myMenuButtons = new ArrayList<>();
        myMenuBar = new JPanel(new GridLayout(0, 1));
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
                // new
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                // load
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                // this and action on the back button could go in a method hmmm
                myMenuBar.removeAll();
                myMenuBar.add(new Help());
                myMenuBar.add(myMenuButtons.get(4));
                myMenuBar.revalidate();
                myMenuBar.repaint();
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                System.exit(1);
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
        final String[] imageNames = {"new", "load", "help", "exit", "back"};
        for (int i = 0; i < imageNames.length; i++) {
            JButton button = buttonMaker(new ImageIcon(
                    String.format("files/%s.png", imageNames[i])));
            button.addActionListener(myListeners[i]);
            myMenuButtons.add(button);
        }
    }

    /**
     * Populates the menu bar.
     */
    private void setUpMainMenu() {
        for (int i = 0; i < myMenuButtons.size() - 1; i++) {
            myMenuBar.add(myMenuButtons.get(i));
        }
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
}