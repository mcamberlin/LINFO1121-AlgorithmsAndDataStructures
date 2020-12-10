

import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;


@RunWith(Parameterized.class)
public class TestHeapEasy {
    private int[] toInsert;
    private int[][] expected;
    private int[] expectedSteps;

    public TestHeapEasy(int[] toInsert, int[][] expected, int[] expectedSteps) {
        this.toInsert = toInsert;
        this.expected = expected;
        this.expectedSteps = expectedSteps;
    }

    @Test
    @Grade(value=0.01)
    public void runAsExpected() {
        Heap heap = new Heap(15);

        int curStep = 0;
        for(int i = 0; i < toInsert.length; i++) {
            heap.push(toInsert[i]);
            if(curStep != expectedSteps.length && expectedSteps[curStep] == i) {
                check(heap.getContent(), expected[curStep]);
                curStep += 1;
            }
        }
    }

    public void check(int[] recorded, int[] expected) {
        int[] onlyContent = new int[expected.length];
        System.arraycopy(recorded, 1, onlyContent, 0, expected.length);
        assertArrayEquals(expected, onlyContent);
    }

    @Parameterized.Parameters
    public static List<Object[]> data() throws IOException {
        return Helper.readTestData();
    }
}

