package Models.Graph;

public class Edge {
    private final Vertex destination;
    private final double weight;

    public Edge(Vertex destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getDest() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }
}
