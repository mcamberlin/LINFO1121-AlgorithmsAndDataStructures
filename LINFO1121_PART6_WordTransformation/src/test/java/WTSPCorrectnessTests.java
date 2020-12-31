//package tests;

import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

import java.util.*;

//import student.GlobalWarmingImpl;
//import student.GlobalWarming;

@RunWith(Parameterized.class)
public class WTSPCorrectnessTests {
    private String from;
    private static final int SIZE_OF_WORD = 7; //<=10
    private static final int TEST_SIZE = 10;

    public WTSPCorrectnessTests(String from) {
        this.from = from;
    }

    @Test
    @Grade(value = 2)
    public void simpleTest() {
        Random r = new Random(45);
        int x = ((int) r.nextFloat()*(SIZE_OF_WORD-1)) + 2, y = ((int) r.nextFloat()*(SIZE_OF_WORD-1)) + 2;
        int start = Math.min(x,y), end = Math.max(x,y);

        String s = from;
        String d = WordTransformationSP.rotation(s,start,end);
        assertEquals(end-start, WordTransformationSP.minimalCost(s, d));
    }

    @Parameterized.Parameters
    public static String[] data() {
        Random r = new Random(12345L);
        String[] tests = new String[TEST_SIZE];

        for (int i = 0; i < TEST_SIZE; i++) {
            tests[i] = Helpers.generateWord(SIZE_OF_WORD, r.nextInt() );
        }
        return tests;
    }


}

