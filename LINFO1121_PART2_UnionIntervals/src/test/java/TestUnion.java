import junit.framework.TestCase;

import java.util.Arrays;

/**
 * This is just a limited number of tests provided for convenience
 * Don't hesitate to extend it with other tests
 */
public class TestUnion extends TestCase {

    public void testEquals() {
        assertTrue((new Interval(1, 3)).equals(new Interval(1, 3)));
        assertFalse((new Interval(1, 4)).equals(new Interval(1, 3)));
    }

    public void testUnion0() {
        assertTrue(Arrays.equals(Union.union(new Interval[]{}), new Interval[]{}));
    }

    public void testUnion1() {
        Interval i1 = new Interval(1, 3);
        Interval i2 = new Interval(1, 3);
        assertTrue(Arrays.equals(Union.union(new Interval[]{i1, i2}), new Interval[]{new Interval(1, 3)}));
    }

    public void testUnion2() {
        Interval i1 = new Interval(1, 3);
        Interval i2 = new Interval(2, 4);
        assertTrue(Arrays.equals(Union.union(new Interval[]{i1, i2}), new Interval[]{new Interval(1, 4)}));
    }

    public void testUnion3() {
        Interval i1 = new Interval(1, 2);
        Interval i2 = new Interval(2, 4);
        assertTrue(Arrays.equals(Union.union(new Interval[]{i1, i2}), new Interval[]{new Interval(1, 4)}));
    }

    public void testUnion4() {
        Interval i1 = new Interval(1, 2);
        Interval i2 = new Interval(3, 4);
        assertTrue(Arrays.equals(
                        Union.union(new Interval[]{i1, i2}),
                        new Interval[]{new Interval(1, 2), new Interval(3, 4)}));
    }

    public void testUnion5() {
        Interval i1 = new Interval(1, 2);
        Interval i2 = new Interval(2, 2);
        assertTrue(Arrays.equals(Union.union(new Interval[]{i1, i2}), new Interval[]{new Interval(1, 2)}));
    }

    public void testUnion6() {
        Interval i1 = new Interval(1, 1);
        Interval i2 = new Interval(2, 2);
        assertTrue(Arrays.equals(Union.union(new Interval[]{i1, i2}),
                new Interval[]{new Interval(1, 1), new Interval(2, 2)}));
    }

    public void testUnion7() {
        Interval i0 = new Interval(7, 9);
        Interval i1 = new Interval(5, 8);
        Interval i2 = new Interval(2, 4);
        assertTrue(Arrays.equals(Union.union(new Interval[]{i0, i1, i2}),
                new Interval[]{new Interval(2, 4), new Interval(5, 9)}));
    }

    public void testUnion8() {
        Interval i0 = new Interval(10, 10);
        Interval i1 = new Interval(2, 4);
        Interval i2 = new Interval(3, 4);
        Interval i3 = new Interval(5, 6);
        Interval i4 = new Interval(6, 9);
        Interval i5 = new Interval(6, 8);

        assertTrue(Arrays.equals(Union.union(new Interval[]{i0, i1, i2, i3, i4, i5}),
                new Interval[]{new Interval(2, 4), new Interval(5, 9), new Interval(10, 10)}));
    }

    public void testUnion9()
    {
        Interval i0 = new Interval(4,7);
        Interval i1 = new Interval(5,8);
        Interval i2 = new Interval(6,7);
        Interval i3 = new Interval(8,11);
        Interval i4 = new Interval(12,12);
        Interval i5 = new Interval(12,15);
        Interval i6 = new Interval(13,14);
        Interval i7 = new Interval(13,15);
        Interval i8 = new Interval(14,17);
        Interval i9 = new Interval(16,16);
        assertTrue(
                Arrays.equals(Union.union(new Interval[]{i0, i1, i2, i3, i4, i5,i6,i7,i8,i9}),
                new Interval[]{new Interval(4, 11), new Interval(12, 17)}
                ));
    }



}
