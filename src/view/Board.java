package view;

//These need to be reorganized later
import javax.swing.JPanel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Displays the "board", that is what the user will see.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class Board extends JPanel{

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
        setBackground(Color.WHITE);
    }
}
