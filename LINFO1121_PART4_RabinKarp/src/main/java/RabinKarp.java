import java.util.HashMap;

public class RabinKarp
{
    //TODO by student: many things are not correct here
    private String[] pat; // pattern (only needed for Las Vegas)
    private HashMap<String,Long> hashs;
    private int M; // pattern length
    private long Q; // a large prime
    private int R = 2048; // alphabet size
    private long RM; // R^(M-1) % Q

    public RabinKarp(String[] pat)
    {
        int nbPatterns = pat.length;
        this.pat = new String[nbPatterns];
        this.pat = pat;

        this.M = pat[0].length(); // assume every pattern has the same length
        this.Q = 4463;

        this.hashs = new HashMap<String, Long>();
        for(int i =0; i< nbPatterns;i++)
        {
            hashs.put(pat[i], hash(pat[i],M));
        }

        RM = 1;
        for (int i = 1; i <= M-1; i++) // Compute R^(M-1) % Q for use
        {
            RM = (R * RM) % Q; // in removing leading digit.
        }

    }

    // @param i = offset
    public boolean check(String txt, int i) //change to improve complexity
    {
        //TODO by student: it's obviously not correct
        // Las Vegas version: does pat[] match txt[i..i-m+1] ?
        return hashs.containsKey(txt.substring(i,i+M));
    }

    // Las Vegas version: does pat[] match txt[i..i-m+1] ?

    private long hash(String key, int M)
    { // Compute hash for key[0..M-1].
        long h = 0;
        for (int j = 0; j < M; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }


    public int search(String txt)
    {
        // Search for hash match in text.
        int N = txt.length();
        if(N < this.M)
            return N;

        long txtHash = hash(txt, this.M);

        if (hashs.containsValue(txtHash) && check(txt,0))
            return 0;

        for (int i = this.M; i < N; i++)
        {
            // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + this.Q - RM*txt.charAt(i-this.M) % this.Q) % this.Q;
            txtHash = (txtHash*R + txt.charAt(i)) % this.Q;

            int offset = i - this.M + 1;
            if (hashs.containsValue(txtHash) && check(txt,offset))
                return offset;
        }
        return N;// no match
    }
}

