import java.util.Arrays;
public class Union {

    public static Interval [] union(Interval [] intervals)
    {
        if(intervals.length <2)
            return intervals;

        Arrays.sort(intervals);

        Interval[] tmp = new Interval[intervals.length];
        tmp[0] = intervals[0];
        int j = 1; //index of the returned array

        for(int i=1; i<intervals.length; i++)
        {
            if( !( intervals[i].min>=tmp[j-1].min &&  intervals[i].max <= tmp[j-1].max)) // the last interval in tmp doesn't contain the new one
            {
                if (areOverlapped(tmp[j-1], intervals[i]))
                {
                    int max;
                    if(intervals[j-1].max > intervals[i].max)
                        max = intervals[i-1].max;
                    else
                        max = intervals[i].max;

                    Interval newInterval = new Interval(tmp[j-1].min,max);
                    tmp[j-1] = newInterval;
                }
                else
                {
                    Interval newInterval = new Interval(intervals[i].min, intervals[i].max);
                    tmp[j] = newInterval;
                    j++;
                }
            }

        }
        Interval[] result = new Interval[j];

        /*
         * @param      src      the source array.
         * @param      srcPos   starting position in the source array.
         * @param      dest     the destination array.
         * @param      destPos  starting position in the destination data.
         * @param      length   the number of array elements to be copied.
         */
        System.arraycopy(tmp, 0, result, 0, j);

        return result;
    }

    /**
     * @param interval1
     * @param interval2
     * @return
     *  @true if interval1 and interval2 overlap
     *  @false otherwise
     */
    public static boolean areOverlapped(Interval interval1, Interval interval2)
    {
        if((interval1.max - interval1.min) <= (interval2.max - interval2.min) )
        {
            if(interval1.min >= interval2.min && interval1.min <= interval2.max
                || interval1.max >= interval2.min && interval1.max <=interval2.max)
                return true;
        }
        else
        {
            if(interval2.min >= interval1.min && interval2.min <= interval1.max
                    || interval2.max >= interval1.min && interval2.max <=interval1.max)
                return true;
        }
        return false;
    }

}
