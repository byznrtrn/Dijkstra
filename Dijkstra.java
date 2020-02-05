import java.util.List;

public class Dijkstra {
    private final List<Vertice> cities;
    private static List<Sides> sides;

    Dijkstra(List<Vertice> cities, List<Sides> sides) {
        this.cities = cities;
        Dijkstra.sides = sides;
    }

    static List<Sides> getSides() {
        return sides;
    }

    List<Vertice> getCities() {
        return cities;
    }
}
