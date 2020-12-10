import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class IncrementalHashTest {

    public boolean correct(char [] input,int[] output, int Q, int M) {

        IncrementalHash hasher = new IncrementalHash(Q,M);

        int prevHash = hasher.hash(input,0);

        for (int i = 1; i < input.length-M; i++) {
            int h1 = hasher.nextHash(input,prevHash,i);
            if (h1 != output[i-1]) return false;
            prevHash = h1;
        }
        return true;
    }

    @Test
    public void hashCorrectOnWords0()
    {
        int M = 5;
        int Q = 100;
        IncrementalHash iH = new IncrementalHash(Q,M);

        char[] t = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        int previous = iH.hash(t,0);

        for(int i=1; i< t.length -M;i++)
        {
            System.out.println("from :" + i);
            System.out.println((iH.hash(t,i) == iH.nextHash(t, previous,i)));
            assertEquals(iH.hash(t,i), iH.nextHash(t, previous,i));
            previous = iH.hash(t,i);
            System.out.println(previous);
        }
    }

    @Test
    public void hashCorrectOnWords1() {
        assertTrue("wrong nextHash value returned: -50\n",
                correct(new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'},new int[]{40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 0,
                5, 10, 15, 20, 25, 30, 35},100,5));
    }

    @Test
    public void hashCorrectOnWords2() {
        assertTrue("wrong nextHash value returned: -30\n",correct(new char[]{'T','h','e',' ','U','l','t','i','m','a','t','e',' ','Q','u','e','s','t','i','o','n',' ',
                'o','f',' ','L','i','f','e',',',' ','t','h','e',' ','U','n','i','v','e','r','s','e',' ','a','n','d',' ',
                'E','v','e','r','y','t','h','i','n','g'},new int[]{85, 46, 91, 94, 90, 92, 68, 64, 27, 22, 42, 56, 6, 56,
                13, 84, 55, 93, 29, 70, 45, 73, 7, 33, 92, 90, 50, 49, 0, 53, 21, 35, 13, 21, 37, 13, 7, 83, 71, 54, 65, 60},
                100,15));
    }

    @Test
    public void hashCorrectOnWords3() {
        assertTrue("wrong nextHash value returned: -20\n",correct(new char[]{'“', 'T', 'h', 'e', ' ', 'q', 'u', 'e', 's', 't', 'i', 'o', 'n', ' ', 'o', 'f', ' ',
                'w', 'h', 'e', 't', 'h', 'e', 'r', ' ', 'a', ' ', 'c', 'o', 'm', 'p', 'u', 't', 'e', 'r', ' ', 'c', 'a',
                'n', ' ', 't', 'h', 'i', 'n', 'k', ' ', 'i', 's', ' ', 'n', 'o', ' ', 'm', 'o', 'r', 'e', ' ', 'i', 'n',
                't', 'e', 'r', 'e', 's', 't', 'i', 'n', 'g', ' ', 't', 'h', 'a', 'n', ' ', 't', 'h', 'e', ' ', 'q', 'u',
                'e', 's', 't', 'i', 'o', 'n', ' ', 'o', 'f', ' ', 'w', 'h', 'e', 't', 'h', 'e', 'r', ' ', 'a', ' ', 's',
                'u', 'b', 'm', 'a', 'r', 'i', 'n', 'e', ' ', 'c', 'a', 'n', ' ', 's', 'w', 'i', 'm', '.', '”'},
                new int[]{68, 25, 3, 55, 90, 28, 16, 61, 31, 56, 91, 92, 53, 13, 74, 11, 13, 35, 97, 60, 45, 5, 36, 99,
                        69, 7, 2, 11, 89, 97, 9, 17, 21, 1, 49, 86, 14, 97, 29, 82, 45, 96, 30, 66},100,75));

    }

    @Test
    public void timeComplexityOK() {
        boolean timeOk = new TimeLimitedCodeBlock() {
            @Override
            public void codeBlock() {
                Random rnd = new Random(0);
                char [] input = new char[(int) 10E5];
                for (int i = 0; i < input.length; i++) {
                    input[i] = (char) rnd.nextInt(65536);
                }
                IncrementalHash hasher = new IncrementalHash(100,3000);

                int prevHash = hasher.hash(input,0);
                for (int i = 1; i < input.length-3000; i++) {
                    int h1 = hasher.nextHash(input,prevHash,i);
                    prevHash = h1;
                }
            }
        }.run(250);
        assertTrue("the nextHash should execute in O(1):-100\n",timeOk);
    }

}

