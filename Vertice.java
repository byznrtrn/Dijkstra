public class Vertice {
    final private String id, name;

    Vertice(String id, String name)	{
        this.id = id;
        this.name = name;
    }

    private String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertice other = (Vertice) obj;
        if (id == null)
            return other.id == null;
        else return id.equals(other.id);
    }

    @Override
    public String toString()    {
        return getName();
    }
}
