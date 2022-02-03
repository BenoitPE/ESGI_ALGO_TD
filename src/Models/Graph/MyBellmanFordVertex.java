package Models.Graph;

public class MyBellmanFordVertex
{
    private MyVertex vertex;
    private double distanceFromSource;
    private MyVertex bestParentFromSource;

    public MyBellmanFordVertex(MyVertex vertex) {
        this.vertex = vertex;
        this.distanceFromSource = Double.POSITIVE_INFINITY;
        this.bestParentFromSource = null;
    }

    //region Getters and Setters
    public MyVertex getVertex() {
        return vertex;
    }

    public void setVertex(MyVertex vertex) {
        this.vertex = vertex;
    }

    public double getDistanceFromSource() {
        return distanceFromSource;
    }

    public void setDistanceFromSource(double distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    public MyVertex getBestParentFromSource() {
        return bestParentFromSource;
    }

    public void setBestParentFromSource(MyVertex bestParentFromSource) {
        this.bestParentFromSource = bestParentFromSource;
    }
    //endregion
}
