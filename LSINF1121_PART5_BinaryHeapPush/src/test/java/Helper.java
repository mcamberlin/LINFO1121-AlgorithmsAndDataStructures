
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Helper {
    public static List<Object[]> readTestData() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();

        File file = new File("data/easy.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int nTests = Integer.parseInt(st.nextToken());

        for(int t = 0; t < nTests; t++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int steps = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] content = new int[size];
            for(int i = 0; i < size; i++)
                content[i] = Integer.parseInt(st.nextToken());

            int[][] expected = new int[steps][];
            int[] expectedSteps = new int[steps];

            for(int i = 0; i < steps; i++) {
                st = new StringTokenizer(br.readLine());
                expectedSteps[i] = Integer.parseInt(st.nextToken());
                int length = Integer.parseInt(st.nextToken());
                expected[i] = new int[length];
                for(int j = 0; j < length; j++)
                    expected[i][j] = Integer.parseInt(st.nextToken());
            }

            list.add(new Object[] {content, expected, expectedSteps});
        }

        return list;
    }
}
