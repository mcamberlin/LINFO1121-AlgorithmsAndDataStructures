import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CeilTest {

    ///////// Base
    @Test
    @Grade(value=20)
    public void testCeilOk() {
        java.util.TreeSet<Integer> correct = new java.util.TreeSet<>();
        int values[] = new int[]{12,8,18,3,11,14,20,9,15};
        int in[] = new int[]{11,14,9,4,16,10,19,21,30,40};

        Node root = new Node(values[0]);
        for (int i = 1; i < values.length; i++) {
            root.add(values[i]);
            correct.add(values[i]);
        }

        for (int i : in ) {
            assertEquals(Ceil.ceil(root,i), correct.ceiling(i));
        }
    }

    /////////// Extreme
    /**
     * Generates a random array of Integers, of size n
     */
    public static Integer[] randomArray(int n) {
        java.util.Random rand = new java.util.Random();
        Integer [] array = new Integer[n];
        Arrays.setAll(array, i -> rand.nextInt(1000000));
        return array;
    }

    /**
     * Verifies that values.ceil(where) == ceilFound
     * @param values
     * @param ceilFound
     * @param where
     */
    public static boolean verify(Integer[] values, Integer ceilFound, int where) {
        // Let a real balanced tree for the Java STD lib do the work for us:
        TreeSet<Integer> set = new TreeSet<Integer>();
        Collections.addAll(set, values);
        Integer ceil2 = set.ceiling(where);

        if(ceilFound != null && ceil2 != null)
            return ceilFound.equals(ceil2);
        else
            return ceilFound == ceil2;
    }

    @Test
    @Grade(value=30)
    public void testExtreme() {
        for (int i = 100; i < 1000; i += 100) {
            Integer[] v = randomArray(i+1);
            Node root = new Node(v[0]);
            for(int j = 1; j < v.length; j++)
                root.add(v[j]);
            for(int j = -200; j < 1000001; j += 1000) {
                Integer ceil = Ceil.ceil(root, j);
                assertTrue("correct ceiling value computed",verify(v,ceil,j));
            }
        }
    }

    ////////// complexity
    static private class InstanceConfig {
        int toRetrieve = 0;
        boolean wrongDirection = false;
        HashSet<Node> wrongTurnsCalled = new HashSet<>();

        public void reset(int toRetrieve) {
            this.toRetrieve = toRetrieve;
            wrongDirection = false;
            wrongTurnsCalled = new HashSet<>();
        }
    }

    static private class BaseNode extends Node {
        private int value;
        private InstanceConfig info;
        private BaseNode left;
        private BaseNode right;

        BaseNode(int v, InstanceConfig tor) {
            value = v;
            left = null;
            right = null;
            info = tor;
        }

        @Override
        public int getValue() {
            if(info.wrongTurnsCalled.contains(this))
                info.wrongDirection = true;
            return value;
        }

        @Override
        public Node getLeft() {
            if(value <= info.toRetrieve)
                info.wrongTurnsCalled.add(left);
            return left;
        }

        @Override
        public Node getRight() {
            if(value >= info.toRetrieve)
                info.wrongTurnsCalled.add(right);
            return right;
        }

        public void add(int v) {
            if(v < value && left == null) left = new BaseNode(v, info);
            else if(v < value) left.add(v);
            else if(v > value && right == null) right = new BaseNode(v, info);
            else if(v > value) right.add(v);
        }
    }

    @Test
    @Grade(value=50)
    public void testComplexity() {
        int[][] tests = new int[][]{
                new int[] {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000},
                new int[] {1000, 900, 800, 700, 600, 500, 400, 300, 200, 100},
                new int[] {500, 300, 800, 100, 400, 600, 900, 200, 700},
                new int[] {231,635,69,644,422,855,161,275,10,685,544,34,379,575,858,740,832,842,838,624,118,55,977,163,484,59,737,299,343,161,647,674,249,758,248,19,612,336,419,255,416,460,286,35,678,352,796,195,308,918,778,118,202,879,378,548,214,688,908,668,759,293,875,279,324,472,117,167,637,32,934,34,854,673,113,110,27,585,266,450,769,4,264,206,586,704,304,612,639,948,718,952,534,444,470,302,182,30,165,984},
                new int[] {636,403,939,800,651,781,855,72,835,858,820,463,473,665,524,759,454,454,920,674,496,571,481,255,384,933,7,116,579,895,562,381,151,454,907,146,410,566,332,364,814,193,50,462,922,510,831,766,42,69,917,254,287,65,182,35,50,64,760,822,556,203,381,34,744,360,234,965,932,406,264,581,601,792,160,531,562,997,433,987,204,383,629,132,118,716,216,621,25,11,42,854,759,435,312,741,482,722,546,490},
                new int[] {164,898,443,782,245,1,164,767,788,590,910,745,803,688,801,322,118,70,121,829,130,153,443,718,794,871,935,845,233,187,48,93,235,35,603,481,317,348,674,673,278,809,651,468,858,696,902,905,303,108,952,435,766,922,13,492,29,797,988,120,371,24,115,425,970,898,65,735,938,647,691,886,563,930,958,393,94,218,23,258,825,232,697,673,863,607,356,17,13,340,981,288,9,316,345,155,489,224,449,491},
                new int[] {4,471,616,61,568,47,232,7,921,169,153,583,849,230,996,532,864,343,435,452,391,389,903,390,356,292,769,504,509,354,980,798,825,287,136,115,128,600,31,555,450,625,515,78,940,351,22,801,16,825,338,491,891,994,10,970,381,902,387,173,765,567,81,380,695,995,337,685,631,160,728,804,906,920,905,12,103,226,288,984,15,183,488,245,223,732,8,870,806,641,663,752,468,269,275,651,378,471,259,219},
                new int[] {483,76,190,396,531,330,847,356,79,392,14,322,24,995,193,532,185,885,888,637,950,895,216,860,345,690,29,250,926,586,913,263,855,343,403,416,433,529,492,52,709,676,836,503,767,775,208,75,861,204,525,43,929,122,966,582,451,115,46,793,462,493,886,801,819,181,574,30,912,14,946,908,15,693,140,94,212,970,62,374,306,10,717,708,220,544,742,716,413,555,969,895,92,711,506,989,469,354,819,510},
        };
        boolean wrongDirection = false;
        boolean ok = true;
        try {
            for (int testNb = 0; testNb < tests.length; testNb++) {
                // Get the test
                int[] test = tests[testNb];
                int[] testSorted = new int[test.length];
                System.arraycopy(test, 0, testSorted, 0, test.length);
                Arrays.sort(testSorted);

                // Generate the tree
                InstanceConfig info = new InstanceConfig();
                Node root = new BaseNode(test[0], info);
                for (int i = 1; i < test.length; i++)
                    root.add(test[i]);

                // Test all the possibilities
                int posInSorted = 0;
                for (int i = 0; i <= 1000; i++) {
                    info.reset(i);
                    while (posInSorted != testSorted.length && testSorted[posInSorted] < i)
                        posInSorted++;
                    Integer expected = null;
                    if (posInSorted != testSorted.length)
                        expected = testSorted[posInSorted];
                    Integer returned = Ceil.ceil(root, i);
                    wrongDirection |= info.wrongDirection;
                    if(returned == null && expected != null)
                        ok = false;
                    if(expected == null && returned != null)
                        ok = false;
                    if(returned != null && expected != null && !returned.equals(expected))
                        ok = false;
                }
            }
            if(ok && !wrongDirection){}
            else if(ok && wrongDirection) {
                System.out.println("wrongDirection");
                assertTrue(false);
            }
            else { assertTrue(false);}

        }
        catch (Exception e) {
            System.out.println("exception");
        }
    }
}