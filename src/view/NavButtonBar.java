//TODO make buttons actually do things
//TODO make images a decent size, or variable (small medium large depending on screen size)
//I find 150px or 200x square work well proabbly
//TODO potentially make the buttons their own object like filters from project 4 in 305


package view;

//to be cleaned up later and made proper
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds the navigation bar for the game.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class NavButtonBar {

    //For the attaching objects to the buttons
    private final List<ImageIcon> myNavigation;

    //for storing the buttons proper
    private final List<JButton> myNavButtons;

    //the bar proper
    private final JPanel myNavBar;

    /**
     * Constructor.
     */
    NavButtonBar()
    {
        myNavigation = new ArrayList<>();
        myNavButtons = new ArrayList<>();
        myNavBar = new JPanel();

        createButtons();
    }

    //accessor for the button panel
    public JPanel getNavBar() {
        return myNavBar;
    }

    //Pretty much just trying to populate some buttons and can fiddle with this later
    private void createButtons() {
        //I will likely use this for left forward and right, but make interact and item individually.
        myNavigation.add(new ImageIcon("files/left.png"));
        myNavigation.add(new ImageIcon("files/up.png"));
        myNavigation.add(new ImageIcon("files/right.png"));
        myNavigation.add(new ImageIcon("files/hand.png"));
        myNavigation.add(new ImageIcon("files/bag.png"));

        for(final ImageIcon i : myNavigation) {
            myNavButtons.add(buttonMaker(i));
        }

        for (final JButton b : myNavButtons) {
            myNavBar.add(b);
        }
    }

    public JButton buttonMaker(ImageIcon theIcon) {
        final JButton button = new JButton(theIcon);
        button.setEnabled(true);

        return button;
    }
}
