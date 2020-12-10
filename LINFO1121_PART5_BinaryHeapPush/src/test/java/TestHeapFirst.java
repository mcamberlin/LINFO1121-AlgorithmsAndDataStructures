
import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestHeapFirst {
    @Test
    @Grade(value=0.25)
    public void basicTest() {
        Heap heap = new Heap(10);
        heap.push(5);
        heap.push(1);
        heap.push(2);
        heap.push(3);
        heap.push(8);
        heap.push(10);
        heap.push(6);
        heap.push(0);

        int[] output = new int[]{0, 1, 2, 3, 8, 10, 6, 5};
        assertEquals(8, heap.getSize());
        for(int i = 0; i < 8; i++) {
            assertEquals(output[i], heap.getContent()[i+1]);
        }
    }
}
