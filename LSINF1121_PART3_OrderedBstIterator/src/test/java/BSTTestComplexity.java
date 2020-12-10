import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class BSTTestComplexity {

    private BST<String,Integer> student;

    public BSTTestComplexity(BST<String,Integer> student) {
        this.student = student;
    }

    @Test(timeout=200)
    @Grade(value=40)
    public void runAsExpected() {
        long t0 = System.currentTimeMillis();
        Iterator<String> aIter = student.iterator();
        while (aIter.hasNext()) {
           aIter.next();
        }
        assertFalse(aIter.hasNext());
        long t1 = System.currentTimeMillis();
        System.out.println("time constructor=:"+(t1-t0));
    }

    @Test(timeout=10)
    @Grade(value=10)
    public void runAsExpectedBis() {
        long t0 = System.currentTimeMillis();
        Iterator<String> aIter = student.iterator();
        long t1 = System.currentTimeMillis();
        System.out.println("time constructor bis=:"+(t1-t0));
    }


    @Parameterized.Parameters
    public static List<Object> data() throws IOException {
        Random r = new Random();
        LinkedList tests = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            BST<String,Integer> a = new BST<>();
            for (int k = 0; k < 1000000; k++) {
                int val = r.nextInt();
                String key =  ""+val;
                a.put(key,val);
            }

            tests.add(a);
        }
        return tests;
    }
}

