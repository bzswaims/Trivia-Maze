/*
 * TCSS 360 Software Development and Quality Assurance Techniques
 * Summer 2024
 */

//TODO all of it

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MiniMap extends JPanel {
    /** Length of map. */
    private static final int MAP_LENGTH = 100;
    /** Scaled size of tile. */
    private double myTileSize;
    /** List of room tiles with shapes and coordinates. */
    private List<MyRoomTile> myRoomTiles;
    /** List of doors with shapes, coordinates, and directions. */
    private List<Shape> myDoorTiles;
    /** Representation of player. */
    private Ellipse2D.Double myPlayerSpot;

    /**
     * Constructs MiniMap.
     */
    public MiniMap() {
        myRoomTiles = new ArrayList<MyRoomTile>();
        myTileSize = 0;
        setPreferredSize(new Dimension(MAP_LENGTH, MAP_LENGTH));
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
        for (int i = 0; i < myRoomTiles.size(); i++) {
            MyRoomTile tile = myRoomTiles.get(i);
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
        for (int i = 0; i < myRoomTiles.size(); i++) {
            MyRoomTile tile = myRoomTiles.get(i);
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
        for (int i = 0; i < myRoomTiles.size(); i++) {
            MyRoomTile tile = myRoomTiles.get(i);
            sb.append("ROOM: " + tile.myRow + " " + tile.myCol);
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
        /** Tile's x coord. */
        double myX;
        /** Tile's y coord. */
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

    // may add DoorShape class here
}
