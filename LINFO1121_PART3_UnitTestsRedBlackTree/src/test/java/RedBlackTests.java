import org.junit.Test;

import static org.junit.Assert.*;

public class RedBlackTests {


    public boolean isSorted(MyRedBlack<Integer,Integer> mRBT1)
    {
        Iterable<Integer> keys = mRBT1.keys( mRBT1.min(), mRBT1.max());
        Integer previous = Integer.MIN_VALUE;
        int i =0;
        for(Integer key : keys)
        {
            if(i == 0)
            {
                previous = key;
                i++;
            }
            else
            {
                if(key < previous)
                    return false;
            }
        }
        return true;
    }

    @Test
    public void testIsSorted()
    {
        MyRedBlack<Integer,Integer> mRBT1 = new MyRedBlack<Integer, Integer>();
        mRBT1.put(5, 5);
        mRBT1.put(9, 9);
        mRBT1.put(2, 2);
        mRBT1.put(3, 3);
        mRBT1.put(7, 7);
        mRBT1.put(12, 12);
        mRBT1.put(15, 15);
        mRBT1.put(1, 1);
        mRBT1.put(4, 4);
        mRBT1.put(6, 6);
        mRBT1.put(8, 8);
        mRBT1.put(10, 10);
        mRBT1.put(14, 14);
        mRBT1.put(16, 16);
        assertTrue(isSorted(mRBT1));
    }



    @Test
    public void testIsBalanced()
    {
        MyRedBlack<Integer,Integer> mRBT1 = new MyRedBlack<Integer, Integer>();
        mRBT1.put(5, 5);
        assertEquals(mRBT1.height(),0);
        mRBT1.put(9, 9);
        assertEquals(mRBT1.height(),1);
        mRBT1.put(2, 2);
        assertEquals(mRBT1.height(),1);
        mRBT1.put(3, 3);
        assertEquals(mRBT1.height(),2);
        mRBT1.put(7, 7);
        assertEquals(mRBT1.height(),2);
        mRBT1.put(12, 12);
        assertEquals(mRBT1.height(),3);
        mRBT1.put(15, 15);
        assertEquals(mRBT1.height(),3);
        mRBT1.put(1, 1);
        assertEquals(mRBT1.height(),3);
        mRBT1.put(4, 4);
        assertEquals(mRBT1.height(),3);
        mRBT1.put(6, 6);
        assertEquals(mRBT1.height(),3);
        mRBT1.put(8, 8);
        assertEquals(mRBT1.height(),3);
        mRBT1.put(10, 10);
        assertEquals(mRBT1.height(),4);
        mRBT1.put(14, 14);
        assertEquals(mRBT1.height(),4);
        mRBT1.put(16, 16);
        assertEquals(mRBT1.height(),4);
    }



    @Test
    public void putTest()
    {
        MyRedBlack<Integer,Integer> mRBT1 = new MyRedBlack<Integer, Integer>();
        mRBT1.put(5, 5);
        mRBT1.put(9, 9);
        mRBT1.put(2, 2);
        mRBT1.put(3, 3);
        mRBT1.put(7, 7);
        mRBT1.put(12, 12);
        mRBT1.put(15, 15);
        mRBT1.put(1, 1);
        mRBT1.put(4, 4);
        mRBT1.put(6, 6);
        mRBT1.put(8, 8);
        mRBT1.put(10, 10);
        mRBT1.put(14, 14);
        mRBT1.put(16, 16);

        for(Integer i = 1; i<11 ; i++)
        {
            assertEquals(mRBT1.get(i), i);
        }
        assertNull(mRBT1.get(11));
        assertNull(mRBT1.get(13));

        for(Integer i = 14; i <17 ; i++)
        {
            assertEquals(mRBT1.get(i),i);
        }

    }

    @Test
    public void getTest()
    {
        MyRedBlack<Integer,Integer> mRBT = new MyRedBlack<Integer,Integer>();
        mRBT.put(5,5);
        mRBT.put(9,9);
        mRBT.put(2,2);
        mRBT.put(3,3);
        mRBT.put(7,7);
        mRBT.put(12,12);
        mRBT.put(15,15);
        mRBT.put(1,1);
        mRBT.put(4,4);
        mRBT.put(6,6);
        mRBT.put(8,8);
        mRBT.put(10,10);
        mRBT.put(14,14);
        mRBT.put(16,16);

        for(Integer i = 1; i<11 ; i++)
        {
            assertEquals(mRBT.get(i), i);
        }
        assertNull(mRBT.get(11));
        assertNull(mRBT.get(13));

        for(Integer i = 14; i <17 ; i++)
        {
            assertEquals(mRBT.get(i),i);
        }
    }
}
