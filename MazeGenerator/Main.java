/*
 * TCSS 360 Software Development and Assurance Techniques
 * Summer 2024
 */

public class Main {
    public static void main(final String[] theArgs) {
        Maze maze = new Maze(9, 9);
        final int times = 5;
        for (int i = 0; i < times; i++) {
            maze.assembleMaze();
            System.out.println(maze);
        }
    }
}