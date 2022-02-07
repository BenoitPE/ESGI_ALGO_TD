package Models.Graph;

public class Edge {
    private Vertex destination;
    private double weight;

    //region Constructors
    public Edge(Vertex destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(Vertex destination) {
        this.destination = destination;
        this.weight = 1;
    }
    //endregion

    //region Getters and Setters
    public Vertex getDest() {
        return destination;
    }

    public void setDest(Vertex destination) {
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    //endregion
}
