package Models.Graph;

public class VertexDijkstra {
    private final Vertex vertex;
    private boolean visited;
    private double distanceFromSource;
    private Vertex bestParentFromSource;

    public VertexDijkstra(Vertex vertex) {
        this.vertex = vertex;
        this.visited = false;
        this.distanceFromSource = Double.POSITIVE_INFINITY;
        this.bestParentFromSource = null;
    }

    public VertexDijkstra(Vertex vertex, boolean visited, double distanceFromSource, Vertex bestParentFromSource) {
        this.vertex = vertex;
        this.visited = visited;
        this.distanceFromSource = distanceFromSource;
        this.bestParentFromSource = bestParentFromSource;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
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
}
