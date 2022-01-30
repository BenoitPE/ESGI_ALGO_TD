package Models.Graph;

import java.util.ArrayList;
import java.util.List;

public class MyVertex<T> {
    private T name;
    private List<MyOrientedEdge> adjacentVertices;

    //region Constructors
    public MyVertex(T name, List<MyOrientedEdge> adjacentVertices) {
        this.name = name;
        this.adjacentVertices = adjacentVertices;
    }

    public MyVertex(T name) {
        this.name = name;
        this.adjacentVertices = new ArrayList<>();
    }
    //endregion

    //region Getters and Setters
    public int getDegree() {
        return this.adjacentVertices.size();
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public List<MyOrientedEdge> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void setAdjacentVertices(List<MyOrientedEdge> adjacentVertices) {
        this.adjacentVertices = adjacentVertices;
    }

    public void setAdjacentVertex(MyOrientedEdge adjacentVertex) {
        this.adjacentVertices.add(adjacentVertex);
    }
    //endregion
}
