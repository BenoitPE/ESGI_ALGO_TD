package Models;

import java.util.ArrayList;
import java.util.List;

public class MyVertex<T> {
    private T name;
    private List<MyVertex<T>> adjacentVertices;

    public MyVertex(T name, List<MyVertex<T>> adjacentVertices) {
        this.name = name;
        this.adjacentVertices = adjacentVertices;
    }

    public MyVertex(T name) {
        this.name = name;
        this.adjacentVertices = new ArrayList<MyVertex<T>>();
    }

    public int getDegree() {
        return this.adjacentVertices.size();
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public List<MyVertex<T>> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void setAdjacentVertices(List<MyVertex<T>> adjacentVertices) {
        this.adjacentVertices = adjacentVertices;
    }

    public void setAdjacentVertex(MyVertex adjacentVertex) {
        this.adjacentVertices.add(adjacentVertex);
    }
}
