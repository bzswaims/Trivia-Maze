/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Displays the help screen.
 *
 * @author Zane Swaims
 * @author Abbygaile Yrojo
 * @version 1.0
 */
public class Help extends JPanel {
    /**
     * Game info.
     */
    private static final String TEXT =
            "Welcome to Trivia Maze! Search for the exit by testing your " +
            "trivia knowledge with each door you attempt to enter. But we " +
            "warned- answer a question incorrectly, and the door will be " +
            "locked forever. There are items scattered in some of the rooms " +
            "to assist you. Good luck!";

    /**
     * Possible image for explaining things.
     */
    private final Image myImage;

    /**
     * Constructs the help screen.
     */
    public Help() {
        setPreferredSize(new Dimension(200, 400));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextArea text = new JTextArea(TEXT);
        text.setFont(Font.getFont("Tomorrow Regular"));
        text.setWrapStyleWord(true);
        text.setLineWrap(true);

        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File("files/helpmenu.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        myImage = bufferedImage.
                getScaledInstance(200, 200, Image.SCALE_DEFAULT);

        this.add(text);
        this.add(new JLabel(new ImageIcon(myImage)));

        setVisible(true);
    }
}

