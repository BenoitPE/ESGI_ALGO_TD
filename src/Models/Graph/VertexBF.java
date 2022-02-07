package Models.Graph;

public class VertexBF {
    private final Vertex vertex;
    private double distanceFromSource;
    private Vertex bestParentFromSource;

    public VertexBF(Vertex vertex) {
        this.vertex = vertex;
        this.distanceFromSource = Double.POSITIVE_INFINITY;
        this.bestParentFromSource = null;
    }

    //region Getters and Setters
    public Vertex getVertex() {
        return vertex;
    }

    public double getDistFromSource() {
        return distanceFromSource;
    }

    public void setDistFromSource(double distance) {
        this.distanceFromSource = distance;
    }

    public Vertex getBestParent() {
        return bestParentFromSource;
    }

    public void setBestParent(Vertex parent) {
        this.bestParentFromSource = parent;
    }
    //endregion
}
