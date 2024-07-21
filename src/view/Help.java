//TODO make the help button redraw the screen.

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays the help screen.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class Help {

    /**
     * Title of the game.
     */
    private final String myTitle = "Trivia Maze Title Placeholder";

    /**
     * The icon for the window. bag is a placeholder.
     */
    private final ImageIcon myIcon = new ImageIcon("files/bag.png");

    /**
     * Fetches the screen size.
     */
    private final Dimension myScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Finds 1/3 of the width of the screen.
     */
    private final int myWidth = (int) myScreenSize.getWidth() / 3;

    /**
     * Finds 1/3 of the height of the screen.
     */
    private final int myHeight = (int) myScreenSize.getHeight() / 3;

    /**
     * Object to store the help screen in.
     */
    private JFrame myHelp;

    /**
     * Constructor.
     */
    public Help() {
        myHelp = new JFrame(myTitle);
        myHelp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myHelp.setIconImage(myIcon.getImage());

        start();
    }

    /**
     * This method builds the help screen.
     */
    public void start() {
        myHelp.setSize(new Dimension(myWidth, myHeight));
        myHelp.setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon("files/help.png");
        JLabel label = new JLabel(image);
        myHelp.add(label);
        myHelp.add(createBackButton(new ImageIcon("files/back.png")), BorderLayout.SOUTH);
        myHelp.pack();
        myHelp.setVisible(true);
    }

    /**
     * Accessor to retreive the help screen.
     *
     * @return The help screen.
     */
    public JFrame getHelp(){
        return myHelp;
    }

    /**
     * Creates the back button.
     *
     * @param theIcon The image attached to the button.
     *
     * @return The completed back button.
     */
    private JButton createBackButton(ImageIcon theIcon) {
        final JButton button = new JButton(theIcon);
        button.setEnabled(true);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //I want this to clear the screen and repopulate with the main menu.
                //for now i need it to just close the window.
            }
        });

        return button;
    }
}
