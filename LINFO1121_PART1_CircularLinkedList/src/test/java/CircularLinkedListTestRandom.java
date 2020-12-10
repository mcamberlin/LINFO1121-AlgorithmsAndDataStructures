
import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;



@RunWith(Parameterized.class)
public class CircularLinkedListTestRandom {
    private CircularLinkedList<Integer> student;
    private List<Integer> correct;


    public CircularLinkedListTestRandom(CircularLinkedList<Integer> student, List<Integer> correct) {
        this.student = student;
        this.correct = correct;
    }

    @Test
    @Grade(value=15)
    public void runAsExpected() {
        Iterator<Integer> aIter = student.iterator();
        Iterator<Integer> bIter = correct.iterator();
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
        LinkedList tests = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            CircularLinkedList<Integer> a = new CircularLinkedList<>();
            List<Integer> b = new LinkedList<>();
            for (int k = 0; k < 100; k++) {
                int v = r.nextInt();
                a.enqueue(v);
                b.add(v);
            }
            if (i%2 == 0) {
                a.remove(10);
                b.remove(10);
                a.remove(0);
                b.remove(0);
                a.remove(a.size()-1);
                b.remove(b.size()-1);
            }
            tests.add(new Object[]{a,b});
        }
        return tests;
    }
}

