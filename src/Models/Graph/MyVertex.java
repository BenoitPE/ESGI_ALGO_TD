package Models.Graph;

import java.util.ArrayList;
import java.util.List;

public class MyVertex<T> {
    private T name;
    private List<MyEdge> adjacentVertices;

    //region Constructors
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

    public List<MyEdge> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void setAdjacentVertices(List<MyEdge> adjacentVertices) {
        this.adjacentVertices = adjacentVertices;
    }

    public void setAdjacentVertex(MyEdge adjacentVertex) {
        this.adjacentVertices.add(adjacentVertex);
    }
    //endregion
}
