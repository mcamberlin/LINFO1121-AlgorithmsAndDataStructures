
import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CircularLinkedListTestExtreme {


    @Test
    @Grade(value=15)
    public void testIteratorList() {
        for (int i = 0; i < 20; i++) {
            CircularLinkedList<Integer> a = new CircularLinkedList<>();
            assertEquals(0,a.size());
            a.enqueue(i);
            assertEquals(1,a.size());
            Iterator itera = a.iterator();
            System.out.println(i);
            assertTrue(itera.hasNext());
            assertEquals(i,itera.next());

            CircularLinkedList<Integer> b = new CircularLinkedList<>();
            b.enqueue(i);
            b.remove(0);
            Iterator iterb = b.iterator();
            assertFalse(iterb.hasNext());

        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    @Grade(value=10)
    public void testOutOfBound() {
        CircularLinkedList<Integer> a = new CircularLinkedList<>();
        a.enqueue(3);
        a.remove(1);
    }


    @Test(expected = ConcurrentModificationException.class)
    @Grade(value=10)
    public void testConcurrentModificationNext() {
        CircularLinkedList<Integer> a = new CircularLinkedList<>();
        Iterator iter = a.iterator();
        a.enqueue(3);
        iter.next();
    }





}

