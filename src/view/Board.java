/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package view;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Displays the "board", that is what the user will see.
 * This handles updating the graphics so the user
 * knows their current direction.
 *
 * @author Zane Swaims
 * @author Abbygaile Yrojo
 * @version 1.0
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
     * Stop board from updating;
     */

    private boolean myUpdate;

    /**
     * Constructor.
     */
    public Board() {
        super();
        myRoom = new ImageIcon[8];
        myRoom[0] = new ImageIcon("boards/north.png");
        myRoom[1] = new ImageIcon("boards/east.png");
        myRoom[2] = new ImageIcon("boards/south.png");
        myRoom[3] = new ImageIcon("boards/west.png");
        myRoom[4] = new ImageIcon("boards/turn.png");
        myRoom[5] = new ImageIcon("boards/enterdoor.png");
        myRoom[6] = new ImageIcon("boards/lose.png");
        myRoom[7] = new ImageIcon("boards/win.png");
        myPov = 0;
        myCurrentImage = new JLabel(myRoom[myPov]);
        myUpdate = true;
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

    /**
     * Moves through/towards the door in front of the user.
     */
    public void up() {
        myCurrentImage.setIcon(myRoom[5]);
        delay();
    }
    /**
     * Show lost scene;
     */
    public void lose() {
        myCurrentImage.setIcon(myRoom[6]);

        myUpdate = false;
    }
    /**
     * Show win scene;
     */
    public void win() {
        myCurrentImage.setIcon(myRoom[7]);
        myUpdate = false;
    }

    /**
     * For animation.
     */
    private void delay() {
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            try {
                SwingUtilities.invokeAndWait(() -> {
                    if(myUpdate)
                        myCurrentImage.setIcon(myRoom[myPov]);
                });
            } catch (InterruptedException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }).start();
    }
}
