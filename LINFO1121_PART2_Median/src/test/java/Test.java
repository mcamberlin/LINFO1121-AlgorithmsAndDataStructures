import junit.framework.TestCase;

import java.util.Arrays;

public class Test extends TestCase {

	// assigning the values
    protected void setUp() {

    }

    public static boolean isMedian(Vector a, int lo, int hi, int median) {
    	int le = 0;
    	int eq = 0;
    	for (int i = lo; i <= hi; i++) {
    		if (a.get(i) == median) {
    			eq++;
    		}
    		else if (a.get(i) < median) {
    			le++;
    		}
    	}
    	if (eq == 0) return false;
    	return le <= a.size()/2 && le+eq > a.size()/2;
    }


    public static Vector randomVector(int n) {
        java.util.Random rand = new java.util.Random();
        int [] array = new int[n];
        Arrays.setAll(array, i -> rand.nextInt(1000000));
        Vector v = new Vector(array.length);
        for (int i = 0; i < v.size(); i++) {
            v.set(i,array[i]);
        }
        return v;
    }

    public static Vector randomV(int n) {
        java.util.Random rand = new java.util.Random();
        int [] array = new int[n];
        Arrays.setAll(array, i -> rand.nextInt(10));
        Vector v = new Vector(array.length);
        for (int i = 0; i < v.size(); i++) {
            v.set(i,array[i]);
        }
        return v;
    }


    @org.junit.Test
    public void testMedianOk() {
        for (int i = 100; i < 1000; i += 100) {
            Vector v = randomVector(i+1);
            int m = Median.median(v,0,v.size()-1);
            assertTrue("correct median value computed",isMedian(v,0,v.size()-1,m));
        }
    }
    public void testPartition()
    {
        Vector v = new Vector(9);
        v.set(0,5);
        v.set(1,4);
        v.set(2,3);
        v.set(3,7);
        v.set(4,5);
        v.set(5,1);
        v.set(6,4);
        v.set(7,8);
        v.set(8,0);
        assertEquals(Median.partition(v,0, v.size()-1), 6);
    }

    public void test0()
    {
        Vector v = new Vector(13);
        v.set(0,0);
        v.set(1,1);
        v.set(2,1);
        v.set(3,1);
        v.set(4,2);
        v.set(5,3);
        v.set(6,4);
        v.set(7,5);
        v.set(8,6);
        v.set(9,7);
        v.set(10,8);
        v.set(11,9);
        v.set(12,10);

        assertEquals( Median.median(v,0,12), 4);
    }

    public void test1()
    {
        Vector v = new Vector(9);
        v.set(0,5);
        v.set(1,4);
        v.set(2,3);
        v.set(3,7);
        v.set(4,5);
        v.set(5,1);
        v.set(6,4);
        v.set(7,8);
        v.set(8,0);
        assertEquals(Median.median(v,0, v.size()-1), 4);
    }

    public void test2()
    {
        Vector v = randomV(7);
        int m = Median.median(v,0,v.size()-1);
        assertTrue(isMedian(v,0,v.size()-1,m));
    }

    public void test3()
    {
        for (int i = 100; i < 1000; i += 100) {
            Vector v = randomVector(i+1);
            int m = Median.median(v,0,v.size()-1);
            assertTrue("correct median value computed",isMedian(v,0,v.size()-1,m));
        }
    }


}

