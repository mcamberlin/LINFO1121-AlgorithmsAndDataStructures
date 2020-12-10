

public class GlobalWarmingImpl extends GlobalWarming {

    private int id[];
    private int size[];

    private int nbLines;
    private int nbColumns;
    private int nbSites;

    private int nbSafeZones = 0;
    private int nbLinks = 0;

    public GlobalWarmingImpl(int[][] altitude, int waterLevel)
    {
        // expected pre-processing time in the constructror : O(n^2 log(n^2))

        super(altitude, waterLevel);
        nbLines = altitude.length;
        nbColumns = altitude[0].length;
        nbSites = nbLines * nbColumns;

        id = new int[nbSites];
        size = new int[nbSites];
        for(int i =0; i<nbSites; i++)
        {
            id[i] = i;
            size[i] =1;
        }

        int pointer;
        for(int i =0; i< nbLines; i++ )
        {
            for(int j =0; j<nbColumns; j++)
            {
                pointer = i*nbLines + j;
                //id[pointer] = pointer;
                //size[pointer] = 1;
                if(altitude[i][j] > waterLevel)
                {
                    nbSafeZones++;
                    if(j+1 < nbColumns && altitude[i][j+1] > waterLevel) // check right
                    {
                        union( pointer, pointer+1 );
                    }
                    if(i+1 < nbLines && altitude[i+1][j] > waterLevel) //check south
                    {
                        union( pointer, pointer + nbColumns);
                    }
                }
            }
        }
    }


    public int find(int p)
    {
        while(p!= id[p])
            p = id[p];
        return p;
    }

    public void union(int a, int b)
    {
        assert a <nbSites;
        assert b <nbSites;
        int i = find(a); // return the root of a
        int j = find(b); // return the root of b

        if(id[a] == id[b]) // they are already connected
            return;

        // Make smaller root point to larger one
        if(size[i] < size[j]) // b is bigger
        {
            id[i] = j;
            size[j] += size[i];
        }
        else // a is bigger
        {
            id[j] = i;
            size[i] += size[j];
        }
        nbLinks++;
    }

    public int nbIslands()
    {
        // TODO
        // expected time complexity O(1)
        return nbSafeZones - nbLinks;
    }


    public boolean onSameIsland(Point p1, Point p2)
    {
        if(p1.equals(p2))
            return false;
        // TODO
        // expected time complexity O(1)

        return find(p1.x *nbLines + p1.y) == find(p2.x *nbLines + p2.y);
    }
}
