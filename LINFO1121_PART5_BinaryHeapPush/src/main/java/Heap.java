
/**
 * A binary heap where the content is placed in the array `content`.
 *
 * The array `content` represents a tree and is based on the methods we have seen in the course:
 * The ith node of the tree has indices 2*i and 2*i+1 as children.
 *
 * remarks:
 * - This heap uses a 1-indexing, ie. the first index in the array is 1 instead of 0 as usual. This could ease your computations.
 *   By this way, the index O of the array `content` must always stay empty.
 * - You can use the function increaseSize() to double the size of the array `content`, if needed.
 * - The expected complexity is O(log n) for the insertion in the heap.
 */
public class Heap {
    protected int[] content; // store items at indices 1 to size
    protected int size; // number of items on priority queue

    public Heap(int initSize) {
        size = 0;
        content = new int[initSize];
    }

    /**
     * Doubles the size of the inner storage array
     */
    protected void increaseSize() {
        int[] newContent = new int[content.length*2];
        System.arraycopy(content, 0, newContent, 0, content.length);
        content = newContent;
    }

    /**
     * Add a new value to the heap.
     * @param val value to add
     */
    public void push(int val) {
        //TODO
        //operation on this.content.
        //use increaseSize() if needed.
        if(size == content.length-1)
        {
            increaseSize();
        }
        content[++size] = val; // because index start at one !
        swim(size);
    }

    //You can add other functions if needed here
    public void swim(int k)
    {
        while(k>1 && greater(k/2,k)) // if content[k/2] is greater than content[k] => invariant unsatified => exchange needed
        {
            exch(k,k/2);
            k = k/2;
        }

    }

    public void exch(int i, int j)
    {
        int tmp = content[i];
        content[i] = content[j];
        content[j] = tmp;
    }

    public boolean greater(int thisOne, int thatOne)
    {
        if(content[thisOne] > content[thatOne]) // thisOne is greater than thatOne
            return true;
        else
            return false;
    }

    /**
     * Returns the content of the inner array
     */
    public int[] getContent() {
        return content;
    }

    public int getSize() {
        return size;
    }
}
