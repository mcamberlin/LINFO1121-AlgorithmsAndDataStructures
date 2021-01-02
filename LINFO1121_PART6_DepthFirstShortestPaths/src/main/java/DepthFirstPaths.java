//TODO import

import java.util.Stack;

public class DepthFirstPaths {
    private boolean[] marked; // marked[v] = is there an s-v path?
    private int[] edgeTo;     // edgeTo[v] = last edge on s-v path
    private final int s;

    /**
     * Computes a path between s and every other vertex in graph G
     * @param G the graph
     * @param s the source vertex
     */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // Depth first search from v - Recursive way
    private void dfs(Graph G, int v)
    {
        //TODO question 2
        marked[v] = true;
        for (int w : G.adj(v))
        {
            if (marked[w] == false)
            {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }
    // Depth first search from v - Using a stack
    private void dfs2(Graph G, int v)
    {
        Stack<Integer> stack = new Stack<Integer>();

        marked[v] = true;
        stack.push(v);
        while (!stack.isEmpty())
        {
            int w = stack.pop();
            for (int x : G.adj(w))
            {
                if (!marked[x])
                {
                    marked[x] = true;
                    edgeTo[x] = w;
                    stack.push(w);
                }
            }
        }
    }



    /**
     * Is there a path between the source s and vertex v?
     * @param v the vertex
     * @return true if there is a path, false otherwise
     */
    public boolean hasPathTo(int v) {
        //TODO question 3
        return marked[v];
    }

    /**
     * Returns a path between the source vertex s and vertex v, or
     * null if no such path
     * @param v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *         s and vertex v, as an Iterable
     */
    public Iterable<Integer> pathTo(int v)
    {
        //TODO question 4
        if(!hasPathTo(v))
            return null;

        Stack<Integer> path = new Stack<>();
        for(int runner = v; runner != s; runner = edgeTo[runner])
            path.push(runner);
        path.push(s);
        return path;
    }
}
