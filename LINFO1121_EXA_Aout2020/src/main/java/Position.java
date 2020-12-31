public class Position {
    // Ne modifiez pas cette classe.
    public final int x;
    public final int y;

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public static int distance(Position a, Position b)
    {
        // Ne modifiez pas cette fonction.
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    @Override
    public String toString()
    {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
