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
    public Key min() {
        //TODO O(1) expected
        return pq[1];
    }

    /**
     * @pre size() >= 1
     */
    public Key max() {
        //TODO O(1) expected
        if (size()==1)
            return pq[1];
        else if (size()==2)
            return pq[2];
        else if( less(2,3))
            return pq[3];
        else
            return pq[2];
    }

    /**
     * @pre 1 <= k <= size()
     */
    private void swim(int k) {
        //TODO O(log N) expected
        if(isMinLevel(k))
            swimMin(k);
        else
            swimMax(k);
    }

    private void swimMin(int k)
    {
        if(k>1 && less(k/2, k))
        {
            exch(k, k / 2);
            swimMax(k/2);
        }
        else if (k>3 && less(k, k/4))
        {
            exch(k, k/4);
            swimMin(k/4);
        }
    }

    private void swimMax(int k)
    {
        if(k>1 && less(k, k/2))
        {
            exch(k, k / 2);
            swimMin(k/2);
        }
        else if (k>7 && less(k/4, k))
        {
            exch(k, k/4);
            swimMax(k/4);
        }
    }

    private boolean isMinLevel(int k)
    {
        int level = 0;

        while(k>= power(level))
        {
            level++;
        }
        if(level%2==0)
            return true;
        else
            return false;
    }

    private int power(int b)
    {
        int c;
        for(c=2; b>0; b--)
            c=c*2;
        return c;
    }

    // ---------------------------------------------------------------------------------------
    // Constructor and helpers. You should not modify this. However, using them may be useful.
    // ---------------------------------------------------------------------------------------

    public MinMaxHeap(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * @return pq[i] < pq[j]
     */
    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * Exchanges positions i and j in pq
     */
    private void exch(int i, int j) {
        Key e = pq[i];
        pq[i] = pq[j];
        pq[j] = e;
    }

    /**
     * @return true if the heap is empty, false else
     */
    public boolean isEmpty() {
        return N == 0;
    }
    /**
     * @return the size of the heap
     */
    public int size() {
        return N;
    }

    /**
     * @param v value to insert in the heap. v != null.
     */
    public void insert(Key v) {
        pq[++N] = v;
        if (N >= (1 << height)) height++;
        swim(N);
    }

    @Override
    public String toString() {
        return Arrays.toString(pq);
    }
}
