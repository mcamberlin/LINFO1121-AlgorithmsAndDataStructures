//feel free to import anything here

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class MineClimbing {

    private static int[] distTo;          // distTo[v] = distance  of shortest s->v path
    private static Edge[] edgeTo;            // edgeTo[v] = last edge on shortest s->v path
    private static Queue<Integer> priorityQueue; // priority queue of vertices

    private static int m;
    private static int n;

    /**
     * Returns the minimum distance between (startX, startY) and (endX, endY), knowing that
     * you can climb from one mine block (a,b) to the other (c,d) with a cost Math.abs(map[a][b] - map[c][d])
     * <p>
     * Do not forget that the world is round: the position (map.length,i) is the same as (0, i), etc.
     * <p>
     * map is a matrix of size n times m, with n,m > 0.
     * <p>
     * 0 <= startX, endX < n
     * 0 <= startY, endY < m
     */
    public static int best_distance(int[][] map, int startX, int startY, int endX, int endY)
    {
        //TODO Student
        // shortest distance between two points => Dijkstra

        n = map.length;
        m = map[0].length;

        // Tools for Dijkstra
        edgeTo = new Edge[n * m]; // edgeTo[i] = the first edge to reach the source the first
        distTo = new int[n * m]; // distTo[i] = shortest distance to reach the source
        for (int i = 0; i < n * m; i++)
            distTo[i] = Integer.MAX_VALUE; // initially, don't know if all nodes can reach the source

        priorityQueue = new PriorityQueue<>(n * m); //To keep track of vertices that are candidates for being the new to be relaxed


        int s = indice(startX, startY, m);
        edgeTo[s] = new Edge(s, s, 0);
        distTo[s] = 0;
        priorityQueue.add(s);
        
        while (!priorityQueue.isEmpty())
        {
            int v = priorityQueue.remove();// index of the current node to analyse
            for (Edge e : neighbors(map, v / m, v % m)) // each neighbors
            {
                int w = e.other(v);
                if (distTo[w] > distTo[v] + e.weight())
                {
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                    priorityQueue.add(w);
                }
            }
        }

        return distTo[indice(endX, endY, m)];
    }

    private static int indice(int x, int y, int m)
    {
        return x * m + y;
    }

    /**
     * @param map
     * @param x
     * @param y
     * @return a iterable of all the different neighbors of the point (x,y)
     */
    private static Iterable<Edge> neighbors(int[][] map, int x, int y)
    {
        Stack<Edge> stack = new Stack<>();
        int index = -1;
        if (x == 0) //check above
        {
            index = indice(n - 1, y, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[n - 1][y]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        } else
        {
            index = indice(x - 1, y, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[x - 1][y]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        }

        if (x == n - 1) //check below
        {
            index = indice(0, y, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[0][y]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        } else
        {
            index = indice(x + 1, y, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[x + 1][y]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        }

        if (y == 0) // check left
        {
            index = indice(x, m - 1, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[x][m - 1]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        } else
        {
            index = indice(x, y - 1, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[x][y - 1]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        }

        if (y == m - 1) //check right
        {
            index = indice(x, 0, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[x][0]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        } else
        {
            index = indice(x, y + 1, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[x][y + 1]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        }
        return stack;
    }

    private static class Edge {
        private final int v; // vertex
        private final int w;
        private final int weight;

        public Edge(int v, int w, int weight)
        {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int other(int x)
        {
            if (x == this.v)
                return this.w;
            else
                return this.v;
        }

        public int weight()
        {
            return this.weight;
        }
    }

    // you may need to add additional things below.
}
