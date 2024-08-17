/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package control;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Maze;
import view.TriviaMazeGui;

/**
 * Starts the trivia maze.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @author Abbygaile Yrojo
 * @author Mohammed
 * @version 1.0
 */
public final class TriviaMazeMain {
    /**
     *  Private constructor to inhibit instantiation.
     */
    private TriviaMazeMain() {
        // do not instantiate objects of this class
        throw new IllegalStateException();
    }

    /**
     * Set the look and feel for the GUI program.
     */
    private static void setLookAndFeel() {

        try {
            UIManager.setLookAndFeel
                    ("javax.swing.plaf.metal.MetalLookAndFeel");
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
        Maze maze = new Maze();
        TriviaMazeGui view = new TriviaMazeGui();
        TriviaMazeController controller = new TriviaMazeController(maze, view);

        EventQueue.invokeLater(() -> {
            setLookAndFeel();
            controller.start();
        });
    }

}
