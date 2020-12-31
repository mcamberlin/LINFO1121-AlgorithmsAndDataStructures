import java.util.*;

public  class WordTransformationSP {

    /**
     *
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     * @param s
     * @param start
     * @param end
     * @return rotated string
     */
    public static String rotation(String s, int start, int end) {
        return s.substring(0,start)+new StringBuilder(s.substring(start,end)).reverse().toString()+s.substring(end);
    }

    /**
     * Compute the minimal cost from string "from" to string "to" representing the shortest path
     * @param from
     * @param to
     * @return
     */
    public static int minimalCost(String from, String to)
    {
        //TODO

        //Dijkstra tools

        HashMap<String, Integer> distTo = new HashMap<>(); // Graph isn't known in advance... Size, vertices and edges are unknown
        // OK HashMap car String & Integer have hashCode() function
        // HashMap has the property to add, get, contains in constant time (amortized)
        PriorityQueue<Entry> priorityQueue = new PriorityQueue<>();

        distTo.put(from, 0); // initial distance from source to source is 0
        priorityQueue.add(new Entry(from,0));

        while(!priorityQueue.isEmpty())
        {
            Entry current = priorityQueue.poll();

            for(int i=0; i < to.length()-1; i++)
            {
                for(int j=i+2; j <=to.length(); j++) // the string is always from the left to the right
                {
                    String neighbour = rotation(current.string,i,j);

                    int newDistance = current.distance +j-i;

                    if(!distTo.containsKey(neighbour) || distTo.get(neighbour) > newDistance)
                    {
                        distTo.put(neighbour,newDistance);
                        priorityQueue.add(new Entry(neighbour, newDistance));
                    }
                }
            }
        }
        return distTo.get(to);
    }

    private static class Entry implements Comparable<Entry>
    {
        private String string;
        private int distance;

        public Entry(String s, int distance)
        {
            this.string = s;
            this.distance = distance;
        }

        @Override
        public int compareTo(Entry e)
        {
            return this.distance - e.distance;
        }
    }
}
