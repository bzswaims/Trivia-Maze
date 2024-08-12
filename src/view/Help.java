package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Displays the help screen.
 *
 * @author Zane Swaims (bzswaims@uw.edu), Abbygaile Yrojo
 * @version 0.4
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
        setPreferredSize(new Dimension(200, 100));
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

//    /**
//     * Draws text and images.
//     * @param theGraphics Graphics.
//     */
//    @Override
//    protected void paintComponent(final Graphics theGraphics) {
//        super.paintComponent(theGraphics);
//        final Graphics2D g2d = (Graphics2D) theGraphics;
//        g2d.setFont(Font.getFont("Tomorrow Regular"));
//        g2d.drawString(TEXT, 0, 0);
//        g2d.drawImage(myImage, 30, 100, null);
//    }
}

