import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    public void testSimple()
    {
        String message = "Test [0-1, 0-2, 0-3, 0-4] with [2] as sources";
        Graph graph = new Graph(5);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);

        BreadthFirstShortestPaths bfs = new BreadthFirstShortestPaths(graph, Arrays.asList(2));

        assertEquals(message, 0, bfs.distTo(2));
        assertEquals(message, 1, bfs.distTo(0));
        assertEquals(message, 0, bfs.distTo(2));
        assertEquals(message, 2, bfs.distTo(3));
        assertEquals(message, 2, bfs.distTo(4));
    }

    @Test
    public void testDisconnected()
    {
        String message = "Test [0-1, 1-2, 3-4, 4-5] with [3] as sources";
        Graph graph = new Graph(6);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4,5);

        BreadthFirstShortestPaths bfs = new BreadthFirstShortestPaths(graph, Arrays.asList(3));
        assertEquals(message, 1, bfs.distTo(4));
        assertEquals(message, 2, bfs.distTo(5));

        assertEquals(message, BreadthFirstShortestPaths.INFINITY, bfs.distTo(0));
        assertEquals(message, BreadthFirstShortestPaths.INFINITY, bfs.distTo(1));
        assertEquals(message, BreadthFirstShortestPaths.INFINITY, bfs.distTo(2));
    }

    @Test
    public void testLoop()
    {
        String message = "Test [0-1, 1-2, 2-3, 3-4, 4-5, 5-0] with [4] as sources";
        Graph graph = new Graph(6);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 0);

        BreadthFirstShortestPaths bfs = new BreadthFirstShortestPaths(graph, Arrays.asList(4));
        assertEquals(message, 2, bfs.distTo(0));
        assertEquals(message, 3, bfs.distTo(1));
        assertEquals(message, 2, bfs.distTo(2));
        assertEquals(message, 1, bfs.distTo(3));
        assertEquals(message, 0, bfs.distTo(4));
        assertEquals(message, 1, bfs.distTo(5));
    }

    @Test
    public void testMultipleSources()
    {
        String message = "Test [0-1, 1-2, 2-3, 3-4, 4-5] with [0, 2] as sources";
        Graph graph = new Graph(6);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        BreadthFirstShortestPaths bfs = new BreadthFirstShortestPaths(graph, Arrays.asList(0, 2));
        assertEquals(message, 0, bfs.distTo(0));
        assertEquals(message, 1, bfs.distTo(1));
        assertEquals(message, 0, bfs.distTo(2));
        assertEquals(message, 1, bfs.distTo(3));
        assertEquals(message, 2, bfs.distTo(4));
        assertEquals(message, 3, bfs.distTo(5));
    }


}
