package Models.Graph;

public class MyBFVertex
{
    private MyVertex vertex;
    private double distanceFromSource;
    private MyVertex bestParentFromSource;

    public MyBFVertex(MyVertex vertex) {
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
