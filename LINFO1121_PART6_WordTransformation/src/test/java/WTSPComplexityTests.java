//package tests;

import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

//import student.GlobalWarmingImpl;
//import student.GlobalWarming;

@RunWith(Parameterized.class)
public class WTSPComplexityTests {
    private String from;
    private static final int SIZE_OF_WORD = 7; //<=10
    private static final int TEST_SIZE = 10;
    private static int[] seeds = {24, 123, 1234, 12345 };

    public WTSPComplexityTests(String from) {
        this.from = from;
    }

    @Test(timeout=50)
    @Grade(value=4) //4*10 = 40
    public void runAsExpected() {
        Random r = new Random(seeds[2]);
        long t0 = System.currentTimeMillis();
        String s = from;
        String d = Helpers.shuffle(s, r.nextInt());
        WordTransformationSP.minimalCost(s, d);
        long t1 = System.currentTimeMillis();
        System.out.println("time constructor=:"+(t1-t0));
    }

    @Test(timeout=4000)
    @Grade(value=1) //1*10 = 10
    public void runAsExtreme() {
        Random r = new Random(seeds[1]);

        long t0 = System.currentTimeMillis();

        String s = from+Helpers.generateWord(9- SIZE_OF_WORD,seeds[3]);
        String d = Helpers.shuffle(s,seeds[0]);

        WordTransformationSP.minimalCost(s, d);

        long t1 = System.currentTimeMillis();

        System.out.println("time constructor bis=:"+(t1-t0));
    }

    @Parameterized.Parameters
    public static String[] data() {
        Random r = new Random(seeds[0]);
        String[] tests = new String[TEST_SIZE];

        for (int i = 0; i < TEST_SIZE; i++) {
            tests[i] = Helpers.generateWord(SIZE_OF_WORD, r.nextInt() );
        }
        return tests;
    }


}

