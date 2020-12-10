public class ConnectedComponents {
    private boolean[] marked;
    private int[] id;
    private int nbComponents = 0;

    public ConnectedComponents(Graph g)
    {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        for(int s = 0; s< g.V();s++)
        {
            if(!marked[s])
            {
                dfs(g,s);
                nbComponents++;
            }

        }

    }

    private void dfs(Graph g, int s)
    {
        marked[s] = true;
        id[s] = nbComponents;
        for(int w :g.adj(s))
        {
            if(!marked[w])
            {
                dfs(g,w);
            }
        }
    }

    /**
     * @return the number of connected components in g
     */
    public static int numberOfConnectedComponents(Graph g)
    {
        // TODO
        ConnectedComponents CC = new ConnectedComponents(g);
        return CC.nbComponents;
    }
}
