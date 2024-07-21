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
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class MainMenu {

    /**
     * This is for the icons attaching to the buttons.
     * This is primarily for testing and to be removed.
     */
    private final List<ImageIcon> myMenu;

    /**
     * List to store the buttons.
     */
    private final List<JButton> myMenuButtons;

    /**
     * Object to store the buttons onto.
     */
    private final JPanel myMenuBar;

    /**
     * Constructor.
     */
    public MainMenu() {
        myMenu = new ArrayList<>();
        myMenuButtons = new ArrayList<>();
        myMenuBar = new JPanel(new GridLayout(0, 1));

        createButtons();
    }

    /**
     * Accessor to pass to finished menu bar back.
     * @return the completed menu bar.
     */
    public JPanel getMenu() {
        return myMenuBar;
    }

    //I will have to make each button differently sadly i think

    /**
     * Methods that compiles the buttons into the panel.
     */
    private void createButtons() {
        myMenu.add(new ImageIcon("files/new.png"));
        myMenu.add(new ImageIcon("files/load.png"));

        for(final ImageIcon i : myMenu) {
            myMenuButtons.add(buttonMaker(i));
        }

        for (final JButton b : myMenuButtons) {
            myMenuBar.add(b);
        }

        myMenuBar.add(createHelpButton(new ImageIcon("files/help.png")));
        myMenuBar.add(createExitButton(new ImageIcon("files/exit.png")));
    }

    /**
     * Creates the exit button.
     *
     * @param theIcon The image attached to the button.
     *
     * @return The completed exit button.
     */
    private JButton createExitButton(ImageIcon theIcon) {
        final JButton button = new JButton(theIcon);
        button.setEnabled(true);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //This line completely shuts down the java virtual machine
                //not sure if thats ok but for now it works.
                System.exit(1);
            }
        });

        return button;
    }

    /**
     * Creates the help button.
     *
     * @param theIcon The image attached to the button.
     *
     * @return The completed help button.
     */
    private JButton createHelpButton(ImageIcon theIcon) {
        final JButton button = new JButton(theIcon);
        button.setEnabled(true);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //need to clear the jframe, and attach onto it the image for the help screen
                //for now creates a new window. This is fine for testing.
                TriviaMazeGui.setScreen(new Help().getHelp());
                //TriviaMazeGui.revalidate();
            }
        });

        return button;
    }

    /**
     * This is for mass creation of buttons with no functions
     * this is primarily for testing and should be removed for the final product.
     *
     * @param theIcon The image attached to the button.
     *
     * @return The completed button.
     */
    public JButton buttonMaker(ImageIcon theIcon) {
        final JButton button = new JButton(theIcon);
        button.setEnabled(true);

        return button;
    }
}
