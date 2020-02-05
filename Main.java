import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static LinkedList<Vertice> vertex;
    private static LinkedList<Sides> side;

    public static void main(String[] args) throws Exception {

        ArrayList<Integer> data = new ArrayList<>();
        ArrayList<Integer> city = new ArrayList<>();
        vertex = new LinkedList<>();
        side = new LinkedList<>();
        String location = null;
        int cost = Integer.MAX_VALUE;

        Scanner scan = read();
        scan.nextInt();

        while (scan.hasNextInt())
            data.add(scan.nextInt());

        for (int i = 0; i < data.size(); i++)
            if (i % 3 == 0 || i % 3 == 1)
                if (!city.contains(data.get(i)))
                    city.add(data.get(i));

        for (int i = 0; i < city.size(); i++) {
            int j = i + 1;
            Vertice vertice = new Vertice("City " + j, "City " + j);
            vertex.add(vertice);
        }

        int count = 0;

        for (int i = 0; i < data.size(); i = i + 3) {
            concat("Sides" + count, data.get(i), data.get(i + 1), data.get(i + 2));
            count++;
        }

        Dijkstra dijk = new Dijkstra(vertex, side);
        MinFind cm = new MinFind(dijk);

        for (int i = 0; i < vertex.size(); i++) {
            int length = 0;
            for (int j = 0; j < vertex.size(); j++) {
                cm.run(vertex.get(i));
                LinkedList<Vertice> path = cm.getRoad(vertex.get(j));

                if (i != j)
                    for (int k = 0; k < path.size() - 1; k++)
                        for (Sides sides : side)
                            if (sides.getV1().equals(path.get(k)) && sides.getV2().equals(path.get(k + 1)))
                                length += sides.getLength();
            }
            if (!(cost <= length)) {
                cost = length;
                location = vertex.get(i).toString();
            }
        }
        System.out.println("The best city is " + location);
    }

    private static void concat(String id, int v1, int v2, int cost) {
        side.add(new Sides(id, vertex.get(v1 - 1), vertex.get(v2 - 1), cost));
        side.add(new Sides(id, vertex.get(v2 - 1), vertex.get(v1 - 1), cost));
    }

    private static Scanner read() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter filename: ");

        return new Scanner(new File(new File(".").getCanonicalPath() + "\\" + scan.nextLine()));
    }
}
