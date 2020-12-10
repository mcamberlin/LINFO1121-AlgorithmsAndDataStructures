import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class CircularLinkedListTestComplexity {

    private CircularLinkedList<Integer> student;
    private List<Integer> correct;


    public CircularLinkedListTestComplexity(CircularLinkedList<Integer> student, List<Integer> correct) {
        this.student = student;
        this.correct = correct;
    }

    @Test(timeout=500)
    @Grade(value=50)
    public void runAsExpected() {
        int sz = correct.size();
        for (int i = 0; i < sz/2; i++) {
            student.remove(0);
            correct.remove(0);
        }
        Iterator<Integer> aIter = student.iterator();
        Iterator<Integer> bIter = correct.iterator();

        while (bIter.hasNext()) {
            assertTrue(aIter.hasNext());
            assertEquals(bIter.next(),aIter.next());
        }
        assertFalse(bIter.hasNext());
        assertFalse(aIter.hasNext());
    }


    @Parameterized.Parameters
    public static List<Object[]> data() throws IOException {
        LinkedList tests = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            CircularLinkedList<Integer> a = new CircularLinkedList<>();
            List<Integer> b = new LinkedList<>();
            for (int k = 0; k < 1000000; k++) {
                a.enqueue(k);
                b.add(k);
            }
            tests.add(new Object[]{a,b});
        }
        return tests;
    }
}

