import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
    private T name;
    private List<Vertex<T>> adjacentVertices;

    public Vertex(T name, List<Vertex<T>> adjacentVertices) {
        this.name = name;
        this.adjacentVertices = adjacentVertices;
    }

    public Vertex(T name) {
        this.name = name;
        this.adjacentVertices = new ArrayList<Vertex<T>>();
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

    public List<Vertex<T>> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void setAdjacentVertices(List<Vertex<T>> adjacentVertices) {
        this.adjacentVertices = adjacentVertices;
    }

    public void setAdjacentVertex(Vertex adjacentVertex) {
        this.adjacentVertices.add(adjacentVertex);
    }
}
