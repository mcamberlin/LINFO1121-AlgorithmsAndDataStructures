public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    // Please do not add/remove variables/modify their visibility.
    protected int n;           // number of key-value pairs in the symbol table
    protected int m;           // size of linear probing table
    protected Key[] keys;      // the keys
    protected Value[] vals;    // the values


    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[])   new Object[m];
        vals = (Value[]) new Object[m];
    }

    public int size() {
        return n;
    }

    public int capacity() {
        return m;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    // hash function for keys - returns value between 0 and M-1
    protected int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /**
    * resizes the hash table to the given capacity by re-hashing all of the keys
    */
    private void resize(int capacity)
    {
        //TODO STUDENT
        LinearProbingHashST<Key,Value> h = new LinearProbingHashST<>(capacity);
        for(int i=0; i<this.m;i++)
        {
            if(this.keys[i] != null)
                h.put(this.keys[i],this.vals[i]);
        }
        this.m = h.m;
        this.n = h.n;
        this.keys = h.keys;
        this.vals = h.vals;

    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * The load factor should never exceed 50% so make sure to resize correctly
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        //TODO STUDENT
        if(this.n>= this.m/2)
            resize(2*this.m);
        int i = 0;
        for(i =hash(key); keys[i] != null;i = (i+1)%this.m)
        {
            if(this.keys[i].equals(key))
            {
                this.vals[i] = val;
                return;
            }

        }
        this.keys[i] = key;
        this.vals[i] = val;
        this.n = this.n +1;
    }

    /**
     * Returns the value associated with the specified key.
     * @param key the key
     * @return the value associated with {@code key};
     *         {@code null} if no such value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        //TODO STUDENT
        if(key == null)
            throw new IllegalArgumentException();
        for(int i = hash(key); this.keys[i] != null; i = (i+1)%this.m)
        {
            if(this.keys[i].equals(key))
                return vals[i];
        }
        return null;
    }

    /**
    * Returns all keys in this symbol table as an array
    */
    public Object[] keys() {
        Object[] exportKeys = new Object[n];
        int j = 0;
        for (int i = 0; i < m; i++)
            if (keys[i] != null) exportKeys[j++] = keys[i];
        return exportKeys;
    }

}
