//TODO Put your import here

import java.util.*;

public class BreadthFirstShortestPaths {
    public static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked; // marked[v] = is there an s-v path
    private int[] distTo;     // distTo[v] = number of edges shortest s-v path

    /**
     * Computes the shortest path between any
     * one of the sources and very other vertex
     * @param G the graph
     * @param sources the source vertices
     */
    public BreadthFirstShortestPaths(Graph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        for (int v = 0;v < G.V();v++) {
            distTo[v] = INFINITY;
        }
        bfs(G, sources);
    }

    // Breadth-first search from multiple sources
    private void bfs(Graph G, Iterable<Integer> sources)
    {
        //TODO question 1
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int source : sources)
        {
            marked[source] = true;
            distTo[source] = 0;
            queue.add(source);
        }

        while (!queue.isEmpty())
        {
            int v = queue.remove();
            for (int w : G.adj(v))
            {
                if (!marked[w])
                {
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1;
                    System.out.println(w + "," + distTo[w]);
                    queue.add(w);
                }
            }
        }
    }

    /**
     * Is there a path between (at least one of) the sources and vertex v?
     * @param v the vertex
     * @return true if there is a path, and false otherwise
     */
    public boolean hasPathTo(int v) {
        //TODO question 2
        return marked[v] == true;
    }

    /**
     * Returns the number of edges in a shortest path
     * between one of the sources and vertex v?
     * @param v the vertex
     * @return the number of edges in a shortest path
     */
    public int distTo(int v) {
        //TODO question 3
        return distTo[v];
    }
}
