import java.util.List;

public abstract class GlobalWarming {

    /**
     * A class to represent the coordinates on the altitude matrix
     */
    public static class Point {

        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;
            return p.x == x && p.y == y;
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
     * An island is a connected components of safe points wrt to waterLevel
     * @return the number of islands
     */
    public abstract int nbIslands();

    /**
     *
     * @param p1 a point with valid coordinates on altitude matrix
     * @param p2 a point with valid coordinates on altitude matrix
     * @return true if p1 and p2 are on the same island, that is both p1 and p2 are safe wrt waterLevel
     *        and there exists a path (vertical/horizontal moves) from p1 to p2 using only safe positions
     */
        public abstract boolean onSameIsland(Point p1, Point p2);


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
