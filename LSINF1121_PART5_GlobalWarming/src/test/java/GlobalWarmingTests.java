import com.github.guillaumederval.javagrading.Grade;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

import static junit.framework.TestCase.*;

public class GlobalWarmingTests {

    final int [] seeds = new int[]{10,87,83};

    Random rand = new Random(seeds[new java.util.Random().nextInt(3)]);

    abstract class TimeLimitedCodeBlock {

        public boolean run(long time) {
            final Runnable stuffToDo = new Thread() {
                @Override
                public void run() {
                    codeBlock();
                }
            };

            final ExecutorService executor = Executors.newSingleThreadExecutor();
            final Future future = executor.submit(stuffToDo);
            executor.shutdown(); // This does not cancel the already-scheduled task.
            boolean ok = true;
            try {
                future.get(time, TimeUnit.MILLISECONDS);
            } catch (InterruptedException ie) {
                ok = false;
            } catch (ExecutionException ee) {
                ok = false;
            } catch (TimeoutException te) {
                ok = false;
            }
            if (!executor.isTerminated()) {
                future.cancel(true);
                executor.shutdownNow();
                executor.shutdownNow(); // If you want to stop the code that hasn't finished.
            }
            return ok;
        }

        public abstract void codeBlock();
    }

    public int [][] getSimpleMatrix() {
        int[][] matrix = new int[][]{
                {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1}
        };
        return matrix;
    }


    public int [][] getExamMatrix() {
        int [][] tab = new int[][] {{1,3,3,1,3},
                {4,2,2,4,5},
                {4,4,1,4,2},
                {1,4,2,3,6},
                {1,1,1,6,3}};
        return tab;
    }

    public int [][] getExamMatrix10() {
        int [][] tab = new int[][] {
                {0,9,0,2,4,2,7,5,3,0,},
                {1,0,9,7,3,4,3,2,1,5,},
                {0,1,9,9,4,5,8,7,8,0,},
                {8,1,9,0,5,1,8,5,7,9,},
                {1,2,8,5,9,2,7,6,9,4,},
                {2,9,0,9,2,0,5,1,0,7,},
                {6,4,3,2,5,2,6,4,0,0,},
                {2,7,6,4,6,2,5,7,4,5,},
                {3,4,4,9,8,7,0,9,8,4,},
                {3,3,3,1,7,7,6,7,1,7,}
        };
        return tab;
    }




    public int [][] getRandomMatrix(int n,int bound) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(bound);
            }
        }
        return matrix;
    }

    public static GlobalWarming.Point point(int x, int y) {
        return new GlobalWarming.Point(x,y);
    }

    @Test
    public void test0() {
        GlobalWarming gw =  new GlobalWarmingImpl(new int[][] {{1,3,3,1,3},
                {4,2,2,4,5},
                {4,4,1,4,2},
                {1,4,2,3,6},
                {1,1,1,6,3}}
                ,3);
        assertEquals(4,gw.nbIslands());
    }


    @Test
    @Grade(value=10)
    public void testOnSameIslandExam() {
        GlobalWarming gw =  new GlobalWarmingImpl(getExamMatrix(),3);
        boolean ok1 = gw.onSameIsland(new GlobalWarming.Point(1,3),new GlobalWarming.Point(3,4)) == false;
        boolean ok2 = gw.onSameIsland(new GlobalWarming.Point(1,3),new GlobalWarming.Point(1,4)) == true;
        boolean ok3 = gw.onSameIsland(new GlobalWarming.Point(1,3),new GlobalWarming.Point(2,3)) == true;
        boolean ok4 = gw.onSameIsland(new GlobalWarming.Point(2,3),new GlobalWarming.Point(3,4)) == false;
        assertTrue(ok1 && ok2 && ok3 && ok4);
    }

    @Test
    @Grade(value=10)
    public void testNbIslandsExam() {
        GlobalWarming g =  new GlobalWarmingImpl(getExamMatrix(),3);

        boolean ok = g.nbIslands()==4 || g.nbIslands()==20;

        assertTrue("islands returned (should be 4):"+g.nbIslands(),ok);
    }


    @Test
    @Grade(value=10)
    public void testSimpleAll() {
        int [][] matrix = getSimpleMatrix();
        GlobalWarming warming = new GlobalWarmingImpl(matrix,0);

        assertEquals(6,warming.nbIslands() );
        assertFalse(warming.onSameIsland(point(0,0),point(0,0)));
        assertFalse(warming.onSameIsland(point(0, 0), point(0, 1)));
        assertTrue(warming.onSameIsland(point(4,5),point(1,7)));

    }

    @Test
    @Grade(value=10)
    public void testCorrectnessOnSameIsland() {
        int level = 200000;
        GlobalWarming.Point p1 = point(10,10);
        GlobalWarming.Point p2 = point(15,15);


        for (int k = 0; k < 100; k++) {
            int [][] matrix = getRandomMatrix(100,1000000);
            GlobalWarming g1 = new GlobalWarmingImpl(matrix,level);

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100-3; j++) {
                    if (matrix[i][j] > level && matrix[i][j+1] > level && matrix[i][j+2] > level) {
                        assertTrue(g1.onSameIsland(point(i,j),point(i,j+2)));

                    }
                }
            }
        }
        int [][] matrix = getSimpleMatrix();
        GlobalWarming warming = new GlobalWarmingImpl(matrix,0);
        assertFalse(warming.onSameIsland(point(0,0),point(0,0)));
        assertFalse(warming.onSameIsland(point(0, 0), point(0, 1)));
        assertTrue(warming.onSameIsland(point(4,5),point(1,7)));

    }

    @Test
    @Grade(value=10)
    public void testCorrectnessNbIslands() {

        int level = 200000;


        int[][] matrix = getExamMatrix10();


        GlobalWarming warming = new GlobalWarmingImpl(matrix, 15);
        assertEquals(0,warming.nbIslands());

        warming = new GlobalWarmingImpl(matrix, -15);
        assertTrue(warming.nbIslands()==1);

        matrix[5][0] = 30;
        matrix[0][1] = 30;
        matrix[0][8] = 30;
        matrix[6][0] = 30;
        matrix[9][5] = 30;
        matrix[9][4] = 30;

        warming = new GlobalWarmingImpl(matrix, 25);
        assertTrue(warming.nbIslands()==4 ||warming.nbIslands()==98);



        for (int iter = 0; iter < 100; iter++) {


            matrix = new int[100][100];

            boolean [] generated = new boolean[10];
            int nIslandExpected = 0;
            int k = 0;
            int above = 0;
            int count = 0;
            for (int i = 0; i < rand.nextInt(10); i++) {
                count = 0;
                k = rand.nextInt(8);
                matrix[k*10][k*10] = 1;
                matrix[k*10+1][k*10] = 1;
                matrix[k*10][k*10+1] = 1;
                matrix[k*10+1][k*10+1] = 1;
                if (rand.nextBoolean() && !generated[k]) {
                    matrix[k*10+2][k*10+1] = 1;
                    count++;
                }
                if (rand.nextBoolean() && !generated[k]) {
                    matrix[k*10+2][k*10] = 1;
                    count++;
                }
                if (!generated[k]) {
                    generated[k] = true;
                    nIslandExpected += 1;
                    above+= 4+count;
                }
            }

            warming = new GlobalWarmingImpl(matrix, 0);
            assertTrue(warming.nbIslands()==nIslandExpected || warming.nbIslands()==nIslandExpected+10000-above);

        }

        matrix = getSimpleMatrix();
        warming = new GlobalWarmingImpl(matrix,0);
        assertTrue(warming.nbIslands()==6 ||warming.nbIslands()==78);
    }


    @Test(timeout=500)
    @Grade(value=20)
    public void timeComplexityConstructorCorrect() {
        final int [][] matrix = getRandomMatrix(400,2000000);
        GlobalWarming g = new GlobalWarmingImpl(matrix,1000000 );
    }


    final int [][] matrix = getRandomMatrix(500,2000000);
    final GlobalWarming g = new GlobalWarmingImpl(matrix,1000000 );

    @Test(timeout = 5)
    @Grade(value=15)
    public void timeComplexityNbIslands() {
        g.nbIslands();
    }



    @Test(timeout = 500)
    @Grade(value=15)
    public void timeComplexityOnSameIsland() {
        int n = matrix.length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                g.onSameIsland(point(i,j),point(n-1,n-1));
            }
        }
    }
}
