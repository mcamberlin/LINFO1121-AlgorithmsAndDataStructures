
import junit.framework.TestCase;
import org.junit.Test;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is just a limited number of tests provided for convenience
 * Don't hesitate to extend it with other tests
 */
public class TestMinMaxHeap {


    private static <K extends Comparable<K>> Set<K> collect(MinMaxHeap<K> heap) {
        // don't modify this method, it is used for testing purposes
        Set<K> values = new HashSet<>();
        for (int i = 1; i <= heap.size(); i++) {
            values.add(heap.pq[i]);
        }
        return values;
    }


    public static boolean sameContent(MinMaxHeap<Integer> minMaxHeap, Set<Integer> values) {
        return collect(minMaxHeap).equals(values);
    }

    public static boolean satisfiesMinMaxHeapProperties(MinMaxHeap minMaxHeap) {
        if (!completeTree(minMaxHeap)) {
            System.out.println("tree not complete");
            return false;
        }
        if (!satisfiesMinLayerProperty(minMaxHeap,1)) {
            System.out.println("min max heap ordering property not satisfied");
            return false;
        }
        return true;
    }


    public static boolean completeTree(MinMaxHeap minMaxHeap) {

        if (minMaxHeap.pq[0] != null) return false;
        for (int i = 1; i <= minMaxHeap.size(); i++) {
            if (minMaxHeap.pq[i] == null) return false;
        }
        for (int i = minMaxHeap.size()+1; i < minMaxHeap.pq.length; i++) {
            if (minMaxHeap.pq[i] != null) return false;
        }
        return true;
    }


    public static boolean satisfiesMinLayerProperty(MinMaxHeap minMaxHeap, int i) {
        int left = 2 * i;
        int right = left + 1;
        int leftLeft = 2 * left;
        int leftRight = leftLeft +1;
        int rightLeft = 2 * right;
        int rightRight = rightLeft + 1;
        boolean ok = lessThan(minMaxHeap,i,left,right,leftLeft,leftRight,rightLeft,rightRight);

        if (!ok) {
            System.out.println("violating min layer at index "+i+" value:"+minMaxHeap.pq[i]);
        }
        if (left < minMaxHeap.size()) {
            ok = ok && satisfiesMaxLayerProperty(minMaxHeap,left);
        }
        if (right < minMaxHeap.size()) {
            ok = ok && satisfiesMaxLayerProperty(minMaxHeap,right);
        }
        return ok;
    }

    private static boolean satisfiesMaxLayerProperty(MinMaxHeap minMaxHeap, int i) {
        int left = 2 * i;
        int right = left + 1;
        int leftLeft = 2 * left;
        int leftRight = leftLeft +1;
        int rightLeft = 2 * right;
        int rightRight = rightLeft + 1;

        boolean ok = greaterThan(minMaxHeap,i,left,right,leftLeft,leftRight,rightLeft,rightRight);

        if (!ok) {
            System.out.println("violating max layer at index "+i+" value:"+minMaxHeap.pq[i]);
        }
        if (left < minMaxHeap.size()) {
            ok = ok && satisfiesMinLayerProperty(minMaxHeap,left);
        }
        if (right < minMaxHeap.size()) {
            ok = ok && satisfiesMinLayerProperty(minMaxHeap,right);
        }
        return ok;
    }



    public static boolean lessThan(MinMaxHeap minMaxHeap, int i, int ... pos) {
        for (int j: pos) {
            if (j < minMaxHeap.pq.length) {
                if (minMaxHeap.pq[j] == null) return true;
                else if (minMaxHeap.less(j,i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean greaterThan(MinMaxHeap minMaxHeap, int i, int ... pos) {
        for (int j: pos) {
            if (j < minMaxHeap.pq.length) {
                if (minMaxHeap.pq[j] == null) return true;
                else if (minMaxHeap.less(i,j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void testExample() {

        MinMaxHeap<Integer> heap = new MinMaxHeap<>(25);

        heap.insert(8);
        heap.insert(71);
        heap.insert(41);
        heap.insert(31);
        heap.insert(10);
        heap.insert(11);
        heap.insert(16);
        heap.insert(46);
        heap.insert(51);
        heap.insert(31);
        heap.insert(21);
        heap.insert(13);
        heap.insert(12);
        heap.insert(17);
        heap.insert(18);
        heap.insert(35);
        heap.insert(38);
        heap.insert(40);

        assertEquals(Integer.valueOf(8),heap.min());
        assertEquals(Integer.valueOf(71),heap.max());

        assertTrue(satisfiesMinMaxHeapProperties(heap));
        assertTrue(sameContent(heap, new HashSet<Integer>(Arrays.asList(new Integer[]{8, 71, 41, 31, 10, 11, 16, 46, 51, 31, 21, 13, 12, 17, 18, 35, 38, 40}))));

        heap.insert(2);


        assertEquals(Integer.valueOf(2),heap.min());
        assertEquals(Integer.valueOf(71),heap.max());

        assertTrue(satisfiesMinMaxHeapProperties(heap));
        assertTrue(sameContent(heap, new HashSet<Integer>(Arrays.asList(new Integer[]{8, 71, 41, 31, 10, 11, 16, 46, 51, 31, 21, 13, 12, 17, 18, 35, 38, 40, 2}))));


        heap.insert(72);

        assertEquals(Integer.valueOf(2),heap.min());
        assertEquals(Integer.valueOf(72),heap.max());

        assertTrue(satisfiesMinMaxHeapProperties(heap));
        assertTrue(sameContent(heap, new HashSet<Integer>(Arrays.asList(new Integer[]{8, 71, 41, 31, 10, 11, 16, 46, 51, 31, 21, 13, 12, 17, 18, 35, 38, 40, 2, 72}))));


        heap.insert(5);

        assertEquals(Integer.valueOf(2),heap.min());
        assertEquals(Integer.valueOf(72),heap.max());

        assertTrue(satisfiesMinMaxHeapProperties(heap));
        assertTrue(sameContent(heap, new HashSet<Integer>(Arrays.asList(new Integer[]{8, 71, 41, 31, 10, 11, 16, 46, 51, 31, 21, 13, 12, 17, 18, 35, 38, 40, 2, 72, 5}))));

    }

    @Test
    public void testBasic() {

        MinMaxHeap<Integer> heap = new MinMaxHeap<>(10);

        heap.insert(5);

        assertEquals(Integer.valueOf(5),heap.min());
        assertEquals(Integer.valueOf(5),heap.max());

        heap.insert(6);

        assertEquals(Integer.valueOf(5),heap.min());
        assertEquals(Integer.valueOf(6),heap.max());

        heap.insert(4);

        assertEquals(Integer.valueOf(4),heap.min());
        assertEquals(Integer.valueOf(6),heap.max());

        heap.insert(8);

        assertEquals(Integer.valueOf(4),heap.min());
        assertEquals(Integer.valueOf(8),heap.max());

        heap.insert(7);

        assertEquals(Integer.valueOf(4),heap.min());
        assertEquals(Integer.valueOf(8),heap.max());

        heap.insert(3);

        assertEquals(Integer.valueOf(3),heap.min());
        assertEquals(Integer.valueOf(8),heap.max());

    }

    @Test
    public void testCorrectness() {

        for (int size = 1; size < 100; size ++) {
            for (int k = 0; k < 100; k++) {
                Random rand = new Random();
                Set<Integer> values = new HashSet<>();
                int n = size;
                MinMaxHeap<Integer> heap = new MinMaxHeap<Integer>(n);
                int currentMin = Integer.MAX_VALUE;
                int currentMax = Integer.MIN_VALUE;
                for (int i = 0; i < n; i++) {
                    int v = rand.nextInt(300);
                    heap.insert(v);
                    values.add(v);
                    currentMin = Math.min(currentMin, v);
                    currentMax = Math.max(currentMax, v);

                    assertEquals(Integer.valueOf(currentMin), heap.min());
                    assertEquals(Integer.valueOf(currentMax), heap.max());

                }
                assertTrue(satisfiesMinMaxHeapProperties(heap));
                assertTrue(sameContent(heap, values));
            }
        }


    }

    @Test(timeout = 1000)
    public void testComplexity() {
        int n = 100000;
        MinMaxHeap<Integer> heap = new MinMaxHeap<Integer>(n);
        for (int i = 0; i < n; i++) {
            heap.insert(i);
            assertEquals(Integer.valueOf(0), heap.min());
            assertEquals(Integer.valueOf(i), heap.max());
        }
        assertTrue(satisfiesMinMaxHeapProperties(heap));
    }







}
