//feel free to import anything here

import java.util.NoSuchElementException;
import java.util.Stack;

public class MineClimbing {

    private static int[] distTo;          // distTo[v] = distance  of shortest s->v path
    private static Edge[] edgeTo;            // edgeTo[v] = last edge on shortest s->v path
    private static IndexMinPQ<Integer> priorityQueue; // priority queue of vertices

    private static int m;
    private static int n;

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
    public static int best_distance(int[][] map, int startX, int startY, int endX, int endY) {
        //TODO Student

        n = map.length;
        m = map[0].length;

        edgeTo = new Edge[n * m];
        distTo = new int[n * m];
        for (int i = 0; i < n * m; i++)
            distTo[i] = Integer.MAX_VALUE;

        distTo[indice(startX, startY, m)] = 0;
        priorityQueue = new IndexMinPQ<>(n * m); //To keep track of vertices that are candidates for being the newt to be relaxed
        int s = indice(startX, startY, m);
        priorityQueue.insert(s, distTo[s]);

        while (!priorityQueue.isEmpty()) {
            int v = priorityQueue.delMin();
            for (Edge e : neighbors(map, v / m, v % m)) // each neighbors
                relax(e, v);
        }

        return distTo[indice(endX, endY, m)];
    }

    private static int indice(int x, int y, int m) {
        return x * m + y;
    }

    // relax edge e and update pq if changed
    private static void relax(Edge e, int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;

            if (priorityQueue.contains(w))
                priorityQueue.decreaseKey(w, distTo[w]);
            else
                priorityQueue.insert(w, distTo[w]);
        }
    }

    /**
     * @param map
     * @param x
     * @param y
     * @return a iterable of all the different neighbors of the point (x,y)
     */
    private static Iterable<Edge> neighbors(int[][] map, int x, int y) {
        Stack<Edge> stack = new Stack<>();
        int index = -1;
        if (x == 0) //check above
        {
            index = indice(n - 1, y, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[n - 1][y]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        } else {
            index = indice(x - 1, y, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[x - 1][y]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        }

        if (x == n - 1) //check below
        {
            index = indice(0, y, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[0][y]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        } else {
            index = indice(x + 1, y, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[x + 1][y]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        }

        if (y == 0) // check left
        {
            index = indice(x, m - 1, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[x][m - 1]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        } else {
            index = indice(x, y - 1, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[x][y - 1]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        }

        if (y == m - 1) //check right
        {
            index = indice(x, 0, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[x][0]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        } else {
            index = indice(x, y + 1, m);
            Edge edge = new Edge(indice(x, y, m), index, Math.abs(map[x][y] - map[x][y + 1]));
            edgeTo[indice(x, y, m)] = edge;
            edgeTo[index] = edge;
            stack.push(edge);
        }
        return stack;
    }

    private static class Edge {
        private final int v; // vertex
        private final int w;
        private final int weight;

        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int other(int x) {
            if (x == this.v)
                return w;
            else
                return this.v;
        }

        public int weight() {
            return weight;
        }
    }

    /******************************************************************************
     *  Compilation:  javac IndexMinPQ.java
     *  Execution:    java IndexMinPQ
     *  Dependencies: StdOut.java
     *
     *  Minimum-oriented indexed PQ implementation using a binary heap.
     *
     ******************************************************************************/

    private static class IndexMinPQ<Key extends Comparable<Key>> {
        private int maxN;        // maximum number of elements on PQ
        private int n;           // number of elements on PQ
        private int[] pq;        // binary heap using 1-based indexing
        private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
        private Key[] keys;      // keys[i] = priority of i

        /**
         * Initializes an empty indexed priority queue with indices between {@code 0}
         * and {@code maxN - 1}.
         *
         * @param maxN the keys on this priority queue are index from {@code 0}
         *             {@code maxN - 1}
         * @throws IllegalArgumentException if {@code maxN < 0}
         */
        public IndexMinPQ(int maxN) {
            if (maxN < 0) throw new IllegalArgumentException();
            this.maxN = maxN;
            n = 0;
            keys = (Key[]) new Comparable[maxN + 1];    // make this of length maxN??
            pq = new int[maxN + 1];
            qp = new int[maxN + 1];                   // make this of length maxN??
            for (int i = 0; i <= maxN; i++)
                qp[i] = -1;
        }

        /**
         * Returns true if this priority queue is empty.
         *
         * @return {@code true} if this priority queue is empty;
         * {@code false} otherwise
         */
        public boolean isEmpty() {
            return n == 0;
        }

        /**
         * Is {@code i} an index on this priority queue?
         *
         * @param i an index
         * @return {@code true} if {@code i} is an index on this priority queue;
         * {@code false} otherwise
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         */
        public boolean contains(int i) {
            validateIndex(i);
            return qp[i] != -1;
        }

        /**
         * Returns the number of keys on this priority queue.
         *
         * @return the number of keys on this priority queue
         */
        public int size() {
            return n;
        }

        /**
         * Associates key with index {@code i}.
         *
         * @param i   an index
         * @param key the key to associate with index {@code i}
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @throws IllegalArgumentException if there already is an item associated
         *                                  with index {@code i}
         */
        public void insert(int i, Key key) {
            validateIndex(i);
            if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
            n++;
            qp[i] = n;
            pq[n] = i;
            keys[i] = key;
            swim(n);
        }

        /**
         * Returns an index associated with a minimum key.
         *
         * @return an index associated with a minimum key
         * @throws NoSuchElementException if this priority queue is empty
         */
        public int minIndex() {
            if (n == 0) throw new NoSuchElementException("Priority queue underflow");
            return pq[1];
        }

        /**
         * Returns a minimum key.
         *
         * @return a minimum key
         * @throws NoSuchElementException if this priority queue is empty
         */
        public Key minKey() {
            if (n == 0) throw new NoSuchElementException("Priority queue underflow");
            return keys[pq[1]];
        }

        /**
         * Removes a minimum key and returns its associated index.
         *
         * @return an index associated with a minimum key
         * @throws NoSuchElementException if this priority queue is empty
         */
        public int delMin() {
            if (n == 0) throw new NoSuchElementException("Priority queue underflow");
            int min = pq[1];
            exch(1, n--);
            sink(1);
            assert min == pq[n + 1];
            qp[min] = -1;        // delete
            keys[min] = null;    // to help with garbage collection
            pq[n + 1] = -1;        // not needed
            return min;
        }

        /**
         * Returns the key associated with index {@code i}.
         *
         * @param i the index of the key to return
         * @return the key associated with index {@code i}
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @throws NoSuchElementException   no key is associated with index {@code i}
         */
        public Key keyOf(int i) {
            validateIndex(i);
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            else return keys[i];
        }

        /**
         * Change the key associated with index {@code i} to the specified value.
         *
         * @param i   the index of the key to change
         * @param key change the key associated with index {@code i} to this key
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @throws NoSuchElementException   no key is associated with index {@code i}
         */
        public void changeKey(int i, Key key) {
            validateIndex(i);
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            keys[i] = key;
            swim(qp[i]);
            sink(qp[i]);
        }

        /**
         * Change the key associated with index {@code i} to the specified value.
         *
         * @param i   the index of the key to change
         * @param key change the key associated with index {@code i} to this key
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @deprecated Replaced by {@code changeKey(int, Key)}.
         */
        @Deprecated
        public void change(int i, Key key) {
            changeKey(i, key);
        }

        /**
         * Decrease the key associated with index {@code i} to the specified value.
         *
         * @param i   the index of the key to decrease
         * @param key decrease the key associated with index {@code i} to this key
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @throws IllegalArgumentException if {@code key >= keyOf(i)}
         * @throws NoSuchElementException   no key is associated with index {@code i}
         */
        public void decreaseKey(int i, Key key) {
            validateIndex(i);
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            if (keys[i].compareTo(key) == 0)
                throw new IllegalArgumentException("Calling decreaseKey() with a key equal to the key in the priority queue");
            if (keys[i].compareTo(key) < 0)
                throw new IllegalArgumentException("Calling decreaseKey() with a key strictly greater than the key in the priority queue");
            keys[i] = key;
            swim(qp[i]);
        }

        /**
         * Increase the key associated with index {@code i} to the specified value.
         *
         * @param i   the index of the key to increase
         * @param key increase the key associated with index {@code i} to this key
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @throws IllegalArgumentException if {@code key <= keyOf(i)}
         * @throws NoSuchElementException   no key is associated with index {@code i}
         */
        public void increaseKey(int i, Key key) {
            validateIndex(i);
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            if (keys[i].compareTo(key) == 0)
                throw new IllegalArgumentException("Calling increaseKey() with a key equal to the key in the priority queue");
            if (keys[i].compareTo(key) > 0)
                throw new IllegalArgumentException("Calling increaseKey() with a key strictly less than the key in the priority queue");
            keys[i] = key;
            sink(qp[i]);
        }

        /**
         * Remove the key associated with index {@code i}.
         *
         * @param i the index of the key to remove
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @throws NoSuchElementException   no key is associated with index {@code i}
         */
        public void delete(int i) {
            validateIndex(i);
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            int index = qp[i];
            exch(index, n--);
            swim(index);
            sink(index);
            keys[i] = null;
            qp[i] = -1;
        }

        // throw an IllegalArgumentException if i is an invalid index
        private void validateIndex(int i) {
            if (i < 0) throw new IllegalArgumentException("index is negative: " + i);
            if (i >= maxN) throw new IllegalArgumentException("index >= capacity: " + i);
        }

        /***************************************************************************
         * General helper functions.
         ***************************************************************************/
        private boolean greater(int i, int j) {
            return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
        }

        private void exch(int i, int j) {
            int swap = pq[i];
            pq[i] = pq[j];
            pq[j] = swap;
            qp[pq[i]] = i;
            qp[pq[j]] = j;
        }


        /***************************************************************************
         * Heap helper functions.
         ***************************************************************************/
        private void swim(int k) {
            while (k > 1 && greater(k / 2, k)) {
                exch(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && greater(j, j + 1)) j++;
                if (!greater(k, j)) break;
                exch(k, j);
                k = j;
            }
        }

    }


    // you may need to add additional things below.
}
