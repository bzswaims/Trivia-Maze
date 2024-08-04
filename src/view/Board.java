/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

//TODO remove the array of 4 boards, and actually use a different class to compile the maze proper.
//TODO make pov a better field, for now it is for testing

package view;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;

/**
 * Displays the "board", that is what the user will see.
 * This handles updating the graphics so the user knows where they are.
 *
 * @author Zane Swaims (bzswaims@uw.edu), Abbygaile Yrojo
 * @version 0.2
 */
public class Board extends JPanel{
    /**
     * Array of player POVs.
     */
    private final ImageIcon[] myRoom;

    /**
     * Index of current POV.
     */
    private int myPov;

    /**
     * For updating player POV.
     */
    private final JLabel myCurrentImage;

    /**
     * Constructor.
     */
    Board() {
        super();
        myRoom = new ImageIcon[6];
        myRoom[0] = new ImageIcon("boards/north.png");
        myRoom[1] = new ImageIcon("boards/east.png");
        myRoom[2] = new ImageIcon("boards/south.png");
        myRoom[3] = new ImageIcon("boards/west.png");
        myRoom[4] = new ImageIcon("boards/turn.png");
        myRoom[5] = new ImageIcon("boards/enterdoor.png");
        myPov = 0;
        myCurrentImage = new JLabel(myRoom[myPov]);
        start();
    }

    /**
     * This will display the board proper.
     *
     * @param theGraphics the graphics we are using the draw.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

    }

    /**
     * Starts displaying the board.
     */
    private void start () {
        setBackground(Color.WHITE);
        add(myCurrentImage);
        setVisible(true);
    }

    /**
     * Faces the user to the left.
     */
    public void left() {
        if(myPov == 0) {
            myPov = 3;
        }
        else {
            myPov--;
        }

        turn();
    }

    /**
     * Faces the user to the right.
     */
    public void right() {
        if(myPov == 3) {
            myPov = 0;
        }
        else {
            myPov++;
        }

        turn();
    }

    /**
     * Turns the board with animation.
     */
    private void turn() {
        myCurrentImage.setIcon(myRoom[4]);
        delay();
    }

    public int getPov() {
        return myPov;
    }

    /**
     * Moves through/towards the door in front of the user.
     */
    public void up() {
        //this method will go up to the door
        //if unlocked go through the door
        //if locked, prompt player
        //if have not been answered, prompt with question
        //if there is nothing there do nothing

        myCurrentImage.setIcon(myRoom[5]);
        delay();
    }

    private void delay() {
        (new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            try {
                SwingUtilities.invokeAndWait(() -> {
                    myCurrentImage.setIcon(myRoom[myPov]);
                });
            } catch (InterruptedException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        })).start();
    }

    /**
     * Interacts with what is in front of the user.
     * In the case of an item in the room, picks it up
     * In the case of a door, opens the question
     *
     * can be deleted since we have no items and just have up button do the same thing
     */
    public void interact() {
        //
    }

    /**
     * Opens the inventory for the user to see what they have.
     *
     * can be deleted since no items
     */
    public void inventory() {
        //
    }
}
