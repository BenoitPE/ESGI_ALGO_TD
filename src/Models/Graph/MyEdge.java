package Models.Graph;

public class MyEdge {
    private MyVertex destination;
    private double weight;

    //region Constructors
    public MyEdge(MyVertex destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public MyEdge(MyVertex destination) {
        this.destination = destination;
        this.weight = 1;
    }
    //endregion

    //region Getters and Setters
    public MyVertex getDestination() {
        return destination;
    }

    public void setDestination(MyVertex destination) {
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
