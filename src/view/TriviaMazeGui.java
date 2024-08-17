/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package view;

import model.Direction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

/**
 * Starts the GUI for the Trivia Maze.
 *
 * @author Zane Swaims (bzswaims@uw.edu)
 * @author Abbygaile Yrojo
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
     * The panel containing the save help and quit buttons in game.
     */
    private final OptionsPanel myOptionsPanel;

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

    private final JPanel myQAPanel;

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
        myQAPanel = new QAPanel();
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

        myOptionsPanel = new OptionsPanel();
        //I hope this is working, this shit is like magic to me.
        myMainMenu.addPropertyChangeListener(myPCListener);
        myNavBar.addPropertyChangeListener(myPCListener);
        myOptionsPanel.addPropertyChangeListener(myPCListener);
    }


    /**
     * Create listener for player input.
     * @return PropertyChangeListener
     */
    private PropertyChangeListener createPCListener() {
        return theEvt -> {
            String newValue = theEvt.getNewValue().toString();
            if(newValue.equals("Save")) {
                myPCSupport.firePropertyChange("Save", 0, 1);
            } else if(newValue.equals("Load")) {
                myPCSupport.firePropertyChange("Load", 0, 1);
            }
            if (newValue.equals("New game")) {
                myFrame.remove(myMainPanel);
                myFrame.setBackground(DARK);

                JPanel gamePanel = new JPanel();
                gamePanel.setLayout(new GridBagLayout());
                // this acts as the pointer for setting stuff
                GridBagConstraints c = new GridBagConstraints();
                c.weightx = 0.5;
                c.insets = new Insets(5,5,5,5);  // padding

                // board
                c.gridx = 0;
                c.gridy = 0;
                gamePanel.add(myBoard, c);

                // movement
                c.gridx = 0;
                c.gridy = 1;
                gamePanel.add(myNavBar.getNavBar(), c);

                // status / interactable
                c.gridx = 1;
                c.gridy = 0;
                gamePanel.add(myOptionsPanel, c);

                // question / answer boxes
                c.gridx = 2;
                c.gridy = 0;
                gamePanel.add(myQAPanel, c);

                // map
                c.gridx = 2;
                c.gridy = 1;
                gamePanel.add(myMinimap, c);

                myFrame.add(gamePanel);

                myFrame.pack();
                myFrame.setLocationRelativeTo(null);
                myFrame.repaint();
            } else if (newValue.equals("forward") ||
                       newValue.equals("left") || newValue.equals("right")) {
                if (newValue.equals("left")) {
                    myBoard.left();
                } else if (newValue.equals("right")) {
                    myBoard.right();
                }
                setValue("Movement", newValue);
            } else if (theEvt.getPropertyName().equals("Answer")) {
                setValue("Answer", newValue);
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
        ((QAPanel) myQAPanel).setColors(PURPLE, DARK);
        myQAPanel.addPropertyChangeListener(myPCListener);

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
    private void setValue(final String theProperty,
                         final String theNewValue) {
        this.myPCSupport.firePropertyChange(theProperty,
                "TriviaMazeGui", theNewValue);
    }

    /**
     * Update board.
     * @param theChange String.
     */
    public void updateView(final String theChange) {
        if (theChange.equals("up")) {
            myBoard.up();
        }
    }

    /**
     * Lost board.
     */
    public void lost() {
        myBoard.lose();
        myNavBar.getNavBar().removeAll();
        myNavBar.getNavBar().repaint();
        myBoard.repaint();
    }

    /**
     * Won board.
     */
    public void win() {
        myBoard.win();
        myNavBar.getNavBar().removeAll();
        myNavBar.getNavBar().repaint();
        myBoard.repaint();
    }

    /**
     * Sets the size of the minimap.
     * @param theRows the number of rows in the map.
     */
    public void setMapValues(final int theRows) {
        myMinimap.setUpMap(theRows);
    }

    /**
     * adds a room to the minimap.
     * @param theRow the number of rows in the map
     * @param theCol the number of columns in the map
     */
    public void showRoom(final int theRow, final int theCol) {
        myMinimap.addRoomTile(theRow, theCol);
    }

    /**
     * adds a door to the minimap.
     * @param theRow the number of rows in the map
     * @param theCol the number of columns in the map
     * @param theDirection the number representing door's direction
     * @param theState the number representing door's state
     */
    public void showDoor(final int theRow, final int theCol,
                         final Direction theDirection, final int theState) {
        myMinimap.addDoorTile(theRow, theCol, theDirection, theState);
    }

    /**
     * Moves the player icon on the minimap.
     * @param theDirection the direction to move the player icon in.
     */
    public void movePlayer(final int theDirection) {
        myMinimap.movePlayerSpot(theDirection);
    }

    /**
     * Rotate the player icon on the minimap.
     * @param theDirection the direction to rotate the player icon in.
     */
    public void rotatePlayer(final int theDirection) {
        myMinimap.rotatePlayer(theDirection);
    }

    /**
     * Sets up the question for the QA panel.
     * @param theType the type of question being set up.
     * @param theQuestion the question text to be displayed.
     * @param theAnswers the array of answers for the question.
     */
    public void setUpQuestion(final int theType, final String theQuestion,
                              final String[] theAnswers) {
        myNavBar.getNavBar().setEnabled(false);
        ((QAPanel) myQAPanel).setQuestion(theType, theQuestion, theAnswers);
    }

    /**
     * Removes the question from the panel.
     */
    public void stopQuestion() {
        myNavBar.getNavBar().setEnabled(true);
        ((QAPanel) myQAPanel).clearQuestion();
    }
}
