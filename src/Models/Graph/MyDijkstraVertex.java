package Models.Graph;

public class MyDijkstraVertex {
    private MyVertex vertex;
    private boolean visited;
    private double distanceFromSource;
    private MyVertex bestParentFromSource;

    public MyDijkstraVertex(MyVertex vertex) {
        this.vertex = vertex;
        this.visited = false;
        this.distanceFromSource = Double.POSITIVE_INFINITY;
        this.bestParentFromSource = null;
    }

    public MyDijkstraVertex(MyVertex vertex, boolean visited, double distanceFromSource, MyVertex bestParentFromSource) {
        this.vertex = vertex;
        this.visited = visited;
        this.distanceFromSource = distanceFromSource;
        this.bestParentFromSource = bestParentFromSource;
    }

    //region Getters and Setters
    public MyVertex getVertex() {
        return vertex;
    }

    public void setVertex(MyVertex vertex) {
        this.vertex = vertex;
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

    public MyVertex getBestParent() {
        return bestParentFromSource;
    }

    public void setBestParent(MyVertex parent) {
        this.bestParentFromSource = parent;
    }
    //endregion
}
