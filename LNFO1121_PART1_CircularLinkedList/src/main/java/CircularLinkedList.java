
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<Item> implements Iterable<Item> {
    private long nOp = 0; // count the number of operations
    private int n;          // size of the stack
    private Node  last;   // trailer of the list

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    public CircularLinkedList() {
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private long nOp() {
        return nOp;
    }



    /**
     * Append an item at the end of the list
     * @param item the item to append
     */
    public void enqueue(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        if(size() == 0)
        {
            newNode.next = newNode;
            last = newNode;
            n = 1;
        }
        else
        {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
            n++;
        }
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     */
    public Item remove(int index) throws IndexOutOfBoundsException  {
        if(index <0 || index > size()-1 )
            throw new IndexOutOfBoundsException ();
        if (size() == 0)
            return null;

        else if( size() == 1)
        {
            Item it = last.item;
            last.next = null;
            last  = null;
            n--;
            return it;
        }
        else
        {
            int i =0;
            Node runner = last.next;
            Node previous = last;
            while(i<index)
            {
                previous = runner;
                runner = runner.next;
                i++;
            }
            previous.next = runner.next;
            Item it = runner.item;
            runner.next = null;
            runner = null;
            if(index == size() -1)
                last = previous;
            previous = null;
            n--;
            return it;
        }
    }


    /**
     * Returns an iterator that iterates through the items in FIFO order.
     * @return an iterator that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item>
    {
        private Node runner = last;
        private int index = 0;
        private final int exceptedSize = n;

        public boolean hasNext() throws ConcurrentModificationException
        {
            if(exceptedSize != size())
                throw new ConcurrentModificationException();

            if(size() == 0)
                return false;
            return index < size() ;
        }

        public Item next() throws ConcurrentModificationException
        {
            if(exceptedSize != size())
                throw new ConcurrentModificationException();
            if( !hasNext())
                throw new NoSuchElementException();
            runner = runner.next;
            Item item = runner.item;
            index++;
            return item;
        }

        public void remove() throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }
    }

}
