public class Interval implements Comparable<Interval> {

    final int min, max;

    public Interval(int min, int max) {
        assert(min <= max);
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Interval) obj).min == min && ((Interval) obj).max == max;
    }

    @Override
    public String toString() {
        return "["+min+","+max+"]";
    }

    @Override
    public int compareTo(Interval o) {
        if (this.min < o.min) return -1;
        else if (this.min == o.min) return max - o.max;
        else return 1;
    }
}
