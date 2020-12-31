//package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helpers {
    public static String generateWord(int size,int seed) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random(seed);
        StringBuilder buffer = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }

    public static String shuffle(String input, int seed){
        Random r = new Random(seed);

        List<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(r.nextFloat()*characters.size());
            output.append(characters.remove(randPicker));
        }

        return output.toString();
    }

    public static String repeat(String s, int times) {
        if (times <= 0) return "";
        else return s + repeat(s, times-1);
    }

}
