import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {

    private int[] array;

    public GlobalWarmingImpl(int[][] altitude) {
        super(altitude);
        // expected pre-processing time in the constructror : O(n^2 log(n^2))
        // TODO

        array = new int[altitude[0].length * altitude.length];
        for(int i =0; i< altitude.length; i++)
        {
            System.arraycopy(altitude[i],0,array,i*altitude[i].length,altitude[i].length);
            // public static native void arraycopy(Object src,  int  srcPos,
            //                                        Object dest, int destPos,
            //                                        int length);

        } //complexité O(n)

        Arrays.sort(array); // complexité O(n log(n))
        // complexité totale = O(n^2 log(n) ) ?
    }



    public int nbSafePoints(int waterLevel) {
        // TODO
        // expected time complexity O(log(n^2))

        int low = 0;
        int high = array.length-1;
        int mid = low + (high - low)/2;

        if(waterLevel < array[low])
            return array.length;
        else if(waterLevel >= array[high])
            return 0;
        else
        {
            while (low <= high)
            {
                if(array[high] <= waterLevel)
                    break;

                mid = low + (high - low) / 2;
                if (array[mid] <= waterLevel)
                    low = mid + 1;
                else if (array[mid] > waterLevel)
                    high = mid - 1;
            }
            return array.length - mid;
        }
    }

}
