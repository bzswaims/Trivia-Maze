//TODO add in a back button to the screen.

package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Displays the help screen.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class Help {

    /**
     * Object to store the help screen in.
     */
    private JFrame myHelp;

    /**
     * Constructor.
     */
    public Help() {
        myHelp = new JFrame();
        myHelp.setLocationRelativeTo(null);
        myHelp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        start();
    }

    /**
     * This method builds the help screen.
     */
    public void start() {
        ImageIcon image = new ImageIcon("files/help.png");
        JLabel label = new JLabel(image);
        myHelp.add(label);

        //Add in a back button here.

        myHelp.pack();
        myHelp.setVisible(true);
    }
}
