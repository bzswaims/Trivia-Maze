/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Shape> myDoorTiles;
    /** Representation of player. */
    private Ellipse2D.Double myPlayerSpot;

    /**
     * Constructs MiniMap.
     */
    public MiniMap() {
        myRoomTiles = new ArrayList<>();
        myTileSize = 0;
        this.setVisible(true);
        setPreferredSize(new Dimension(MAP_LENGTH, MAP_LENGTH));
        setMinimumSize(new Dimension(MAP_LENGTH, MAP_LENGTH));
    }

    /**
     * Set up the map for adding shapes.
     * @param theRows int rows of maze.
     * @param theCols int columns of maze.
     */
    public void setUpMap(final int theRows, final int theCols) {
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
            g2d.setColor(Color.WHITE);
            g2d.fill(tile.myRectangle);
            g2d.setColor(Color.GREEN);
            g2d.draw(tile.myRectangle);
        }
        g2d.setColor(Color.CYAN);
        g2d.fill(myPlayerSpot);
    }

    /**
     * Changes player spot.
     * @param theX double x.
     * @param theY double y.
     */
    private void setPlayerSpot(final double theX, final double theY) {
        myPlayerSpot.setFrameFromDiagonal(theX, theY, theX + myTileSize, theY + myTileSize);
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
     * JUST FOR TEST PURPOSES!!
     * @return String.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TILES [\n");
        for (MyRoomTile tile : myRoomTiles) {
            sb.append("ROOM: ").append(tile.myRow).append(" ").append(tile.myCol);
        }
        sb.append("\n]");
        return sb.toString();
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
        Rectangle2D myRectangle;

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
            myRectangle = new Rectangle2D.Double(myX, myY, myTileSize, myTileSize);
        }
    }
}
