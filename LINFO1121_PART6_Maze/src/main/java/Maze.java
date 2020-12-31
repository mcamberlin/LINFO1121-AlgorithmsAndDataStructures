import java.util.ArrayDeque;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Maze {
    public static Iterable<Integer> shortestPath(int [][] maze,  int x1, int y1, int x2, int y2) {
        //TODO
        if(maze[x1][y1] == 1 || maze[x2][y2] == 1) // initial or end point is a wall
            return new LinkedList<>();

        // useful variables
        int nbLines = maze.length;
        int nbCols = maze[0].length;

        // Beginning of the BFS
        boolean[] marked = new boolean[nbLines * nbCols];
        int[] distTo = new int[nbLines * nbCols];
        int[] edgeTo = new int[nbLines * nbCols];
        for(int i=0;i<edgeTo.length;i++)
            edgeTo[i] = -1;

        Queue<Integer> queue = new ArrayDeque<>();
        marked[ind(x1,y1,nbCols)] = true;
        distTo[ind(x1,y1,nbCols)] = 0;
        edgeTo[ind(x1,y1,nbCols)] = ind(x1,y1,nbCols);

        queue.add(ind(x1,y1,nbCols));
        while(!queue.isEmpty())
        {
            int i = queue.remove(); // return the index of the current vertex to analyse

            if(i == ind(x2,y2,nbCols)) // the destination is found
            {
                continue;
            }

            int i_x = row(i,nbCols);
            int i_y = col(i,nbCols);

            if(maze[i_x][i_y] == 0 ) // i is a way and not a wall
            {
                if(i_y-1 >=0 && maze[i_x][i_y-1] == 0 && !marked[ind(i_x,i_y-1,nbCols)]) // check left
                {
                    int index = ind(i_x,i_y-1,nbCols);
                    marked[index] = true;
                    distTo[index] = distTo[i] +1;
                    edgeTo[index] = i;
                    queue.add(index);
                }
                if(i_y+1 < nbCols && maze[i_x][i_y+1] == 0 && !marked[ind(i_x,i_y+1,nbCols)]) // check right
                {
                    int index = ind(i_x,i_y+1,nbCols);
                    marked[index] = true;
                    distTo[index] = distTo[i] +1;
                    edgeTo[index] = i;
                    queue.add(index);
                }
                if(i_x-1 >=0 && maze[i_x-1][i_y] == 0 && !marked[ind(i_x-1,i_y,nbCols)]) // check above
                {
                    int index = ind(i_x-1,i_y,nbCols);
                    marked[index] = true;
                    distTo[index] = distTo[i] +1;
                    edgeTo[index] = i;
                    queue.add(index);
                }
                if(i_x+1 <nbLines && maze[i_x+1][i_y] == 0 && !marked[ind(i_x+1,i_y,nbCols)]) // check below
                {
                    int index = ind(i_x+1,i_y,nbCols);
                    marked[index] = true;
                    distTo[index] = distTo[i] +1;
                    edgeTo[index] = i;
                    queue.add(index);
                }
            }
        } // end of the BFS

        LinkedList<Integer> path = new LinkedList<>();

        if(!marked[ind(x2,y2,nbCols)]) // There isn't a path between (x1,y1) and (x2,y2)
            return path;

        for(int index = ind(x2,y2,nbCols); index != ind(x1,y1,nbCols); index = edgeTo[index] )
        {
            path.add(index);
        }
        path.add(ind(x1,y1,nbCols));
        Collections.reverse(path);
        for(int i : path)
            System.out.println(i + " - (" + row(i,nbCols) + "," + col(i,nbCols) + ")");

        return path;
    }


    public static int ind(int x,int y, int nbCols)
    {
        return x*nbCols + y;
    }

    public static int row(int pos, int mCols)
    {
        return pos / mCols;
    }

    public static int col(int pos, int mCols)
    {
        return pos % mCols;
    }
}
