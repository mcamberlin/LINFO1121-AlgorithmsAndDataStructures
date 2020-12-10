import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class BSTTestRandom {
    private BST<String,Integer> student;
    private Set<String> correct;


    public BSTTestRandom(BST<String,Integer> student, Set<String> correct) {
        this.student = student;
        this.correct = correct;
    }


    @Test
    @Grade(value=15)
    public void runAsExpected() {
        Iterator<String> aIter = student.iterator();
        Iterator<String> bIter = correct.iterator();
        assertEquals(correct.size(),student.size());
        while (bIter.hasNext()) {
            assertTrue(aIter.hasNext());
            assertEquals(bIter.next(),aIter.next());
        }
        assertFalse(bIter.hasNext());
        assertFalse(aIter.hasNext());
    }


    @Parameterized.Parameters
    public static List<Object[]> data() throws IOException {
        Random r = new Random();
        int min=1, max=26;
        LinkedList tests = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            BST<String,Integer> a = new BST<>();
            Set<String> b = new TreeSet<>();
            for (int k = 0; k < 15; k++) {
                int v = r.nextInt((max - min) + 1) + min;
                a.put(getCharForNumber(v),v);
                b.add(getCharForNumber(v));
            }

            tests.add(new Object[]{a,b});
        }
        return tests;
    }
    public static String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    }
}

