import java.util.ArrayList;
import java.util.Arrays;

public class Union {

    public static Interval [] union(Interval [] intervals)
    {
        // TODO
        if(intervals.length == 0)
            return new Interval[]{};
        if(intervals.length ==1)
            return intervals;

        ArrayList<Interval> rslt = new ArrayList<>();

        Arrays.sort(intervals);

        int min = intervals[0].min;
        int max = intervals[0].max;

        for(int i=0; i<intervals.length; i++)
        {
            if(i< intervals.length-1 && overlapped(intervals[i], intervals[i+1]))
            {
                min = intervals[i].min;
                max = intervals[i].max;
                Interval currentMax = intervals[i];
                int j = i+1;
                for(; j<intervals.length && overlapped(currentMax, intervals[j]); j++)
                {
                    if(intervals[j].max >max)
                    {
                        currentMax = intervals[j];
                        max = intervals[j].max;
                    }
                }

                rslt.add(new Interval(min,max));
                i = j-1;
            }
            else
            {
                rslt.add(intervals[i]);
            }
        }
        return rslt.toArray(new Interval[0]);
    }

    /***
     *
     * @param u1 = union such that u1.compareTo(u2) <0
     * @param u2
     * @return true if both u1 and u2 overlap
     *          false otherwise
     */
    public static boolean overlapped(Interval u1, Interval u2)
    {
        if(u1.max < u2.min)
            return false;
        return true;
    }

}
