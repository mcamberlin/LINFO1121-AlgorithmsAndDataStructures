import java.util.ArrayList;

public class DigraphImplem implements Digraph {
    private final int V;
    private int E;
    private final ArrayList< ArrayList<Integer> > adj; //Adjacency list

    public DigraphImplem(int V)
    {
         // TODO
        this.V = V;
        this.E = 0;
        adj = new ArrayList<>(V);
        for(int i=0; i<V; i++)
        {
            adj.add(new ArrayList<Integer>(1));
        }
    }

    /**
     * The number of vertices
     */
    public int V()
    {
        // TODO
        return this.V;
    }

    /**
     * The number of edges
     */
    public int E() {
        // TODO
        return this.E;
    }

    /**
     * Add the edge v->w
     */
    public void addEdge(int v, int w) {
        // TODO
        adj.get(v).add(w);
        E++;
        
    }

    /**
     * The nodes adjacent to node v
     * that is the nodes w such that there is an edge v->w
     */
    public Iterable<Integer> adj(int v) {
        // TODO
        return adj.get(v);
    }

    /**
     * A copy of the digraph with all edges reversed
     */
    public Digraph reverse()
    {
        // TODO
        DigraphImplem reverseGraph = new DigraphImplem(this.V);
        for(int i = 0; i<this.V; i++) // for every list inside adj
        {
            for(Integer w : adj.get(i))
            {
                reverseGraph.addEdge(w,i);
            }
        }
        return reverseGraph;
    }
}
