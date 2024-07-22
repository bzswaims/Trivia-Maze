//TODO remove the array of 4 boards, and actually use a different class to compile the maze proper.
//TODO make pov a better field, for now it is testing

package view;

//These need to be reorganized later
import java.awt.*;
import java.beans.PropertyChangeSupport;
import javax.swing.*;

/**
 * Displays the "board", that is what the user will see.
 * This handles updating the graphics so the user knows where they are.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class Board extends JPanel{

    //testing fields
    private ImageIcon[] myRoom;
    //pov is what board we are looking at in the array.
    int pov = 0;

    /**
     * Property change support object.
     * I do not know if we will need this, but just in case.
     */
    private final PropertyChangeSupport myPCS = new PropertyChangeSupport(this);

    /**
     * Constructor.
     */
    Board() {
        super();
        myRoom = new ImageIcon[4];
        myRoom[0] = new ImageIcon("boards/north.png");
        myRoom[1] = new ImageIcon("boards/east.png");
        myRoom[2] = new ImageIcon("boards/south.png");
        myRoom[3] = new ImageIcon("boards/west.png");

        start();
  ;  }

    /**
     * This will display the board proper.
     *
     * @param theGraphics the graphics we are using the draw.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);

        final Graphics2D g2d = (Graphics2D) theGraphics;
        setBackground(Color.WHITE);
    }

    private void start () {
        add(new JLabel(myRoom[pov]));
        setVisible(true);
    }

    public void left() {
        if(pov == 0) {
            pov = 3;
        }
        else {
            pov--;
        }

        turn();
    }

    public void right() {
        if(pov == 3) {
            pov = 0;
        }
        else {
            pov++;
        }

        turn();
    }

    private void turn() {
        removeAll();
        add(new JLabel(myRoom[pov]));
        revalidate();
        repaint();
    }

    public void up() {
        //this method will go up to the door
        //if unlocked go thro the door
        //if locked, prompt player
        //if have not been answered, prompt with question
        //if there is nothign there do nothing
    }

    public void interact() {
        //
    }

    public void inventory() {
        //
    }
}
