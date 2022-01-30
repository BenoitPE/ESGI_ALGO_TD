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

    //region Print
    public static void print(MyDijkstraVertex v) {
        System.out.print("Name: " + v.getVertex().getName()
                + ", v.isVisited(): " + v.isVisited()
                + ", v.getDistanceFromSource(): " + v.getDistanceFromSource()
        );
        if (v.getBestParentFromSource() != null)
            System.out.println(", v.getBestParentFromSource().getName(): " + v.getBestParentFromSource().getName());
        else
            System.out.println();
    }
    //endregion
}
