import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Tests {

    @Test
    public void testSimple()
    {
        String message = "Test [0-1, 0-2, 0-3, 0-4] with 2 as source";
        Graph graph = new Graph(5);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);

        DepthFirstPaths dfs = new DepthFirstPaths(graph, 2);

        assertTrue(message, dfs.hasPathTo(0));
        assertTrue(message, dfs.hasPathTo(1));
        assertTrue(message, dfs.hasPathTo(2));
        assertTrue(message, dfs.hasPathTo(3));
        assertTrue(message, dfs.hasPathTo(4));
    }

    @Test
    public void testDisconnected()
    {
        String message = "Test [0-1, 1-2, 3-4] with 3 as source";
        Graph graph = new Graph(5);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);

        DepthFirstPaths dfs = new DepthFirstPaths(graph, 3);

        assertFalse(message, dfs.hasPathTo(0));
        assertFalse(message, dfs.hasPathTo(2));
        assertTrue(message, dfs.hasPathTo(3));
        assertTrue(message, dfs.hasPathTo(4));


    }

    @Test
    public void testLoop()
    {
        String message = "Test [0-1, 1-2, 2-3, 3-4, 4-0] with 3 as source";
        Graph graph = new Graph(6);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);

        DepthFirstPaths dfs = new DepthFirstPaths(graph, 3);

        assertTrue(message, dfs.hasPathTo(4));
    }
}
