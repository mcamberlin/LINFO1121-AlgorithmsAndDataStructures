import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class MYBSTTEST
{
    private BST<Integer, Integer> bst = new BST<Integer, Integer>();
    public MYBSTTEST()
    {
        bst.put(12, 12);
        bst.put(8, 8);
        bst.put(18, 18);
        bst.put(3, 3);
        bst.put(11, 11);
        bst.put(14, 14);
        bst.put(20, 20);
        bst.put(9, 9);
        bst.put(15, 15);
    }

    @Test
    public void testNext()
    {
        Iterator<Integer> it = bst.iterator();

        assertEquals(it.next(),new Integer(3));
        assertEquals(it.next(),new Integer(8));
        assertEquals(it.next(),new Integer(9));
        assertEquals(it.next(),new Integer(11));
        assertEquals(it.next(),new Integer(12));
        assertEquals(it.next(),new Integer(14));
        assertEquals(it.next(),new Integer(15));
        assertEquals(it.next(),new Integer(18));
        assertEquals(it.next(),new Integer(20));

    }
}






