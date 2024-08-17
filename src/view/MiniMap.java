/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package view;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.SwingUtilities;
import model.Direction;

/**
 * Creates the minimap panel to be used on the main GUI for the game.
 *
 * @author Mohammed
 * @author Abbygaile Yrojo
 * @version 1.0
 */
public class MiniMap extends JPanel {
    /** Length of map. */
    private static final int MAP_LENGTH = 250;
    /** Pointers for direction. */
    private static final ImageIcon[] POINTERS =
            {new ImageIcon("map/map_up.png"),
                    new ImageIcon("map/map_right.png"),
                    new ImageIcon("map/map_down.png"),
                    new ImageIcon("map/map_left.png")};
    /** Door state icons. */
    private static final ImageIcon[] DOORS =
            {new ImageIcon("map/door_state_0.png"),
             new ImageIcon("map/door_state_1.png"),
             new ImageIcon("map/door_state_2.png")};
    /** Room tile icon. */
    private static final ImageIcon TILE = new ImageIcon("map/roomtile.png");
    /** Player icons. */
    private static final ImageIcon[] PLAYER =
            {new ImageIcon("map/player_idle1.png"),
             new ImageIcon("map/player_idle2.png"),
             new ImageIcon("map/player_distress.png")};
    /** Scaled size of tile. */
    private double myTileSize;
    /** List of room tiles with shapes and coordinates. */
    private final List<MyRoomTile> myRoomTiles;
    /** List of doors with shapes, coordinates, and directions. */
    private final List<MyDoorTile> myDoorTiles;
    /** Representation of player. */
    private Ellipse2D.Double myPlayerSpot;
    /** Player's current x. */
    private int myPlayerX;
    /** Player's current x. */
    private int myPlayerY;
    /** Player's current Direction. */
    private int myPlayerDirection;
    /** Player idle frame number. */
    private int myPlayerIdle;

    /**
     * Constructs MiniMap.
     */
    public MiniMap() {
        myRoomTiles = new ArrayList<>();
        myDoorTiles = new ArrayList<>();
        myTileSize = 0;
        myPlayerDirection = 0;
        myPlayerIdle = 0;
        this.setVisible(true);
        setPreferredSize(new Dimension(MAP_LENGTH, MAP_LENGTH));
        setMinimumSize(new Dimension(MAP_LENGTH, MAP_LENGTH));
        startPlayerAnimation();

    }

    /**
     * Set up the map for adding shapes.
     * Only one length is needed since the maze is a square.
     * @param theRows int rows of maze.
     */
    public void setUpMap(final int theRows) {
        myTileSize = (double) MAP_LENGTH / theRows;
        myPlayerSpot = new Ellipse2D.Double();
        setBackground(Color.BLACK);
    }

    /**
     * Attempt to add new room tile.
     * @param theRow int row.
     * @param theCol int col.
     */
    public void addRoomTile(final int theRow, final int theCol) {
        if (!hasRoomTile(theRow, theCol)) {
            MyRoomTile tile = new MyRoomTile(theRow, theCol);
            if (myRoomTiles.isEmpty()) {
                setPlayerSpot((double) theCol * myTileSize,
                        (double) theRow * myTileSize);
            }
            myRoomTiles.add(tile);
            repaint();
        }
    }

    /**
     * Attempt to add new door tile.
     * @param theRow int row.
     * @param theCol int col.
     * @param theDirection int direction.
     */
    public void addDoorTile(final int theRow, final int theCol,
                            final Direction theDirection,
                            final int theState) {
        if (hasDoorTile(theRow, theCol)) {
            removeDoorTile(theRow, theCol, theDirection);
        }
        MyDoorTile tile = new MyDoorTile(theRow, theCol,
                                        theDirection, theState);
        myDoorTiles.add(tile);
        repaint();
    }

    /**
     * Used to check if room tiles already has shape of room.
     * @param theRow int row.
     * @param theCol int col.
     * @return boolean.
     */
    private boolean hasRoomTile(final int theRow, final int theCol) {
        for (MyRoomTile tile : myRoomTiles) {
            if (tile.myCol == theCol && tile.myRow == theRow) {
                return true;
            }
        }
        return false;
    }

    /**
     * Used to check if door tiles already exists.
     * @param theRow int row.
     * @param theCol int col.
     * @return boolean.
     */
    private boolean hasDoorTile(final int theRow, final int theCol) {
        for (MyRoomTile tile : myRoomTiles) {
            if (tile.myCol == theCol && tile.myRow == theRow) {
                return true;
            }
        }
        return false;
    }

    /**
     * Used to remove door tile;
     * @param theRow int row.
     * @param theCol int col.
     * @param theDirection int direction.
     * @return boolean.
     */
    private boolean removeDoorTile(final int theRow, final int theCol,
                                   final Direction theDirection) {
        for (MyDoorTile tile : myDoorTiles) {
            if (tile.myCol == theCol && tile.myRow == theRow
                    && tile.myDir == theDirection) {
                myDoorTiles.remove(tile);
                return true;
            }
        }
        return false;
    }

    /**
     * Draws the map.
     * @param theG the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        final Graphics2D g2d = (Graphics2D) theG;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        for (MyRoomTile tile : myRoomTiles) {
            tile.myShape.paintIcon(this, theG, (int) tile.myX, (int) tile.myY);
        }
        for (MyDoorTile tile : myDoorTiles) {
            tile.myShape.paintIcon(this, theG, tile.myX, tile.myY);
        }

        ImageIcon playerIdle = getScaledImage(PLAYER[myPlayerIdle],
                (int) myTileSize, (int) myTileSize);

        ImageIcon playerPointer = getScaledImage(POINTERS[myPlayerDirection],
                (int) myTileSize, (int) myTileSize);

        playerIdle.paintIcon(this, theG, myPlayerX , myPlayerY);
        playerPointer.paintIcon(this, theG, myPlayerX , myPlayerY);


    }

    /**
     * Changes player spot.
     * @param theX double x.
     * @param theY double y.
     */
    private void setPlayerSpot(final double theX, final double theY) {
        myPlayerSpot.setFrameFromDiagonal(theX, theY,
                theX + myTileSize, theY + myTileSize);
        myPlayerX = (int) theX;
        myPlayerY = (int) theY;


        repaint();
    }

    /**
     * Moves player spot.
     * @param theDirection int direction.
     */
    public void movePlayerSpot(final int theDirection) {
        if (theDirection < 0 || theDirection > 3) {
            throw new IllegalArgumentException("Out of bounds! [0, 3]");
        }
        double x = myPlayerSpot.getX();
        double y = myPlayerSpot.getY();
        switch (theDirection) {
            case 0: // N
                y -= myTileSize;
                break;
            case 1: // E
                x += myTileSize;
                break;
            case 2: // S
                y += myTileSize;
                break;
            case 3: // W
                x -= myTileSize;
                break;
            default:
        }

        setPlayerSpot(x, y);

    }

    /**
     * Rotate the player.
     * @param theDirection int direction.
     */
    public void rotatePlayer(final int theDirection) {
        if (theDirection < 0 || theDirection > 3) {
            throw new IllegalArgumentException("Out of bounds! [0, 3]");
        }
        myPlayerDirection = theDirection;

        delayRepaint();

    }

    /**
     * Room tile with data necessary for drawing room correctly.
     */
    private class MyRoomTile {
        /** Room's row. */
        int myRow;
        /** Room's column. */
        int myCol;
        /** Tile's x coordinate. */
        double myX;
        /** Tile's y coordinate. */
        double myY;
        /** Shape of room tile. */
        ImageIcon myShape;

        /**
         * Constructs room tile.
         * @param theRow int row.
         * @param theCol int col.
         */
        public MyRoomTile(final int theRow, final int theCol) {
            myRow = theRow;
            myCol = theCol;
            myX = theCol * myTileSize;
            myY = theRow * myTileSize;
            myShape = getScaledImage(TILE, (int) myTileSize, (int) myTileSize);
        }
    }

    /**
     * Room tile with data necessary for drawing room correctly.
     */
    private class MyDoorTile {
        /** Door's row. */
        int myRow;
        /** Door's column. */
        int myCol;
        /** Tile's x coordinate. */
        int myX;
        /** Tile's y coordinate. */
        int myY;
        /** Tile's direction. */
        Direction myDir;
        /** Tile's state. */
        int myState;
        /** Tile's shape. */
        ImageIcon myShape;

        /**
         * Constructs room tile.
         * @param theRow int row.
         * @param theCol int col.
         */
        public MyDoorTile(final int theRow, final int theCol,
                          final Direction theDirection, final int theState) {
            myRow = theRow;
            myCol = theCol;
            myX = (int) ( theCol * myTileSize );
            myY = (int) ( theRow * myTileSize) ;
            myDir = theDirection;
            myState = theState;

            myShape = getScaledImage(DOORS[myState],
                    (int) myTileSize, (int) myTileSize);

            switch (myDir){
                case Direction.NORTH:
                    myY -= myShape.getIconHeight() / 2;
                    break;
                case Direction.EAST:
                    myX += myShape.getIconWidth() / 2;
                    break;
                case Direction.SOUTH:
                    myY += myShape.getIconHeight() / 2;
                    break;
                case Direction.WEST:
                    myX -= myShape.getIconWidth() / 2;
                    break;
            }
        }
    }
    private void startPlayerAnimation() {
        new Thread(() -> {
            while( true ){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    SwingUtilities.invokeAndWait(() -> {
                        myPlayerIdle = myPlayerIdle == 0 ? 1 : 0;
                        repaint();
                    });
                } catch (InterruptedException | InvocationTargetException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }).start();
    }

    /**
     * Returns scaled image icon.
     * @param srcImg ImageIcon.
     * @param w int width.
     * @param h int height.
     * @return ImageIcon.
     */
    private ImageIcon getScaledImage(ImageIcon srcImg, int w, int h){

        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg.getImage(), 0, 0, w, h, null);
        g2.dispose();

        return new ImageIcon(resizedImg);
    }

    /**
     * For animation.
     */
    private void delayRepaint() {
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            try {
                SwingUtilities.invokeAndWait(() -> repaint());
            } catch (InterruptedException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }).start();
    }
}
