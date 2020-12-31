public class StationTime implements Comparable<StationTime> {
    //ne modifiez pas cette classe

    public final String pos; //la gare
    public final int time; //le temps

    public StationTime(String pos, int time)
    {
        this.pos = pos;
        this.time = time;
    }

    @Override
    public int hashCode()
    {
        return pos.hashCode() ^ Integer.hashCode(~time);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof StationTime)
            return ((StationTime) obj).pos.equals(pos) && ((StationTime) obj).time == time;
        return false;
    }

    @Override
    public int compareTo(StationTime o)
    {
        int out = time - o.time;
        if (out == 0)
            return pos.compareTo(o.pos);
        return out;
    }

    @Override
    public String toString()
    {
        return "StationTime{" +
                "pos='" + pos + '\'' +
                ", time=" + time +
                '}';
    }
}
