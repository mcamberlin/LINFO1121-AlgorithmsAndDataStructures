public class IncrementalHash {


    private static final int R = 31;
    private int M;
    private int RM;
    private int Q;

    /**
     *
     * @param Q is the modulo to apply
     * @param M is the length of the words to hash
     */
    public IncrementalHash(int Q, int M) {
        assert(M > 0);
        assert(Q > 0);
        this.Q = Q;
        this.M = M;
        // computes (R^(M-1))%Q
        // which might be useful for implementing nextHash
        RM = 1;
        for (int i = 1; i <= M-1; i++) {
            RM = (RM * R)%Q;
        }
    }

    /**
     * Compute the hash function on the substring
     * t[from,...,from+M-1] in O(M)
     * @param t
     * @param 0 <= from <= t.length-M
     * @return (t[from]*R^(M-1)+t[from+1]*R^(M-2)+...+t[from+M-1])%Q
     */
    public int hash(char[] t, int from) {
        int h = 0;
        for (int i = from; i < from+M; i++) {
            h = (R*h+t[i]) % Q;
        }
        return h;
    }

    /**
     * Compute the hash function on the substring
     * t[from,...,from+M-1] in O(1)
     * @param t
     * @param previousHash = hash(from-1)
     * @param 0 < from <= t.length-M
     * @return (t[from]*R^(M-1)+t[from+1]*R^(M-2)+...+t[from+M-1])%Q
     */
    public int nextHash(char[] t, int previousHash, int from) {
        // TODO, obviously this is not O(1)
        int r = 1;
        for(int i =0; i<M-1;i++)
            r *=R;
        return ((previousHash + t[from-1] * (Q-RM))*R + t[from+M-1]) %Q;
    }


    public static void main(String[] args)
    {
        char[] t = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


        IncrementalHash iH = new IncrementalHash(100,5);
        int hashA = iH.hash(t,0);
        System.out.println("hashA : " + hashA);
        int hashB = iH.hash(t,1);
        System.out.println("hashB : " + hashB);

        System.out.println(iH.nextHash(t,hashA,1));
    }
}
