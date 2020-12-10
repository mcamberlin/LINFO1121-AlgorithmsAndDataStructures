import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {

    private static final int INFINITY = Integer.MAX_VALUE;

    private ArrayList<Point>[][] adj ;
    private boolean[][] marked;
    private Point[][] edgeTo;
    private int[][] distTo;

    /**
     * In the following, we assume that the points are connected to
     * horizontal or vertical neighbors but not to the diagonal ones
     * @param altitude is a n x n matrix of int values representing altitudes (positive or negative)
     * @param waterLevel is the water level, every entry <= waterLevel is flooded
     */
    public GlobalWarmingImpl(int[][] altitude, int waterLevel)
    {
        super(altitude,waterLevel);
        // TODO

        adj = new ArrayList[altitude.length][altitude[0].length];
        for(int i=0; i<altitude.length;i++)
        {
            for(int j=0; j< altitude[0].length;j++)
            {
                adj[i][j] = new ArrayList<Point>();
            }
        }


        for(int i= 0; i<altitude.length;i++)
        {
            for(int j=0; j<altitude[0].length;j++)
            {
                if(altitude[i][j]>waterLevel)
                {

                    if(j+1 <altitude[0].length && altitude[i][j+1] > waterLevel) // regarder a droite
                    {
                        adj[i][j+1].add(new Point(i,j));
                        adj[i][j].add(new Point(i,j+1));
                    }

                    if(i+1 <altitude.length && altitude[i+1][j] > waterLevel) // regarder en bas
                    {
                        adj[i+1][j].add(new Point(i,j));
                        adj[i][j].add(new Point(i+1,j));
                    }
                }
            }
        }


    }


    /**
     *
     * @param s a safe point with valid coordinates on altitude matrix
     * @param d a safe point (different from p1) with valid coordinates on altitude matrix
     * @return the shortest simple path (vertical/horizontal moves) if any between from p1 to p2 using only vertical/horizontal moves on safe points.
     *         an empty list if not path exists (i.e. p1 and p2 are not on the same island).
     */
    public List<Point> shortestPath(Point s, Point d) {
        // TODO
        // expected time complexity O(n^2)

        //System.out.println(s + "," + d);
        //assert !s.equals(d);

        // !!! The function @shortestPath can be called several times => Need to reset values at every call.

        marked = new boolean[altitude.length][altitude[0].length];
        edgeTo = new Point[altitude.length][altitude[0].length];
        distTo = new int[altitude.length][altitude[0].length];

        if(altitude[s.x][s.y]<= waterLevel || altitude[d.x][d.y] <= waterLevel)
            return new ArrayList<Point>();

        Queue<Point > queue = new ArrayDeque<>();

        marked[s.x][s.y] = true;
        distTo[s.x][s.y] = 1;
        queue.add(s);

        while( ! queue.isEmpty())
        {
            Point v = queue.remove();
            for(Point w : adj[v.x][v.y])
            {
                if(!marked[w.x][w.y])
                {
                    marked[w.x][w.y] = true;
                    edgeTo[w.x][w.y] = v;
                    distTo[w.x][w.y] = distTo[v.x][v.y] + 1 ;
                    queue.add(w);
                }
            }
        }


        List<Point> path = new ArrayList<>();
        if(!marked[d.x][d.y] || distTo[d.x][d.y] == INFINITY) // d is unreachable from s
            return path;

        Point x;
        //for (x = d; distTo[x.x][x.y] != 0; x = edgeTo[x.x][x.y])
        for (x = d; distTo[x.x][x.y] !=1 ; x = edgeTo[x.x][x.y])
            path.add(x);

        path.add(s);
        Collections.reverse(path);
        /*for(Point p: path)
            System.out.println("("+ p.x + "," + p.y + ")");
        */

        return path;

    }

}
