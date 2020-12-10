import java.util.List;

public abstract class GlobalWarming {

    /**
     * A class to represent the coordinates on the altitude matrix
     */
    public static class Point {

        public final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Point)
            {
                Point p = (Point) obj;
                return p.x == x && p.y == y;
            }
            return false;
        }

        public String toString()
        {
            return "(" + x + ", "  + y + ")";
        }
    }

    final int[][] altitude;
    final int waterLevel;

    /**
     * In the following, we assume that the points are connected to
     * horizontal or vertical neighbors but not to the diagonal ones
     * @param altitude is a n x n matrix of int values representing altitudes (positive or negative)
     * @param waterLevel is the water level, every entry <= waterLevel is flooded
     */
    public GlobalWarming(int[][] altitude, int waterLevel) {
        this.altitude = altitude;
        this.waterLevel = waterLevel;
    }


    /**
     *
     * @param p1 a safe point with valid coordinates on altitude matrix
     * @param p2 a safe point (different from p1) with valid coordinates on altitude matrix
     * @return the shortest simple path (vertical/horizontal moves) if any between from p1 to p2 using only vertical/horizontal moves on safe points.
     *         an empty list if not path exists (i.e. p1 and p2 are not on the same island).
     */
    public abstract List<Point> shortestPath(Point p1, Point p2);


    public int nbSafePointsCorrect(int waterLevel) {
        int res = 0;
        for (int i = 0; i < altitude.length; i++) {
            for (int j = 0; j < altitude.length; j++) {
                if (altitude[i][j] > waterLevel) {
                    res++;
                }
            }
        }
        return res;
    }


}
