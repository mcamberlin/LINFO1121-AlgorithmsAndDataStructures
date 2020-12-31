import java.util.PriorityQueue;
import java.util.Stack;

public class MineClimbing {

    private static int m;
    private static int n;

    /**
     * Returns the minimum distance between (startX, startY) and (endX, endY), knowing that
     * you can climb from one mine block (a,b) to the other (c,d) with a cost Math.abs(map[a][b] - map[c][d])
     * Do not forget that the world is round: the position (map.length,i) is the same as (0, i), etc.
     * map is a matrix of size n times m, with n,m > 0.
     * 0 <= startX, endX < n
     * 0 <= startY, endY < m
     */
    public static int best_distance(int[][] map, int startRow, int startCol, int endRow, int endCol)
    {
        //TODO Student

        m = map[0].length;
        n = map.length;
        int size = m * n;

        int[] distTo = new int[size];
        for (int i = 0; i < size; i++)
        {
            distTo[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Entry> priorityQueue = new PriorityQueue<Entry>();

        int source = indice(startRow, startCol, m);
        int destination = indice(endRow, endCol, m);
        distTo[source] = 0;
        priorityQueue.add(new Entry(source, 0));

        while (!priorityQueue.isEmpty())
        {
            Entry current = priorityQueue.remove();

            if (current.position == destination) // to gain rapidity
                break;

            int current_distance = current.dist;
            int current_height = map[current.position / m][current.position % m];


            for (int neighbor : neighbors(map, current.position / m, current.position % m))
            {
                int difference_height = Math.abs(current_height - map[neighbor / m][neighbor % m]);

                if (current_distance + difference_height < distTo[neighbor]) // shorter path found
                {
                    distTo[neighbor] = current_distance + difference_height;
                    priorityQueue.add(new Entry(neighbor, current_distance + difference_height));
                }

            }
        }
        return distTo[destination];
    }

    private static int indice(int x, int y, int m)
    {
        return x * m + y;
    }


    private static class Entry implements Comparable<Entry> {
        int position;
        int dist;

        Entry(int position, int value)
        {
            this.position = position;
            this.dist = value;
        }

        public int compareTo(Entry o)
        {
            return this.dist - o.dist;
        }
    }

    /**
     * @param map
     * @param x
     * @param y
     * @return a iterable of all the different neighbors of the point (x,y)
     */
    private static Iterable<Integer> neighbors(int[][] map, int x, int y)
    {
        Stack<Integer> stack = new Stack<>();
        int index = -1;
        if (x == 0) //check above
        {
            index = indice(n - 1, y, m);
            stack.push(index);
        } else
        {
            index = indice(x - 1, y, m);
            stack.push(index);
        }
        if (x == n - 1) //check below
        {
            index = indice(0, y, m);
            stack.push(index);
        } else
        {
            index = indice(x + 1, y, m);
            stack.push(index);
        }

        if (y == 0) // check left
        {
            index = indice(x, m - 1, m);
            stack.push(index);
        } else
        {
            index = indice(x, y - 1, m);
            stack.push(index);
        }

        if (y == m - 1) //check right
        {
            index = indice(x, 0, m);
            stack.push(index);
        } else
        {
            index = indice(x, y + 1, m);
            stack.push(index);
        }
        return stack;
    }
}
