import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


public class BSTTestExtreme {


    @Test
    @Grade(value=15)
    public void testIteratorList() {
        char db[] = new char[] {'S', 'E', 'A', 'R', 'C', 'H', 'E', 'X', 'A', 'M', 'P', 'L', 'E'};
        BST<String, Integer> student = new BST<String, Integer>();

        Iterator<String> iterator = student.iterator();
        //assertFalse(iterator.hasNext());

        Set<String> correct = new TreeSet<>();
        for (int i = 0; i < db.length; i++) {
            String key = ""+db[i];
            student.put(key, i);
            if (i==6) {
                assertEquals(i,student.size());
            }
            assertEquals(student.get(key).intValue(),i);
            correct.add(key);
        }

        Iterator<String> aIter = student.iterator();
        Iterator<String> bIter = correct.iterator();

        while (bIter.hasNext()) {
            assertTrue(aIter.hasNext());
            assertEquals(bIter.next(),aIter.next());
        }
        assertFalse(bIter.hasNext());
        assertFalse(aIter.hasNext());

    }

    @Test(expected = NoSuchElementException.class)
    @Grade(value=10)
    public void testNoSuchElementException() {
        BST<String,Integer> student = new BST<>();
        student.iterator().next();

    }


    @Test(expected = ConcurrentModificationException.class)
    @Grade(value=10)
    public void testConcurrentModificationNext() {
        BST<String,Integer> student = new BST<>();
        student.put("A",1);

        Iterator iter = student.iterator();
        student.put("B",2);
        iter.next();
    }





}

