public class Sides {

    private final String id;
    private final Vertice v1, v2;
    private final int length;

    Sides(String id, Vertice v1, Vertice v2, int length)	{
        this.id = id;
        this.v1 = v1;
        this.v2 = v2;
        this.length = length;
    }

    public String getId() {
        return id;
    }

    Vertice getV1() {
        return v1;
    }

    Vertice getV2() {
        return v2;
    }

    int getLength() {
        return length;
    }

    @Override
    public String toString()	{
        return v1 + " " + v2;
    }

}
