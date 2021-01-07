import java.util.Arrays;

public class MinMaxHeap<Key extends Comparable<Key>> {

    // ---------------------------------------------------------------------------------------
    // Instance variables
    // ---------------------------------------------------------------------------------------

    // You are not allowed to add/delete variables, nor modifying their names or visibility.
    public Key[] pq;          // contains the elements starting at position 1
    public int N = 0;         // number of elements in the heap
    public int height = 0;    // should help you to know if you are at a level min or max

    // ---------------------------------------------------------------------------------------------------
    // Functions that you should implement. This is the only part of this class that you should modify ;-)
    // ---------------------------------------------------------------------------------------------------

    /**
     * @pre size() >= 1
     */
    public Key min()
    {
        //TODO O(1) expected
        if (isEmpty())
            return null;
        return pq[1];
    }

    /**
     * @pre size() >= 1
     */
    public Key max()
    {
        //TODO O(1) expected
        if (isEmpty())
            return null;
        else if (size() == 1)
            return pq[1];
        else if (size() == 2)
            return pq[2];
        else if (pq[2].compareTo(pq[3]) < 0)
            return pq[3];
        else
            return pq[2];
    }

    /**
     * @pre 1 <= k <= size()
     */
    private void swim(int k)
    {
        //TODO O(log N) expected
        //1. Determine in which level we are
        int level = level(k);
        if (level % 2 == 0) // => insertion in a min level
        {
            int i;
            //check min levels
            for (i = k; i / 4 > 0 && pq[i].compareTo(pq[i / 4]) < 0; i = i / 4)
            {
                exch(i, i / 4);
            } // correctly positionned

            //check max levels
            if (i / 2 > 0 && pq[i].compareTo(pq[i / 2]) > 0)
            {
                exch(i, i / 2);
                swim(i / 2);
            }
        } else // insertion in a max level
        {
            int i;
            //check max levels
            for (i = k; i / 4 > 0 && pq[i].compareTo(pq[i / 4]) > 0; i = i / 4)
            {
                exch(i, i / 4);
            } // correctly positionned

            //check min levels
            if (i / 2 > 0 && pq[i].compareTo(pq[i / 2]) < 0)
            {
                exch(i, i / 2);
                swim(i / 2);
            }

        }

    }

    public static int level(int k)
    {
        if (k == 1)
            return 1;
        int level = 0;
        while (k != 1)
        {
            k = k / 2;
            level++;
        }
        return level;
    }

    // ---------------------------------------------------------------------------------------
    // Constructor and helpers. You should not modify this. However, using them may be useful.
    // ---------------------------------------------------------------------------------------

    public MinMaxHeap(int maxN)
    {
        this.pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * @return pq[i] < pq[j]
     */
    public boolean less(int i, int j)
    {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * Exchanges positions i and j in pq
     */
    private void exch(int i, int j)
    {
        Key e = pq[i];
        pq[i] = pq[j];
        pq[j] = e;
    }

    /**
     * @return true if the heap is empty, false else
     */
    public boolean isEmpty()
    {
        return N == 0;
    }

    /**
     * @return the size of the heap
     */
    public int size()
    {
        return N;
    }

    /**
     * @param v value to insert in the heap. v != null.
     */
    public void insert(Key v)
    {
        pq[++N] = v;
        if (N >= (1 << height)) height++;
        swim(N);
    }

    @Override
    public String toString()
    {
        return Arrays.toString(pq);
    }
}
