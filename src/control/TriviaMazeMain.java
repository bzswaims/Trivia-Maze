package control;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.TriviaMazeGui;

/**
 * Starts the trivia maze. Still has not been tested
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 *         Abby Yrojo
 *         Mahammod
 * @version 0.1
 */
public final class TriviaMazeMain {

    /* private constructor to inhibit instantiation. */
    private TriviaMazeMain() {
        // do not instantiate objects of this class
        throw new IllegalStateException();
    }

    /**
     * Set the look and feel for the GUI program.
     */
    private static void setLookAndFeel() {

        try {

            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

        } catch (final UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException");
        } catch (final ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (final InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (final IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        }

    }

    /**
     * The start point for the PowerPaint application.
     *
     * @param theArgs command line arguments - ignored in this program
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLookAndFeel();
                new TriviaMazeGui();
            }
        });
    }

}
