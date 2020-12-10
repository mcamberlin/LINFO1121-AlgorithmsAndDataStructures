
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class GlobalWarmingTest {

    final int [] seeds = new int[]{0,7,13};

    Random rand = new Random(seeds[new java.util.Random().nextInt(3)]);

    public int [][] getSimpleMatrix() {
        int[][] matrix = new int[][]{
                {0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
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


    @Test
    public void testShortestPathExam() {
        List<GlobalWarming.Point> path = new GlobalWarmingImpl(getExamMatrix(), 3).shortestPath(new GlobalWarming.Point(1, 0), new GlobalWarming.Point(3, 1));
        assertTrue( path != null && path.size() == 4 && validPath(getExamMatrix(),3,point(1,0),point(3,1),path) );
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
    public void test1()
    {
        /*int[][] matrix = new int[][]{
                {0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };*/

        int[][] matrix = getRandomMatrix(15,2);

        GlobalWarming warming = new GlobalWarmingImpl(matrix,-1);

        List<GlobalWarming.Point> path = warming.shortestPath(point(0,0),point(0,5));

        assertTrue("4::error in shortestPath, path not valid", validPath(matrix, -1, point(0, 0), point(0, 5), path));

    }

    @Test
    public void testSimpleAll() {
        assertTrue(simpleAll());
    }
    public boolean simpleAll() {
        int [][] matrix = getSimpleMatrix();
        GlobalWarming warming = new GlobalWarmingImpl(matrix,0);

        List<GlobalWarming.Point> path1 = warming.shortestPath(point(1,1),point(1,1));

            if (!validPath(matrix,0,point(1,1),point(1,1),path1)) {
                System.out.println("1");
                System.out.println("1::error in shortestPath");
                return false;
            }

            if (warming.shortestPath(point(9,9),point(9,9)) != null) {
                System.out.println("2");

                if(!warming.shortestPath(point(9,9),point(9,9)).isEmpty()) {
                    System.out.println("2::error in shortestPath");
                    return false;
                }
            }

            if (warming.shortestPath(point(0,9),point(9,9)) != null ) {
                System.out.println("3");

                if(!warming.shortestPath(point(0,9),point(9,9)).isEmpty()) {
                    System.out.println("3::error in shortestPath");
                    return false;
                }
            }

        List<GlobalWarming.Point> path2 = warming.shortestPath(point(4,5),point(1,7));
            if (!validPath(matrix,0,point(4,5),point(1,7),path2)) {
                System.out.println("4::error in shortestPath, path not valid");
                return false;
            }

            if (path2.size() != 8) {
                System.out.println("error in shortestPath, not correct length");
                System.out.println(path2.size());
                return false;
            }

        return true;
    }

    @Test
    public void testCorrectnessShortestPath() {
        assertTrue(correctnessShortestPath());
    }

    private boolean correctnessShortestPath() {
        int level = 200000;
        GlobalWarming.Point p1 = point(10,10);
        GlobalWarming.Point p2 = point(15,15);

        for (int k = 0; k < 50; k++) {
            int [][] matrix = getRandomMatrix(50,1000000);
            GlobalWarming g1 = new GlobalWarmingImpl(matrix,level);

            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50-3; j++) {
                    if (matrix[i][j] > level && matrix[i][j+1] > level && matrix[i][j+2] > level) {

                        List<GlobalWarming.Point> path = g1.shortestPath(point(i,j),point(i,j+2));

                        if (path.size() != 3 && !validPath(matrix,level,point(i,j),point(i,j+2),path)) {
                            System.out.println("1.");
                            System.out.println("indices = (" + i + ", " + j + ")");
                            System.out.println(path.size());
                            return false;
                        }
                    }
                }
            }
        }
        int [][] matrix = getSimpleMatrix();
        GlobalWarming warming = new GlobalWarmingImpl(matrix,0);

        List<GlobalWarming.Point> path2 = warming.shortestPath(point(4,5),point(1,7));
        if (!validPath(matrix,0,point(4,5),point(1,7),path2)) {
            System.out.println("2.");
            return false;
        }

        if (path2.size() != 8) {
            System.out.println("3.");
            return false;
        }
        return true;
    }


    public boolean validPath(int [][] matrix, int level, GlobalWarming.Point p1, GlobalWarming.Point p2, List<GlobalWarming.Point> path) {
        for (GlobalWarming.Point p: path) {
            if (matrix[p.x][p.y] <= level) return false;
        }
        for (int i = 0; i < path.size()-1; i++) {
            if (!neighbors(path.get(i),path.get(i+1))) {
                return false;
            }
        }
        if (matrix[p1.x][p1.y] <= level && !path.isEmpty()) return false;
        if (matrix[p2.x][p2.y] <= level && !path.isEmpty()) return false;

        return !path.isEmpty() && path.get(0).equals(p1) && path.get(path.size()-1).equals(p2);
    }


    public boolean neighbors(GlobalWarming.Point p1,GlobalWarming.Point p2) {
        return Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y) == 1;
    }


    @Test (timeout = 10)
    public void timeComplexityConstructorCorrect() {
        final int [][] matrix = getRandomMatrix(100,2000000);

        int max = 0;
        // do some computation here
        long t0 = System.currentTimeMillis();
        GlobalWarming g = new GlobalWarmingImpl(matrix,1000000 );
        long t1 = System.currentTimeMillis();
        System.out.println("time constructor:"+(t1-t0));
    }

    @Test (timeout = 250)
    public void timeComplexityShortestPath() {
        final int [][] matrix = getRandomMatrix(70,2000000);
        final GlobalWarming g = new GlobalWarmingImpl(matrix,1000000 );

        long t0 = System.currentTimeMillis();
        int n = matrix.length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                g.shortestPath(point(i,j),point(n-1,n-1));
            }
        }
        long t1 = System.currentTimeMillis();
        System.out.println("time shortestPath:"+(t1-t0));

    }

}

