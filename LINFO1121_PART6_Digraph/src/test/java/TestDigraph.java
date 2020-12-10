import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


public class TestDigraph {

    public static void assertEqualsIterable(Iterable<Integer> one,Iterable<Integer> two) {
        ArrayList<Integer> oneList = new ArrayList<>();
        for (int i: one) {
            oneList.add(i);
        }
        ArrayList<Integer> twoList = new ArrayList<>();
        for (int i: two) {
            twoList.add(i);
        }
        Integer [] oneArray = oneList.toArray(new Integer[0]);
        Arrays.sort(oneArray);
        Integer [] twoArray = twoList.toArray(new Integer[0]);
        Arrays.sort(twoArray);
        assertArrayEquals(oneArray,twoArray);
    }

    public void assertEqualsGraph(Digraph g1, Digraph g2) {
        assertEquals(g1.V(), g2.V());
        assertEquals(g1.E(), g2.E());
        for (int i = 0; i < g1.V(); i++) {
            assertEqualsIterable(g1.adj(i),g2.adj(i));
        }
    }


    @Test
    public void test() {
        Digraph g = new DigraphImplem(10);

        g.addEdge(0,1);
        g.addEdge(0,2);
        assertEquals(10,g.V());
        assertEquals(2,g.E());

        assertEqualsIterable(g.adj(0), Arrays.asList(1,2));
        assertFalse(g.adj(1).iterator().hasNext());

        Digraph gr = g.reverse();

        assertFalse(gr.adj(0).iterator().hasNext());

        assertEqualsIterable(g.adj(0), Arrays.asList(1,2));
        assertFalse(g.adj(1).iterator().hasNext());

        assertEqualsIterable(gr.adj(1), Arrays.asList(0));
        assertEqualsIterable(gr.adj(2), Arrays.asList(0));

        assertEqualsGraph(g,g.reverse().reverse());

        assertEqualsGraph(g,g);


    }

    // Feel free to add tests in this class to debug your program.
    // Be aware that Inginious will check the complexity of your code.




}

