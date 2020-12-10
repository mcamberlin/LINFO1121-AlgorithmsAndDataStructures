import java.util.Random;

public class Median {

    public static int median(Vector a, int lo, int hi) {

        int middle = a.size()/2;

        while(hi > lo)
        {
            int j = partition(a,lo,hi);

            if(j == middle)
                return a.get(j);
            else if(j > middle)
                hi = j-1;
            else
                lo = j+1;

        }
        return a.get(middle);
    }

    public static int partition(Vector v, int low , int high)
    {
        int i = low+1; //index to the right scanner
        int j = high; //index to the left scanner
        int pivot = v.get(low);
        while(true)
        {
            //scan to the right
            while(v.get(i)<=pivot)
            {
                if(i==high)
                    break;
                i++;
            } // end of the loop => an element is NOT in the correct order

            //scan to the left
            while(v.get(j) > pivot)
            {
                if(j == low)
                    break;
                j--;
            } // end of the loop => an element is NOT in the correct order

            //check for scan completed
            if(i >= j)
                break;
            v.swap(i, j); // swap the two elements that were disordered
        }
        v.swap(low, j);
        return j;

    }

    public static boolean less(Vector a, int i, int j)
    {
        return a.get(i)<a.get(j);
    }

}
