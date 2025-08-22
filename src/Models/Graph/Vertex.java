package Models.Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String name;
    private List<Edge> adjacentVertices;

    public Vertex(String name) {
        this.name = name;
        this.adjacentVertices = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Edge> getAdjacents() {
        return adjacentVertices;
    }

    public void setAdjacent(Edge adjacent) {
        this.adjacentVertices.add(adjacent);
    }
}
