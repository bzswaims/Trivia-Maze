//TODO Make an actual icon for the game.

//What I need to do is create panels, the panels will each house the different classes.
//trivia mazeGUI will pass the frame down to the mainMenu class, and well pretty much pass the JFrame around the program
//from there when main menu clicks a button I can pass the JFrame into a new class and adjust it there.

//instead I can pass in the JFrame and return the JFrame, and set the JFrame into the returned JFrame

package view;

import model.AbstractQuestion;
import model.Room;
import model.TrueFalseQuestion;

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
     * Background image with title.
     */
    private static final ImageIcon BACKGROUND =
            new ImageIcon("files/title_screen.png");

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
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Finds 1/3 of the width of the screen.
     */
    private static final int WIDTH = (int) SCREEN_SIZE.getWidth() / 3;

    /**
     * Finds 1/3 of the height of the screen.
     */
    private static final int HEIGHT = (int) SCREEN_SIZE.getHeight() / 3;

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
     * Main panel with menu and background.
     */
    private final JPanel myMainPanel;

    /**
     * Panel with background image.
     */
    private final JPanel myBackgroundPanel;

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
        myMainPanel = new JPanel() {
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        myBackgroundPanel = new JPanel() {
            /**
             * Draws the background image.
             * @param g the <code>Graphics</code> object to protect.
             */
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = BACKGROUND.getImage();
                // Draw the image at its original size centered
                int x = (getWidth() - BACKGROUND.getIconWidth()) / 2;
                int y = (getHeight() - BACKGROUND.getIconHeight()) / 2;

                g.drawImage(image, x, y, this);
            }

            /**
             * Prevents image from being scaled.
             * @return Dimension.
             */
            @Override
            public Dimension getPreferredSize() {
                // Set preferred size to the image size
                return new Dimension(BACKGROUND.getIconWidth(),
                        BACKGROUND.getIconHeight());
            }
        };

        myQuestionDisplay = new QuestionDisplay();
        myOptionsPanel = new OptionsPanel();
        //I hope this is working, this shit is like magic to me.
        myMainMenu.addPropertyChangeListener(myPCListener);
        myNavBar.addPropertyChangeListener(myPCListener);
        AbstractQuestion temp = new TrueFalseQuestion(2);
        temp.setQuestion("Testing");
        temp.setCorrectAnswer("True");
        myQuestionDisplay.setQuestion(temp);
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
                    myFrame.remove(myMainPanel);

                    JPanel gamePanel = new JPanel();
                    JPanel viewPanel = new JPanel();
                    viewPanel.add(myBoard, BorderLayout.CENTER);
                    viewPanel.add(myOptionsPanel, BorderLayout.EAST);
                    gamePanel.add(myNavBar.getNavBar(), BorderLayout.SOUTH);
                    myFrame.add(viewPanel, BorderLayout.CENTER);
                    myFrame.add(gamePanel, BorderLayout.SOUTH);

                    JPanel informationPanel = new JPanel();
                    informationPanel.setLayout(new GridLayout(2, 1, 2, 2));
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
        myMainPanel.setBackground(DARK);
        myMainPanel.setLayout(new OverlayLayout(myMainPanel));
        myBackgroundPanel.setOpaque(false);
        myBackgroundPanel.setMaximumSize(new Dimension(BACKGROUND.getIconWidth(),
                BACKGROUND.getIconHeight()));

        myMainPanel.add(myMainMenu.getMenu());
        myMainPanel.add(myBackgroundPanel);
        myFrame.add(myMainPanel);
        myMainMenu.setColors(PURPLE, DARK);
        myFrame.setSize(new Dimension(BACKGROUND.getIconWidth(),
                BACKGROUND.getIconHeight()));
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

    public void setMapValues(final int theRows, final int theCols) {
        myMinimap.setUpMap(theRows, theCols);
    }

    public void showRoom(final int theRow, final int theCol) {
        myMinimap.addRoomTile(theRow, theCol);
    }

    public void getMapToString() {
        System.out.println(myMinimap.toString());
    }

    public void movePlayer(final int theDirection) {
        myMinimap.movePlayerSpot(theDirection);
    }
}
