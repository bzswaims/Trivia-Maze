//TODO all of it

package view;

import model.Direction;
import model.Room;

import javax.swing.*;
import java.awt.*;

public class MiniMap extends JPanel {
    private static final int TILE_SIZE = 50; // Size of each tile in pixels
    private static final int GRID_SIZE = 4;  // Number of tiles in each row/column
    private static final int CIRCLE_DIAMETER = 10; // Diameter of the circle


    private Room[][] myMinimapRooms;
    private Room myCurrentRoom;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMinimap(g);
    }

    private void drawMinimap(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int row = 0; row < myMinimapRooms.length; row++) {
            for (int col = 0; col < myMinimapRooms.length; col++) {

                // Calculate the position of the tile
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;

                g2d.setColor(Color.WHITE);

                if(myMinimapRooms[row][col].isEnd()){
                    g2d.setColor(Color.RED);
                }
                if(myMinimapRooms[row][col].isBlock()){
                    g2d.setColor(Color.LIGHT_GRAY);
                }
                // Draw the tile
                g2d.fillRect(x, y, TILE_SIZE, TILE_SIZE);

                // Optionally, draw the border of the tile
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, TILE_SIZE, TILE_SIZE);

                g2d.setColor(Color.GREEN);
                // Door is in this order N-E-S-W

                if(myCurrentRoom.equals(myMinimapRooms[row][col])){
                    g2d.setColor(Color.CYAN);
                    drawCircle(g2d, x + TILE_SIZE / 2 - CIRCLE_DIAMETER / 2, y + TILE_SIZE / 2 - CIRCLE_DIAMETER / 2);
                }
                g2d.setColor(Color.GREEN);
                // Draw circles centered on each side of the tile
                Room myRoom = myMinimapRooms[row][col];
                if(myRoom.getDoor(Direction.NORTH) != null){
                    drawCircle(g2d, x + TILE_SIZE / 2 - CIRCLE_DIAMETER / 2, y - CIRCLE_DIAMETER / 2); // Top side
                }
                if(myRoom.getDoor(Direction.SOUTH) != null) {
                    drawCircle(g2d, x + TILE_SIZE / 2 - CIRCLE_DIAMETER / 2, y + TILE_SIZE - CIRCLE_DIAMETER / 2); // Bottom side
                }
                if(myRoom.getDoor(Direction.WEST) != null) {

                    drawCircle(g2d, x - CIRCLE_DIAMETER / 2, y + TILE_SIZE / 2 - CIRCLE_DIAMETER / 2); // Left side
                }
                if(myRoom.getDoor(Direction.EAST) != null) {
                    drawCircle(g2d, x + TILE_SIZE - CIRCLE_DIAMETER / 2, y + TILE_SIZE / 2 - CIRCLE_DIAMETER / 2); // Right side

                }
            }
        }
    }
    public MiniMap() {
        setPreferredSize(new Dimension(TILE_SIZE * GRID_SIZE, TILE_SIZE * GRID_SIZE));
        setBackground(Color.WHITE);
    }
    private void drawCircle(Graphics2D g2d, int x, int y) {
        g2d.fillOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
    }
    /**
     * Sets minimap room
     * @param theRooms the rooms of the maze
     */
    public void setRooms(Room[][] theRooms){
        myMinimapRooms = theRooms;
    }
    /**
     * Sets minimap current room
     * @param theRoom the current room we are in
     */
    public void setCurrentRoom(Room theRoom){
        myCurrentRoom = theRoom;
    }
}
