import java.util.LinkedList;
import java.util.PriorityQueue;

public class MineClimbing {
    /**
     * Returns the minimum distance between (startX, startY) and (endX, endY), knowing that
     * you can climb from one mine block (a,b) to the other (c,d) with a cost Math.abs(map[a][b] - map[c][d])
     * <p>
     * Do not forget that the world is round: the position (map.length,i) is the same as (0, i), etc.
     * <p>
     * map is a matrix of size n times m, with n,m > 0.
     * <p>
     * 0 <= startX, endX < n
     * 0 <= startY, endY < m
     */
    public static int best_distance(int[][] map, int startX, int startY, int endX, int endY)
    {
        //TODO Student
        //idea
        //shortest path between a source and a destination and only with positive weights => Dijkstra

        //Tools for Dijskra
        int[][] distTo = new int[map.length][map[0].length];

        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                distTo[i][j] = Integer.MAX_VALUE;
        int[][][] edgeTo = new int[map.length][map[0].length][2];
        PriorityQueue<Entry> priorityQueue = new PriorityQueue<>();

        distTo[startX][startY] = 0;
        edgeTo[startX][startY] = new int[]{startX, startY};

        priorityQueue.add(new Entry(startX, startY, 0));
        while (!priorityQueue.isEmpty())
        {
            Entry current = priorityQueue.poll();
            if (current.x == endX && current.y == endY)
                return distTo[endX][endY];

            for (Entry neighbor : neighbors(current, map))
            {
                if (distTo[neighbor.x][neighbor.y] > distTo[current.x][current.y] + Math.abs(map[current.x][current.y] - map[neighbor.x][neighbor.y]))
                {
                    distTo[neighbor.x][neighbor.y] = distTo[current.x][current.y] + Math.abs(map[current.x][current.y] - map[neighbor.x][neighbor.y]);
                    edgeTo[neighbor.x][neighbor.y] = new int[]{current.x, current.y};
                    priorityQueue.add(new Entry(neighbor.x, neighbor.y, distTo[current.x][current.y] + Math.abs(map[current.x][current.y] - map[neighbor.x][neighbor.y])));
                }
            }
        }
        return distTo[endX][endY]; // should never occurs normally
    }

    private static Iterable<Entry> neighbors(Entry current, int[][] map)
    {
        LinkedList<Entry> rslt = new LinkedList<>();
        //check north
        if (current.x == 0) // on the first line
        {
            int neighborX = map.length - 1;
            int neighborY = current.y;
            int neighborDist = Math.abs(map[current.x][current.y] - map[neighborX][neighborY]);
            rslt.add(new Entry(neighborX, neighborY, neighborDist));
        } else
        {
            rslt.add(new Entry(current.x - 1, current.y, Math.abs(map[current.x][current.y] - map[current.x - 1][current.y])));
        }
        //check south
        if (current.x == map.length - 1)
        {
            int neighborX = 0;
            int neighborY = current.y;
            int neighborDist = Math.abs(map[current.x][current.y] - map[neighborX][neighborY]);
            rslt.add(new Entry(neighborX, neighborY, neighborDist));
        } else
        {
            rslt.add(new Entry(current.x + 1, current.y, Math.abs(map[current.x][current.y] - map[current.x + 1][current.y])));
        }

        //check west
        if (current.y == 0)
        {
            int neighborX = current.x;
            int neighborY = map[0].length - 1;
            int neighborDist = Math.abs(map[current.x][current.y] - map[neighborX][neighborY]);
            rslt.add(new Entry(neighborX, neighborY, neighborDist));
        } else
        {
            rslt.add(new Entry(current.x, current.y - 1, Math.abs(map[current.x][current.y] - map[current.x][current.y - 1])));
        }

        //check east
        if (current.y == map[0].length - 1)
        {
            int neighborX = current.x;
            int neighborY = 0;
            int neighborDist = Math.abs(map[current.x][current.y] - map[neighborX][neighborY]);
            rslt.add(new Entry(neighborX, neighborY, neighborDist));
        } else
        {
            rslt.add(new Entry(current.x, current.y + 1, Math.abs(map[current.x][current.y] - map[current.x][current.y + 1])));
        }
        return rslt;
    }

    private static class Entry implements Comparable<Entry> {
        private int x;
        private int y;
        private int dist; // distance between source and current entry

        public Entry(int x, int y, int dist)
        {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        /**
         * Shorter distance towards destination should have the highest priority
         */
        public int compareTo(Entry o)
        {
            return this.dist - o.dist;
        }
    }
}
