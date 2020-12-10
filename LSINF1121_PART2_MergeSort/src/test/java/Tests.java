import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class Tests {

    @Test
    public void testSortOdd()
    {
        String message = "Test [7 4 2 8 3]";
        Integer[] arr = new Integer[]{7, 4, 2, 8, 3};

        MergeSort.sort(arr);
        assertArrayEquals(message, new Integer[]{2, 3, 4, 7, 8}, arr);
    }

    @Test
    public void testSortEven()
    {
        String message = "Test [1 9 5 3 7 6]";
        Integer[] arr = new Integer[]{1, 9, 5, 3, 7, 6};

        MergeSort.sort(arr);
        assertArrayEquals(message, new Integer[]{1, 3, 5, 6, 7, 9}, arr);
    }
}
