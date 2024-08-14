//TODO Make an actual icon for the game.

//What I need to do is create panels, the panels will each house the different classes.
//trivia mazeGUI will pass the frame down to the mainMenu class, and well pretty much pass the JFrame around the program
//from there when main menu clicks a button I can pass the JFrame into a new class and adjust it there.

//instead I can pass in the JFrame and return the JFrame, and set the JFrame into the returned JFrame

package view;

import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.html.Option;

/**
 * Starts the GUI for the Trivia Maze.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @version 0.1
 */
public class TriviaMazeGui {

    /**
     * Color of inner components.
     */
    private static final Color PURPLE = new Color(173, 38, 194);

    /**
     * Color of background.
     */
    private static final Color DARK = new Color(7, 0, 47);

    /**
     * Title of the game.
     */
    private static final String TITLE = "Trivia Maze Title Placeholder";

    /**
     * The icon for the window. bag is a placeholder.
     */
    private static final ImageIcon BAG = new ImageIcon("files/bag.png");

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
     * The window.
     */
    private static JFrame myFrame;

    /**
     * The board to draw to.
     */
    private final Board myBoard;

    /**
     * The navigation bar.
     */
    private final NavButtonBar myNavBar;

    /**
     * The main menu.
     */
    private final MainMenu myMainMenu;

    /**
     * The minimap.
     */
    private final MiniMap myMinimap;

    /**
     * The panel containing the save help and quit buttons ingame.
     */
    private final OptionsPanel myOptionsPanel;

    /**
     * The Question info panel.
     */
    private final QuestionDisplay myQuestionDisplay;

    /**
     * Used to load game.
     */
    private final PropertyChangeListener myPCListener;

    /**
     * To communicate with Controller.
     */
    private final PropertyChangeSupport myPCSupport;

    /**
     * Constructor.
     */
    public TriviaMazeGui() {
        myFrame = new JFrame(TITLE);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setIconImage(BAG.getImage());
        myBoard = new Board();
        myMainMenu = new MainMenu();
        myNavBar = new NavButtonBar();
        myPCListener = createPCListener();
        myPCSupport = new PropertyChangeSupport(this);
        myMinimap = new MiniMap();
        myQuestionDisplay = new QuestionDisplay();
        myOptionsPanel = new OptionsPanel();
        //I hope this is working, this shit is like magic to me.
        myMainMenu.addPropertyChangeListener(myPCListener);
        myNavBar.addPropertyChangeListener(myPCListener);
    }


    /**
     * Create listener for player input.
     * @return PropertyChangeListener
     */
    private PropertyChangeListener createPCListener() {
        return new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent theEvt) {
                String value = theEvt.getNewValue().toString();
                if (value.equals("New game")) {
                    myFrame.remove(myMainMenu.getMenu());

                    JPanel gamePanel = new JPanel();
                    JPanel viewPanel = new JPanel();
                    viewPanel.add(myBoard, BorderLayout.CENTER);
                    viewPanel.add(myOptionsPanel, BorderLayout.EAST);
                    gamePanel.add(myNavBar.getNavBar(), BorderLayout.SOUTH);
                    myFrame.add(viewPanel, BorderLayout.CENTER);
                    myFrame.add(gamePanel, BorderLayout.SOUTH);

                    JPanel informationPanel = new JPanel();
                    informationPanel.setLayout(new GridLayout(2, 1));
                    informationPanel.add(myQuestionDisplay);
                    informationPanel.add(myMinimap);
                    myFrame.add(informationPanel, BorderLayout.EAST);

                    //myFrame.add(new JLabel(new ImageIcon("boards/treasure.png")), BorderLayout.EAST);
                    myFrame.pack();
                    myFrame.setLocationRelativeTo(null);
                    myFrame.repaint();
                } else if (value.equals("forward") ||
                           value.equals("left") || value.equals("right")) {
                    if (value.equals("left")) {
                        myBoard.left();
                    } else if (value.equals("right")) {
                        myBoard.right();
                    }
                    setValue(value);
                }
            }
        };
    }

    /**
     * Performs all tasks necessary to display the UI.
     */
    public void start() {
        usingCustomFonts();
        myFrame.setBackground(DARK);
        myFrame.add(myMainMenu.getMenu(), BorderLayout.CENTER);
        myMainMenu.setColors(PURPLE, DARK);
        myFrame.setSize(new Dimension(myWidth, myHeight));
        myFrame.setLocationRelativeTo(null);

        myFrame.setVisible(true);
    }

    private void usingCustomFonts() {
        GraphicsEnvironment ge;
        try {
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT,
                    new File("files/Tomorrow-Regular.ttf")).deriveFont(12f);
            ge.registerFont(font);
            font = Font.createFont(Font.TRUETYPE_FONT,
                    new File("files/Tomorrow-SemiBold.ttf")).deriveFont(24f);
            ge.registerFont(font);
        } catch (FontFormatException | IOException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

        /**
     * Adds PropertyChangeListener to PropertyChangeSupport.
     * @param theListener PropertyChangeListener.
     */
    public void addPropertyChangeListener(
            final PropertyChangeListener theListener) {
        myPCSupport.addPropertyChangeListener(theListener);
    }

    /**
     * Changes the menu by notifying listener.
     * @param theNewValue String.
     */
    public void setValue(String theNewValue) {
        String oldValue = "Still";
        this.myPCSupport.firePropertyChange("Movement",
                oldValue, theNewValue);
    }

    /**
     * Update board.
     * @param theChange String.
     */
    public void updateView(String theChange) {
        if (theChange.equals("up")) {
            myBoard.up();
        }
    }

    /**
     * Sets minimap room
     * @param rooms the rooms in the maze
     */
    public void setMinimapRooms(Room[][] rooms) {
        myMinimap.setRooms(rooms);
    }

    /**
     * Set the current room for the minimap
     * @param room the current room we are in
     */
    public void setMinimapCurrentRoom(Room room) {
        myMinimap.setCurrentRoom(room);
    }
}
