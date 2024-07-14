public class Main {
    public static void main(final String[] theArgs) {
        Maze maze = new Maze(9, 9);
        maze.assembleMaze();
        System.out.println(maze);
    }
}
