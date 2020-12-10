import java.util.PriorityQueue;

public class Huffman {
    private Huffman() { }

    // Huffman trie node
    public static class Node implements Comparable<Node>{
        public final int ch;
        public final int freq;
        public final Node left, right;

        Node(int ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }

        // is the node a leaf node?
        public boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }
    }

    /**
     * build the Huffman trie given frequencies
     * corresponding to each character codes from 0 to R-1.
     * freq[i] is the frequency for the character with code i
     * freq.length = R.
     * R is either 256 or 65536, depending on whether the user choose to use unicode or ASCII.
     */
    public static Node buildTrie(int R, int[] freq) {
        // TODO
        //First step is to create a forest of 1-node trees (leaves), one for each character in the input stream, each assigned
        PriorityQueue<Node> pq = new PriorityQueue<>(); // Min priority queue
        // run over the frequency array
        for(int c = 0; c <R; c++)
        {
             if(freq[c]>0) // check if the character is used at least once. If so, add it in the priority queues according to its frequency
             {
                 pq.add(new Node(c, freq[c], null, null));
             }
        }

        // merge two smallest trees
        while(pq.size()>1)
        {
            Node x = pq.poll(); //The poll() method is used to retrieve and remove the head of this queue, or returns null if this queue is empty.
            Node y = pq.poll();
            Node parent = new Node('\0', x.freq + y.freq,x,y);
            pq.add(parent);

        }
        return pq.poll();
    }
}
