import java.util.*;

class MinFind {
    private List<Vertice> city;
    private List<Sides> side;
    private Set<Vertice> city1, city2;
    private Map<Vertice, Vertice> before;
    private Map<Vertice, Integer> after;

    MinFind(Dijkstra graph) {

        this.city = new ArrayList<>(graph.getCities());
        this.side = new ArrayList<>(Dijkstra.getSides());
    }

    void run(Vertice source) {
        city1 = new HashSet<>();
        city2 = new HashSet<>();
        after = new HashMap<>();
        before = new HashMap<>();
        after.put(source, 0);
        city2.add(source);
        while (city2.size() > 0) {
            Vertice node = getMinimum(city2);
            city1.add(node);
            city2.remove(node);
            findDistance(node);
        }
    }

    private void findDistance(Vertice node) {
        List<Vertice> adjacentCities = getSides(node);
        for (Vertice target : adjacentCities) {
            if (getDistance(target) > getDistance(node) + getDistance(node, target)) {
                after.put(target, getDistance(node) + getDistance(node, target));
                before.put(target, node);
                city2.add(target);
            }
        }
    }

    private int getDistance(Vertice node, Vertice target) {
        for (Sides sides : side)
            if (sides.getV1().equals(node) && sides.getV2().equals(target))
                return sides.getLength();
        throw new RuntimeException();
    }

    private int getDistance(Vertice destination) {
        Integer d = after.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else return d;
    }

    private List<Vertice> getSides(Vertice node) {
        List<Vertice> sides = new ArrayList<>();
        for (Sides edge : side)
            if ((edge.getV1().equals(node)) && !isSettled(edge.getV2()))
                sides.add(edge.getV2());
        return sides;
    }

    private boolean isSettled(Vertice city) {
        return city1.contains(city);
    }

    private Vertice getMinimum(Set<Vertice> cities) {
        Vertice min = null;
        for (Vertice city : cities) {
            if (min != null) {
                if (getDistance(city) < getDistance(min))
                    min = city;
            } else
                min = city;
        }
        return min;
    }

    LinkedList<Vertice> getRoad(Vertice target) {
        LinkedList<Vertice> path = new LinkedList<>();
        Vertice step = target;
        if (before.get(step) == null)
            return null;
        path.add(step);
        while (before.get(step) != null) {
            step = before.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }
}