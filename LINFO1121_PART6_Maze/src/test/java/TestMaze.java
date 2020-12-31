import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

public class TestMaze extends TestCase {

    public int [][] maze1 = new int[][] {
            {0,0,0,0,0,0,0},
            {1,1,0,0,0,0,0},
            {0,0,0,0,0,1,0},
            {0,1,1,1,1,1,1},
            {0,0,0,0,1,0,0},
            {1,1,0,0,1,0,0},
            {0,0,0,0,1,0,0}
    };

    public int [][] maze2 = new int[][] {
            {0,0,0,1,0,0,0},
            {1,1,0,0,0,1,0}
    };

    @Test
    public void testMaze0() {
        int [][] maze = new int[][]{
                {0, 0, 0, 1},
                {1, 1, 0, 0}
        };

        Iterable<Integer> path = Maze.shortestPath(maze,0,0,0,2);
        Integer [] pathArray = toArray(path);
        assertTrue(validPathSourceToDest(0,0,0,2,maze,path));
        assertTrue(pathArray.length == 3);
    }

    @Test
    public void testMaze1a() {
        Iterable<Integer> path = Maze.shortestPath(maze1,0,0,6,0);
        Integer [] pathArray = toArray(path);
        assertTrue(validPathSourceToDest(0,0,6,0,maze1,path));
        assertTrue(pathArray.length == 15);
    }

    @Test
    public void testMaze1b() {
        // should not have a path
        // unreachable destination
        assertTrue(!Maze.shortestPath(maze1,0,0,6,6).iterator().hasNext());
        // unreachable destination
        assertTrue(!Maze.shortestPath(maze1,6,6,0,0).iterator().hasNext());
        // start position is a wall
        assertTrue(!Maze.shortestPath(maze1,1,0,6,0).iterator().hasNext());
        // end position is a wall
        assertTrue(!Maze.shortestPath(maze1,6,0,1,0).iterator().hasNext());
    }

    @Test
    public void testMaze1c() {
        Iterable<Integer> path = Maze.shortestPath(maze1,0,0,0,0);
        Integer [] pathArray = toArray(path);
        assertTrue(validPathSourceToDest(0,0,0,0,maze1,path));
        assertTrue(pathArray.length == 1);
    }

    @Test
    public void testMaze2a() {
        Iterable<Integer> path = Maze.shortestPath(maze2,0,0,1,6);
        Integer [] pathArray = toArray(path);
        assertTrue(validPathSourceToDest(0,0,1,6,maze2,path));
        assertTrue(pathArray.length == 10);
    }



    public Integer[] toArray(Iterable<Integer> path) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        path.forEach(list::add);
        return list.toArray(new Integer[0]);
    }


    public static int row(int pos, int mCols) { return pos / mCols; }

    public static int col(int pos, int mCols) { return pos % mCols; }

    public boolean validPathSourceToDest(int x1, int y1, int x2, int y2, int [][] maze, Iterable<Integer> path) {
        int n = maze.length;
        int m = maze[0].length;
        Iterator<Integer> ite = path.iterator();
        if (!ite.hasNext()) return false;
        int p = ite.next();
        int x = row(p,m);
        int y = col(p,m);
        if (x != x1 || y != y1) return false;
        while (ite.hasNext()) {
            p = ite.next();
            int x_ = row(p,m);
            int y_ = col(p,m);
            if (maze[x][y] == 1) return false;
            if (Math.abs(x_-x)+Math.abs(y_-y) != 1) return false;
            x = x_; y = y_;
        }
        if (x != x2 || y != y2) return false;
        return true;
    }



}
